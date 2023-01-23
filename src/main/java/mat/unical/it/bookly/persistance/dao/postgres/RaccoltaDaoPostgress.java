package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.RaccoltaDao;
import mat.unical.it.bookly.persistance.model.Raccolta;
import mat.unical.it.bookly.persistance.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaccoltaDaoPostgress implements RaccoltaDao {

    Connection conn;
    public RaccoltaDaoPostgress(Connection conn){this.conn = conn;};

    @Override
    public List<Raccolta> findAllForUser(Long id) {
        List<Raccolta> raccolteUtente = new ArrayList<>();
        String query = "select * from raccolte where utente = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Raccolta raccolta = new Raccolta();
                raccolta.setId(rs.getLong("id"));
                raccolta.setNome(rs.getString("nome"));
                raccolta.setUtente(rs.getLong("utente"));

                raccolteUtente.add(raccolta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raccolteUtente;

    }

    @Override
    public List<Raccolta> findAll() {
        List<Raccolta> raccolte = new ArrayList<>();
        String query = "select * from raccolte";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                Raccolta raccolta = new Raccolta();
                raccolta.setId(rs.getLong("id"));
                raccolta.setNome(rs.getString("nome"));
                raccolta.setUtente(rs.getLong("utente"));

                raccolte.add(raccolta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raccolte;
    }

    @Override
    public Raccolta findByPrimaryKey(Long id) {
        Raccolta raccolta = null;
        String query = "select * from raccolte where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                raccolta = new Raccolta();
                raccolta.setId(rs.getLong("id"));
                raccolta.setNome(rs.getString("nome"));
                raccolta.setUtente(rs.getLong("utente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raccolta ;
    }


    @Override
    public void saveOrUpdate(Raccolta raccolta) {
        //TODO: try update
        if(findByPrimaryKey(raccolta.getId()) == null){
            String insertStr = "INSERT INTO raccolte VALUES (?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);
                Long newId = IdBroker.getId(conn);

                st.setLong(1,newId);
                st.setString(2,raccolta.getNome());
                st.setLong(3,raccolta.getUtente());
                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE raccolte set nome = ?," +
                    "utente = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,raccolta.getNome());
                st.setLong(2,raccolta.getUtente());
                st.setLong(3,raccolta.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM raccolte where id = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllForUser(Long idUtente) {

        String query = "DELETE FROM raccolte where utente = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idUtente);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

