package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.PartecipaDao;
import mat.unical.it.bookly.persistance.model.Evento;
import mat.unical.it.bookly.persistance.model.Partecipazione;
import mat.unical.it.bookly.persistance.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PartecipaDaoPostgress implements PartecipaDao {
    Connection conn;
    public PartecipaDaoPostgress(Connection conn){
        this.conn = conn;
    }


    @Override
    public List<Utente> usersToEvent(Long evento) {
        List<Utente> utenti = new ArrayList<>();
        String query = "SELECT utente from partecipa where evento = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,evento);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getLong("utente"));
                utenti.add(utente);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    @Override
    public void createPartecipation(Long utente, Long evento) {
        if(singlePartecipation(utente, evento) == null){
            String insertStr = "INSERT INTO partecipa VALUES (?,?)";
            PreparedStatement st;

            try{
                st = conn.prepareStatement(insertStr);

                st.setLong(1,utente);
                st.setLong(2,evento);

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("già presente questo evento");
        }
    }

    @Override
    public Partecipazione singlePartecipation(Long utente, Long evento) {
        Partecipazione partecipazione = null;
        String query = "SELECT * from partecipa where utente = ? and evento = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,utente);
            st.setLong(2,evento);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                partecipazione = new Partecipazione();
                partecipazione.setUtente(rs.getLong("utente"));
                partecipazione.setEvento(rs.getLong("evento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partecipazione;
    }

    @Override
    public void deletePartecipation(Long utente, Long evento) {
        String query = "DELETE FROM partecipa where utente = ? and evento = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,utente);
            st.setLong(2,evento);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Evento> eventFromUserList(Long utente) {
        List<Evento> eventi = new ArrayList<>();
        String query = "select evento from partecipa where utente = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,utente);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Evento evento = DBManager.getInstance().getEventoDao().findByPrimaryKey(rs.getLong("evento"));
                eventi.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventi;
    }

    @Override
    public void deleteAllEventPartecipations(Long evento) {
        String query = "DELETE FROM partecipa where evento = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,evento);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
