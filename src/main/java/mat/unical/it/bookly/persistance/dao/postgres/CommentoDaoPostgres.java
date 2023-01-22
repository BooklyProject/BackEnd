package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.CommentoDao;
import mat.unical.it.bookly.persistance.model.Commento;
import mat.unical.it.bookly.persistance.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentoDaoPostgres implements CommentoDao {
    Connection conn;
    public CommentoDaoPostgres(Connection conn){
        this.conn = conn;
    }
    @Override
    public List<Commento> findAllWroteByUser(Long idUtente) {
        List<Commento> commentiUtente = new ArrayList<>();
        String query = "" +
                "SELECT c.id as c_id, c.descrizione as c_des, c.data as c_da, c.mi_piace " +
                "as c_mi, c.non_mi_piace as c_no, c.recensione as c_rec " +
                "FROM commenti c " +
                "JOIN post p ON p.id = c.id " +
                "JOIN utenti u ON p.utente = u.id " +
                "WHERE p.utente = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idUtente);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Commento commento = new Commento();
                commento.setId(rs.getLong("c_id"));
                commento.setDescrizione(rs.getString("c_des"));
                commento.setData(rs.getDate("c_da"));
                commento.setNumeroMiPiace(rs.getInt("c_mi"));
                commento.setNumeroNonMiPiace(rs.getInt("c_no"));
                commento.setRecensioni(rs.getLong("c_rec"));

                commentiUtente.add(commento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentiUtente;
    }

    @Override
    public Commento findByPrimaryKey(Long id) {
        Commento c = null;
        String query = "select * from commenti where id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                c = new Commento();
                c.setId(rs.getLong("id"));
                c.setDescrizione(rs.getString("descrizione"));
                c.setData(rs.getDate("data"));
                c.setNumeroMiPiace(rs.getInt("mi_piace"));
                c.setNumeroNonMiPiace(rs.getInt("non_mi_piace"));
                c.setRecensioni(rs.getLong("recensione"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void saveOrUpdate(Commento commento) {
        if(findByPrimaryKey(commento.getId()) == null){

            Post p = new Post();
            Long id = IdBroker.getId(conn);
            p.setId(id);
            p.setIdUtente(Long.valueOf(29));
            DBManager.getInstance().getPostDao().saveUpdate(p);
            String insertStr = "INSERT INTO commenti VALUES (?,?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);

                st.setLong(1,id);
                st.setString(2,commento.getDescrizione());
                st.setDate(3,commento.getData());
                st.setInt(4,commento.getNumeroMiPiace());
                st.setInt(5,commento.getNumeroNonMiPiace());
                st.setLong(6,commento.getRecensioni());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE commenti set descrizione = ?," +
                    "data = ?," +
                    "mi_piace = ?," +
                    "non_mi_piace = ?," +
                    "recensione = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,commento.getDescrizione());
                st.setDate(2,commento.getData());
                st.setInt(3,commento.getNumeroMiPiace());
                st.setInt(4,commento.getNumeroNonMiPiace());
                st.setLong(5,commento.getRecensioni());
                st.setLong(6,commento.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM commenti where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
