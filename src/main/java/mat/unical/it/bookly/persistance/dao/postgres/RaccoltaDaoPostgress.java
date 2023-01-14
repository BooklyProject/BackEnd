package mat.unical.it.bookly.persistance.dao.postgres;

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
    public Raccolta findByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public void saveOrUpdate(Raccolta raccolta) {

    }

    @Override
    public void delete(Long id) {

    }
}
