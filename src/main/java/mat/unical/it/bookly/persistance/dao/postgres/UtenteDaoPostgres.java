package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.Raccolta;
import mat.unical.it.bookly.persistance.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDaoPostgres implements UtenteDao {
    Connection conn;

    public UtenteDaoPostgres(Connection conn) {  //attraverso il costruttore settiamo l'oggetto di tipo Connection
        this.conn = conn;
    }

    @Override
    public List<Utente> findAll() {
        List<Utente> utenti = new ArrayList<>();
        String query = "select * from utenti"; //ritorna la lista di tutti gli utenti
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setUsername(rs.getString("username"));
                utente.setUserImage(rs.getString("user_image"));
                utente.setBanned(rs.getBoolean("is_banned"));
                utente.setResetPasswordToken(rs.getString("reset_password_token"));

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
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setUsername(rs.getString("username"));
                utente.setUserImage(rs.getString("user_image"));
                utente.setBanned(rs.getBoolean("is_banned"));
                utente.setResetPasswordToken(rs.getString("reset_password_token"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    @Override
    public Utente findByEmail(String email) {
        Utente utente = null;
        String query = "SELECT * FROM utenti where email = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setUsername(rs.getString("username"));
                utente.setUserImage(rs.getString("user_image"));
                utente.setBanned(rs.getBoolean("is_banned"));
                utente.setResetPasswordToken(rs.getString("reset_password_token"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return utente;
    }

    @Override
    public void saveOrUpdate(Utente utente) {
        utente.setBanned(false);
        if (findByEmail(utente.getEmail()) == null) {
            String insertStr = "INSERT INTO utenti VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement st;

            try {
                st = conn.prepareStatement(insertStr);
                Long newId = IdBroker.getId(conn);
                System.out.println("banned: " + utente.getUsername());
                st.setLong(1, IdBroker.getId(conn));
                st.setString(2, utente.getUsername());
                st.setString(3, utente.getNome());
                st.setString(4, utente.getCognome());
                st.setString(5, utente.getEmail());
                st.setString(6, utente.getPassword());
                st.setString(7, utente.getUserImage());
                st.setBoolean(8, utente.getBanned());
                st.setString(9, utente.getResetPasswordToken());
                st.executeUpdate();
                /*
                Raccolta raccolta1 = new Raccolta();
                Raccolta raccolta2 = new Raccolta();
                raccolta1.setNome("Preferiti");
                raccolta1.setUtente(newId);
                raccolta2.setNome("Da leggere");
                raccolta2.setUtente(newId);
                DBManager.getInstance().getRaccoltaDao().saveOrUpdate(raccolta1);
                DBManager.getInstance().getRaccoltaDao().saveOrUpdate(raccolta2);

                 */

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateStr = "UPDATE utenti set username = ?," +
                    "nome = ?," +
                    "cognome = ?," +
                    "email = ?," +
                    "password = ?," +
                    "user_image = ?," +
                    "is_banned = ?," +
                    "reset_password_token = ?" +
                    "where id = ?";
            PreparedStatement st;
            try {
                st = conn.prepareStatement(updateStr);
                st.setString(1, utente.getUsername());
                st.setString(2, utente.getNome());
                st.setString(3, utente.getCognome());
                st.setString(4, utente.getEmail());
                st.setString(5, utente.getPassword());
                st.setString(6, utente.getUserImage());
                st.setBoolean(7, utente.getBanned());
                st.setString(8, utente.getResetPasswordToken());
                st.setLong(9, utente.getId());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) { //delete utente attraverso id
        String query = "DELETE FROM utenti where id = ?";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utente findByEmailAndPassword(String email, String password) {
        Utente utente = null;
        String query = "SELECT * from utenti where email = ? and password = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setUsername(rs.getString("username"));
                utente.setUserImage(rs.getString("user_image"));
                utente.setBanned(rs.getBoolean("is_banned"));
                utente.setResetPasswordToken(rs.getString("reset_password_token"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    @Override
    public Utente findByToken(String token){
        Utente utente = null;
        String query = "SELECT * from utenti where reset_password_token = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNome(rs.getString("nome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utente.setUsername(rs.getString("username"));
                utente.setUserImage(rs.getString("user_image"));
                utente.setBanned(rs.getBoolean("is_banned"));
                utente.setResetPasswordToken(rs.getString("reset_password_token"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }
}
