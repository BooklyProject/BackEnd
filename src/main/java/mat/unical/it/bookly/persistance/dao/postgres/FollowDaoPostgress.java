package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.FollowDao;
import mat.unical.it.bookly.persistance.model.Follow;
import mat.unical.it.bookly.persistance.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowDaoPostgress implements FollowDao {
    Connection conn;
    public FollowDaoPostgress(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Utente> followList(Long id) {
        List<Utente> utenti = new ArrayList<>();
        String query = "select utente2 from segue where utente1 = ?"; //ritorna la lista di tutti gli utenti
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(rs.getLong("utente2"));
                utenti.add(utente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    @Override
    public Follow singleFollow(Long utente1, Long utente2) {
        Follow follow = null;
        String query = "select * from segue where utente1 = ? and utente2 = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,utente1);
            st.setLong(2,utente2);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                follow = new Follow();
                follow.setUtente1(rs.getLong("utente1"));
                follow.setUtente2(rs.getLong("utente2"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return follow;
    }

    @Override
    public void createFollow(Long utente1, Long utente2) {
        if(singleFollow(utente1, utente2) == null){
            String insertStr = "INSERT INTO segue VALUES (?,?)";
            PreparedStatement st;


            try{
                st = conn.prepareStatement(insertStr);

                st.setLong(1,utente1);
                st.setLong(2,utente2);

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("già presente questo follow");
        }
    }

    @Override
    public void deleteFollow(Long utente1, Long utente2) {
        String query = "DELETE FROM segue where utente1 = ? and utente2 = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,utente1);
            st.setLong(2,utente2);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

