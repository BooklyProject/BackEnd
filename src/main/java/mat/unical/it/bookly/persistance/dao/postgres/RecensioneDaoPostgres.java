package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.RecensioneDao;
import mat.unical.it.bookly.persistance.model.Post;
import mat.unical.it.bookly.persistance.model.Recensione;

import java.awt.image.DataBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDaoPostgres implements RecensioneDao {

    Connection conn;
    public RecensioneDaoPostgres(Connection conn){this.conn = conn;};

    @Override
    public List<Recensione> findAllWroteByUser(Long idUtente) {
        List<Recensione> recensioniUtente = new ArrayList<>();
        String query = "SELECT r.id as r_id, r.descrizione as r_desc, r.voto as r_voto, r.data as r_data, r.mi_piace as r_mi," +
                "r.non_mi_piace as r_no, r.libro as r_libro " +
                        "FROM recensioni r " +
                        "JOIN post p ON p.id = r.id " +
                "JOIN utenti u ON p.utente = u.id " +
                "where p.utente = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idUtente);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Recensione recensione = new Recensione();
                recensione.setId(rs.getLong("r_id"));
                recensione.setDescrizione(rs.getString("r_desc"));
                recensione.setVoto(rs.getInt("r_voto"));
                recensione.setData(rs.getDate("r_data"));
                recensione.setNumeroMiPiace(rs.getInt("r_mi"));
                recensione.setNumeroNonMiPiace(rs.getInt("r_no"));
                recensione.setLibro(rs.getString("r_libro"));

                recensioniUtente.add(recensione);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensioniUtente;
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
        return r;

    }

    @Override
    public Long findUserByReview(Long id) {

        String query = "SELECT * FROM post p WHERE p.id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getLong("utente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Recensione> findReviewsByBook(Long idUtente, String ISBNLibro) {
        List<Recensione> recensioni = new ArrayList<>();
        String query1 = "select * from recensioni r where r.libro = ? AND EXISTS(SELECT * FROM post p WHERE p.id = r.id AND EXISTS(SELECT * FROM utenti u WHERE p.utente = u.id AND u.id = ?))";
        try {
            PreparedStatement st = conn.prepareStatement(query1);
            st.setString(1, ISBNLibro);
            st.setLong(2, idUtente);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Recensione r = new Recensione();
                r.setId(rs.getLong("id"));
                r.setDescrizione(rs.getString("descrizione"));
                r.setVoto(rs.getInt("voto"));
                r.setData(rs.getDate("data"));
                r.setNumeroMiPiace(rs.getInt("mi_piace"));
                r.setNumeroNonMiPiace(rs.getInt("non_mi_piace"));
                r.setLibro(rs.getString("libro"));

                recensioni.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query2 = "select * from recensioni r where r.libro = ? AND EXISTS(SELECT * FROM post p WHERE p.id = r.id AND EXISTS(SELECT * FROM utenti u WHERE p.utente = u.id AND u.id != ?))";
        try {
            PreparedStatement st = conn.prepareStatement(query2);
            st.setString(1, ISBNLibro);
            st.setLong(2, idUtente);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Recensione r = new Recensione();
                r.setId(rs.getLong("id"));
                r.setDescrizione(rs.getString("descrizione"));
                r.setVoto(rs.getInt("voto"));
                r.setData(rs.getDate("data"));
                r.setNumeroMiPiace(rs.getInt("mi_piace"));
                r.setNumeroNonMiPiace(rs.getInt("non_mi_piace"));
                r.setLibro(rs.getString("libro"));

                recensioni.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return recensioni;
    }

    @Override
    public String findPreferredResultByAttribute(Long idUtente, String attribute) {
        List<String> listaAttributi = new ArrayList<>();
        String query = "SELECT * FROM recensioni r" +
                "WHERE EXISTS ( SELECT * FROM post p WHERE r.id = p.id AND EXISTS(SELECT * FROM utenti u WHERE p.utente = u.id AND u.id = ?))";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idUtente);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                listaAttributi.add(rs.getString(attribute));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(listaAttributi.isEmpty()){
            return "Nessuno";
        }
        else {
            int maxcount = 0;
            String element_having_max_freq = "";
            for (int i = 0; i < listaAttributi.size(); i++) {
                int count = 0;
                for (int j = 0; j < listaAttributi.size(); j++) {
                    if (listaAttributi.get(i) == listaAttributi.get(j)) {
                        count++;
                    }
                }

                if (count > maxcount) {
                    maxcount = count;
                    element_having_max_freq = listaAttributi.get(i);
                }
            }
            return element_having_max_freq;
        }
    }

    @Override
    public void saveOrUpdate(Recensione recensione) {
        if(findByPrimaryKey(recensione.getId()) == null){
            //
            Post p = new Post();
            Long id = IdBroker.getId(conn);
            p.setId(id);
            p.setIdUtente(Long.valueOf(30));
            DBManager.getInstance().getPostDao().saveUpdate(p);
            String insertStr = "INSERT INTO recensioni VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);

                st.setLong(1,id);
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
        String query = "DELETE FROM recensioni where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
