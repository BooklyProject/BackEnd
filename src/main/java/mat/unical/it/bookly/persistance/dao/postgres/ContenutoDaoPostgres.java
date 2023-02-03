package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.ContenutoDao;
import mat.unical.it.bookly.persistance.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContenutoDaoPostgres implements ContenutoDao {

    Connection conn;
    public ContenutoDaoPostgres(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Libro> findBooksForCollection(Long idRaccolta) {
        List<Libro> libri = new ArrayList<>();
        String query = "SELECT libro from contiene where raccolta = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idRaccolta);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Libro libro = DBManager.getInstance().getLibroDao().findByPrimaryKey(rs.getString("libro"));
                libri.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libri;
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
    public void save(Long idRaccolta, String ISBNLibro) {
        String insertStr = "INSERT INTO contiene VALUES (?,?)";
        PreparedStatement st;
        try{
            st = conn.prepareStatement(insertStr);

            st.setLong(1,idRaccolta);
            st.setString(2,ISBNLibro);

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

    @Override
    public void deleteBooksForCollections(Long idRaccolta) {
        String query = "DELETE FROM contiene where raccolta = ? ";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idRaccolta);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
