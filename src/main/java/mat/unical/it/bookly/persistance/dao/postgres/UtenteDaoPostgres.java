package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDaoPostgres implements UtenteDao {
    Connection conn;

    public UtenteDaoPostgres(Connection conn){  //attraverso il costruttore settiamo l'oggetto di tipo Connection
        this.conn = conn;
    }

    @Override
    public List<Utente> findAll() {
        List<Utente> utenti = new ArrayList<>();
        String query = "select * from utenti"; //ritorna la lista di tutti gli utenti
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                Utente utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setUsername(rs.getString("username"));

                utenti.add(utente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    @Override
    public Utente findByPrimaryKey(Long id) {    //ritorna l'utente attraverso il suo ID
        Utente utente = null;
        String query = "select * from utenti where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    @Override
    public void saveOrUpdate(Utente utente) {
        if(findByPrimaryKey(utente.getId()) == null){
            String insertStr = "INSERT INTO utenti VALUES (?,?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);
                Long newId = IdBroker.getId(conn);

                st.setLong(1,newId);
                st.setString(2,utente.getUsername());
                st.setString(3,utente.getNome());
                st.setString(4,utente.getCognome());
                st.setString(5,utente.getEmail());
                st.setString(6,utente.getPassword());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE utenti set username = ?," +
                    "nome = ?," +
                    "cognome = ?," +
                    "email = ?," +
                    "password = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,utente.getUsername());
                st.setString(2,utente.getNome());
                st.setString(3,utente.getCognome());
                st.setString(4,utente.getEmail());
                st.setString(5,utente.getPassword());
                st.setLong(6,utente.getId());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) { //delete utente attraverso id
        String query = "DELETE FROM utenti where id = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
