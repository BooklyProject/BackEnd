package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.EventoDao;
import mat.unical.it.bookly.persistance.model.Commento;
import mat.unical.it.bookly.persistance.model.Evento;
import mat.unical.it.bookly.persistance.model.Post;
import mat.unical.it.bookly.persistance.model.Utente;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDaoPostgres implements EventoDao {
    Connection conn;
    public EventoDaoPostgres(Connection conn){this.conn = conn;}


    @Override
    public List<Evento> findAll() {
        List<Evento> eventi = new ArrayList<>();
        String query = "select * from eventi"; //ritorna la lista di tutti gli utenti
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getLong("id"));
                evento.setNome(rs.getString("nome"));
                evento.setDescrizione(rs.getString("descrizione"));
                evento.setData(rs.getDate("data"));
                evento.setLuogo(rs.getString("luogo"));
                evento.setPartecipanti(rs.getInt("partecipanti"));

                eventi.add(evento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventi;
    }

    @Override
    public List<Evento> findAllCreatedByUser(Long idUtente) {
        List<Evento> eventi = new ArrayList<>();
        String query = "" +
                "SELECT e.id as e_id, e.nome as e_nome, e.descrizione as e_de," +
                "e.data as e_data, e.luogo as e_luogo, e.partecipanti as e_partecipanti, e.orario as e_orario " +
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
                evento.setData(rs.getDate("e_data"));
                evento.setLuogo(rs.getString("e_luogo"));
                evento.setPartecipanti(rs.getInt("e_partecipanti"));
                evento.setOrario(rs.getString("e_orario"));

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
                e.setData(rs.getDate("data"));
                e.setLuogo(rs.getString("luogo"));
                e.setPartecipanti(rs.getInt("partecipanti"));
                e.setOrario(rs.getString("orario"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public void saveOrUpdate(Evento evento, Long idUtente) {
        if(evento.getId() == null || findByPrimaryKey(evento.getId()) == null){
            Post p = new Post();
            Long id = IdBroker.getId(conn);
            p.setId(id);
            p.setIdUtente(idUtente);
            DBManager.getInstance().getPostDao().saveUpdate(p);
            String insertStr = "INSERT INTO eventi VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);

                st.setLong(1,id);
                st.setString(2,evento.getNome());
                st.setString(3,evento.getDescrizione());
                st.setDate(4,evento.getData());
                st.setString(5,evento.getLuogo());
                st.setInt(6,evento.getPartecipanti());
                st.setString(7,evento.getOrario());


                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE eventi set nome = ?," +
                    "descrizione = ?," +
                    "data = ?," +
                    "luogo = ?," +
                    "partecipanti = ?," +
                    "orario = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,evento.getNome());
                st.setString(2,evento.getDescrizione());
                st.setDate(3,evento.getData());
                st.setString(4,evento.getLuogo());
                st.setInt(5,evento.getPartecipanti());
                st.setString(6,evento.getOrario());
                st.setLong(7,evento.getId());

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
            DBManager.getInstance().getPostDao().delete(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Evento> findAvailableEvents(Long idUtente) {
        List<Evento> eventi = new ArrayList<>();
        String query = "SELECT * FROM eventi e1 WHERE e1.id NOT IN (SELECT p.evento FROM partecipa p WHERE p.utente = ?)" +
                "AND e1.id NOT IN (SELECT e.id FROM eventi e JOIN post p ON p.id = e.id JOIN utenti u ON p.utente = u.id WHERE p.utente = ?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idUtente);
            st.setLong(2, idUtente);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Evento evento = new Evento();
                evento.setId(rs.getLong("id"));
                evento.setNome(rs.getString("nome"));
                evento.setDescrizione(rs.getString("descrizione"));
                evento.setData(rs.getDate("data"));
                evento.setLuogo(rs.getString("luogo"));
                evento.setPartecipanti(rs.getInt("partecipanti"));
                evento.setOrario(rs.getString("orario"));

                eventi.add(evento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return eventi;
    }
}
