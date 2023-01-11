package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtenteDaoPostgres implements UtenteDao {
    Connection conn;

    public UtenteDaoPostgres(Connection conn){  //attraverso il costruttore settiamo l'oggetto di tipo Connection
        this.conn = conn;
    }

    @Override
    public List<Utente> findAll() {
        return null;
    }

    @Override
    public Utente findByPrimaryKey(Integer id) {
        Utente utente = null;
        String query = "select * from utenti where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                utente = new Utente();
                utente.setId(rs.getInt("id"));
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

    }

    @Override
    public void delete(Utente utente) {

    }
}
