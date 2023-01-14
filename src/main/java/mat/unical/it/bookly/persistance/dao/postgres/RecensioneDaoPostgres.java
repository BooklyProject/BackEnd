package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.RecensioneDao;
import mat.unical.it.bookly.persistance.model.Post;
import mat.unical.it.bookly.persistance.model.Recensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecensioneDaoPostgres implements RecensioneDao {

    Connection conn;
    public RecensioneDaoPostgres(Connection conn){this.conn = conn;};

    @Override
    public List<Recensione> findAllWroteByUser(Long idUtente) {
        return null;
    }

    @Override
    public Recensione findByPrimaryKey(Long id) {
        Recensione r = null;
        String query = "select * from recensioni where id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                r = new Recensione();
                r.setId(rs.getLong("id"));
                r.setDescrizione(rs.getString("descrizione"));
                r.setVoto(rs.getInt("voto"));
                r.setData(rs.getDate("data"));
                r.setNumeroMiPiace(rs.getInt("mi_piace"));
                r.setNumeroNonMiPiace(rs.getInt("non_mi_piace"));
                r.setLibro(rs.getString("libro"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;s

    }

    @Override
    public void saveOrUpdate(Recensione recensione) {
        if(findByPrimaryKey(recensione.getId()) == null){
            String insertStr = "INSERT INTO recensioni VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);
                Long newId = IdBroker.getId(conn);

                st.setLong(1,newId);
                st.setString(2,recensione.getDescrizione());
                st.setInt(3,recensione.getVoto());
                st.setDate(4,recensione.getData());
                st.setInt(5,recensione.getNumeroMiPiace());
                st.setInt(6,recensione.getNumeroNonMiPiace());
                st.setString(7,recensione.getLibro());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE recensioni set descrizione = ?," +
                    "voto = ?," +
                    "data = ?," +
                    "mi_piace = ?," +
                    "non_mi_piace = ?," +
                    "libro = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,recensione.getDescrizione());
                st.setInt(2,recensione.getVoto());
                st.setDate(3,recensione.getData());
                st.setInt(4,recensione.getNumeroMiPiace());
                st.setInt(5,recensione.getNumeroNonMiPiace());
                st.setString(6,recensione.getLibro());
                st.setLong(7,recensione.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {

    }
}
