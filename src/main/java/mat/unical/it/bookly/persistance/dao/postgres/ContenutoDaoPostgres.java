package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.ContenutoDao;
import mat.unical.it.bookly.persistance.model.Commento;
import mat.unical.it.bookly.persistance.model.Contenuto;
import mat.unical.it.bookly.persistance.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContenutoDaoPostgres implements ContenutoDao {

    Connection conn;
    public ContenutoDaoPostgres(Connection conn){
        this.conn = conn;
    }

    @Override
    public Contenuto findByPrimaryKey(Long idRaccolta, String ISBNLibro) {
        Contenuto contenuto = null;
        String query = "select * from contiene where raccolta = ? and libro = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idRaccolta);
            st.setString(2, ISBNLibro);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                contenuto = new Contenuto();
                contenuto.setRaccolta(rs.getLong("raccolta"));
                contenuto.setLibro(rs.getString("libro"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contenuto;
    }

    @Override
    public void save(Contenuto contenuto) {
        String insertStr = "INSERT INTO contiene VALUES (?,?)";
        PreparedStatement st;
        try{
            st = conn.prepareStatement(insertStr);

            st.setLong(1,contenuto.getRaccolta());
            st.setString(2,contenuto.getLibro());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long idRaccolta, String ISBNLibro) {
        String query = "DELETE FROM contiene where raccolta = ? and libro = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idRaccolta);
            st.setString(2,ISBNLibro);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
