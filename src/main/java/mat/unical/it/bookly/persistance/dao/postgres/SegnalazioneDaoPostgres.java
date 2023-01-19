package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.SegnalazioneDao;
import mat.unical.it.bookly.persistance.model.Raccolta;
import mat.unical.it.bookly.persistance.model.Segnalazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SegnalazioneDaoPostgres implements SegnalazioneDao {

    Connection conn;

    public SegnalazioneDaoPostgres(Connection conn){this.conn = conn;};

    @Override
    public List<Segnalazione> findAll() {
        List<Segnalazione> segnalazioni = new ArrayList<>();
        String query = "select * from segnalazioni";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Segnalazione segnalazione = new Segnalazione();
                segnalazione.setId(rs.getLong("id"));
                segnalazione.setTipo(rs.getString("tipo"));
                segnalazione.setPost(rs.getLong("post"));
                segnalazione.setUtente(rs.getLong("utente"));
                segnalazione.setAmministratore((rs.getLong("amministratore")));
                segnalazione.setDescrizione(rs.getString("descrizione"));

                segnalazioni.add(segnalazione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return segnalazioni;
    }

    @Override
    public Segnalazione findByPrimaryKey(Long id) {
        Segnalazione segnalazione = null;
        String query = "select * from segnalazioni where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                segnalazione = new Segnalazione();
                segnalazione.setId(rs.getLong("id"));
                segnalazione.setTipo(rs.getString("tipo"));
                segnalazione.setPost(rs.getLong("post"));
                segnalazione.setUtente(rs.getLong("utente"));
                segnalazione.setAmministratore(rs.getLong("amministratore"));
                segnalazione.setDescrizione(rs.getString("descrizione"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return segnalazione;
    }

    @Override
    public void saveOrUpdate(Segnalazione segnalazione) {
        if(findByPrimaryKey(segnalazione.getId()) == null){
            String insertStr = "INSERT INTO segnalazioni VALUES (?,?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);
                Long newId = IdBroker.getId(conn);

                st.setLong(1,segnalazione.getId());
                st.setString(2,segnalazione.getTipo());
                st.setLong(3,segnalazione.getPost());
                st.setLong(4,segnalazione.getUtente());
                st.setLong(5,segnalazione.getAmministratore());
                st.setString(6,segnalazione.getDescrizione());
                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE raccolte set tipo = ?," +
                    "post = ?," +
                    "utente = ?," +
                    "amministratore = ?," +
                    "descrizione = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);

                st.setString(1,segnalazione.getTipo());
                st.setLong(2,segnalazione.getPost());
                st.setLong(3,segnalazione.getUtente());
                st.setLong(4,segnalazione.getAmministratore());
                st.setString(5,segnalazione.getDescrizione());
                st.setLong(6,segnalazione.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Segnalazione> findByAdministrator(Long amministratore) {
        List<Segnalazione> segnalazioni = new ArrayList<>();
        String query = "select * from segnalazioni where amministratore = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,amministratore);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Segnalazione segnalazione = new Segnalazione();
                segnalazione.setId(rs.getLong("id"));
                segnalazione.setTipo(rs.getString("tipo"));
                segnalazione.setPost(rs.getLong("post"));
                segnalazione.setUtente(rs.getLong("utente"));
                segnalazione.setAmministratore((rs.getLong("amministratore")));
                segnalazione.setDescrizione(rs.getString("descrizione"));

                segnalazioni.add(segnalazione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return segnalazioni;
    }


    @Override
    public void delete(Long id) {
        String query = "DELETE FROM segnalazioni where id = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
