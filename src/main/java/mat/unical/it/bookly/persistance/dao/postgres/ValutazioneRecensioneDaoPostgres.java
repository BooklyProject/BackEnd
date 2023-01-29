package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.dao.ValutazioneRecensioneDao;
import mat.unical.it.bookly.persistance.model.ValutazioneRecensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValutazioneRecensioneDaoPostgres implements ValutazioneRecensioneDao {

    Connection conn;

    public ValutazioneRecensioneDaoPostgres(Connection conn) {  //attraverso il costruttore settiamo l'oggetto di tipo Connection
        this.conn = conn;
    }

    @Override
    public ValutazioneRecensione findByPrimaryKey(Long idRecensione, Long idUtente) {
        ValutazioneRecensione valutazioneRecensione = null;
        String query = "select * from valutazionirecensioni where recensione = ? and utente = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idRecensione);
            st.setLong(2, idUtente);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                valutazioneRecensione = new ValutazioneRecensione();
                valutazioneRecensione.setRecensione(rs.getLong("recensione"));
                valutazioneRecensione.setUtente(rs.getLong("utente"));
                valutazioneRecensione.setTipo(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valutazioneRecensione;
    }

    @Override
    public void saveOrUpdate(ValutazioneRecensione v) {
        if(findByPrimaryKey(v.getRecensione(), v.getUtente()) == null){

            String insertStr = "INSERT INTO valutazionirecensioni VALUES (?,?,?)";
            try{
                PreparedStatement st = conn.prepareStatement(insertStr);

                st.setLong(1,v.getRecensione());
                st.setLong(2,v.getUtente());
                st.setString(3,v.getTipo());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE valutazionirecensioni set tipo = ? where recensione = ? and utente = ?";

            try{
                PreparedStatement st = conn.prepareStatement(updateStr);

                st.setString(1,v.getTipo());
                st.setLong(2,v.getRecensione());
                st.setLong(3,v.getUtente());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long idRecensione, Long idUtente) {
        String query = "DELETE FROM valutazionirecensioni where recensione = ? and utente = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idRecensione);
            st.setLong(2,idUtente);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
