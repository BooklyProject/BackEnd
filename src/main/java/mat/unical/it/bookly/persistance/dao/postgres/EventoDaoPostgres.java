package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.EventoDao;
import mat.unical.it.bookly.persistance.model.Commento;
import mat.unical.it.bookly.persistance.model.Evento;
import mat.unical.it.bookly.persistance.model.Post;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDaoPostgres implements EventoDao {
    Connection conn;
    public EventoDaoPostgres(Connection conn){this.conn = conn;}


    @Override
    public List<Evento> findAllCreatedByUser(Long idUtente) {
        List<Evento> eventi = new ArrayList<>();
        String query = "" +
                "SELECT e.id as e_id, e.nome as e_nome, e.descrizione as e_de," +
                "e.data as e_data, e.luogo as e_luogo " +
                "FROM eventi e " +
                "JOIN post p ON p.id = e.id " +
                "JOIN utenti u ON p.utente = u.id " +
                "WHERE p.utente = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idUtente);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getLong("e_id"));
                evento.setNome(rs.getString("e_nome"));
                evento.setDescrizione(rs.getString("e_de"));
                evento.setLuogo(rs.getString("e_luogo"));

                eventi.add(evento);
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return  eventi;
    }



    @Override
    public Evento findByPrimaryKey(Long id) {
        Evento e = null;
        String query = "select * from eventi where id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                e = new Evento();
                e.setId(rs.getLong("id"));
                e.setNome(rs.getString("nome"));
                e.setDescrizione(rs.getString("descrizione"));
                e.setLuogo(rs.getString("luogo"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public void saveOrUpdate(Evento evento) {
        if(findByPrimaryKey(evento.getId()) == null){
            //
            Post p = new Post();
            Long id = IdBroker.getId(conn);
            p.setId(id);
            p.setIdUtente(Long.valueOf(30));
            DBManager.getInstance().getPostDao().saveUpdate(p);
            String insertStr = "INSERT INTO eventi VALUES (?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);

                st.setLong(1,id);
                st.setString(2,evento.getNome());
                st.setString(3,evento.getDescrizione());
                st.setDate(4,evento.getData());
                st.setString(5,evento.getLuogo());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE eventi set nome = ?," +
                    "descrizione = ?," +
                    "data = ?," +
                    "luogo = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,evento.getNome());
                st.setString(2,evento.getDescrizione());
                st.setDate(3,evento.getData());
                st.setString(4,evento.getLuogo());
                st.setLong(5,evento.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM eventi where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
