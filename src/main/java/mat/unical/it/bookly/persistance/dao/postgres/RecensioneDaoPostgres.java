package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.RecensioneDao;
import mat.unical.it.bookly.persistance.model.Post;
import mat.unical.it.bookly.persistance.model.Recensione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecensioneDaoPostgres implements RecensioneDao {

    Connection conn;
    public RecensioneDaoPostgres(Connection conn){this.conn = conn;};

    @Override
    public List<Recensione> findAllWroteByUser(Long idUtente) {
        List<Recensione> recensioniUtente = new ArrayList<>();
        String query = "SELECT * FROM recensioni r WHERE EXISTS(SELECT * FROM post p WHERE p.id = r.id AND p.utente = ?)";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,idUtente);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Recensione recensione = new Recensione();
                recensione.setId(rs.getLong("id"));
                recensione.setDescrizione(rs.getString("descrizione"));
                recensione.setVoto(rs.getInt("voto"));
                recensione.setNumeroMiPiace(rs.getInt("mi_piace"));
                recensione.setNumeroNonMiPiace(rs.getInt("non_mi_piace"));
                recensione.setLibro(rs.getString("libro"));

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
        String query1 = "select * from recensioni r where r.libro = ? AND EXISTS(SELECT * FROM post p WHERE p.id = r.id AND p.utente = ?)";
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
                r.setNumeroMiPiace(rs.getInt("mi_piace"));
                r.setNumeroNonMiPiace(rs.getInt("non_mi_piace"));
                r.setLibro(rs.getString("libro"));

                recensioni.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query2 = "select * from recensioni r where r.libro = ? AND EXISTS(SELECT * FROM post p WHERE p.id = r.id AND p.utente != ?)";
        try {
            PreparedStatement st2 = conn.prepareStatement(query2);
            st2.setString(1, ISBNLibro);
            st2.setLong(2, idUtente);
            ResultSet rs1 = st2.executeQuery();

            while (rs1.next()) {
                Recensione r = new Recensione();
                r.setId(rs1.getLong("id"));
                r.setDescrizione(rs1.getString("descrizione"));
                r.setVoto(rs1.getInt("voto"));
                r.setNumeroMiPiace(rs1.getInt("mi_piace"));
                r.setNumeroNonMiPiace(rs1.getInt("non_mi_piace"));
                r.setLibro(rs1.getString("libro"));

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
        String query = "SELECT * FROM libri l WHERE EXISTS ( SELECT * FROM recensioni r WHERE l.isbn = r.libro AND EXISTS( SELECT * FROM post p WHERE r.id = p.id AND EXISTS(SELECT * FROM utenti u WHERE p.utente = u.id AND u.id = ?)))";
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
    public Long saveOrUpdate(Recensione recensione, Long idUtente) {
        Long idR = null;
        if(recensione.getId() == null || findByPrimaryKey(recensione.getId()) == null){

            Post p = new Post();
            idR = IdBroker.getId(conn);
            p.setId(idR);
            p.setIdUtente(idUtente);
            p.setTipologia("recensione");
            //System.out.println("tipologia: " + p.getTipologia());
            DBManager.getInstance().getPostDao().saveUpdate(p);
            String insertStr = "INSERT INTO recensioni VALUES (?,?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);

                st.setLong(1,idR);
                st.setString(2,recensione.getDescrizione());
                st.setInt(3,recensione.getVoto());
                st.setInt(4,recensione.getNumeroMiPiace());
                st.setInt(5,recensione.getNumeroNonMiPiace());
                st.setString(6,recensione.getLibro());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            idR = recensione.getId();
            String updateStr = "UPDATE recensioni set descrizione = ?," +
                    "voto = ?," +
                    "mi_piace = ?," +
                    "non_mi_piace = ?," +
                    "libro = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,recensione.getDescrizione());
                st.setInt(2,recensione.getVoto());
                st.setInt(3,recensione.getNumeroMiPiace());
                st.setInt(4,recensione.getNumeroNonMiPiace());
                st.setString(5,recensione.getLibro());
                st.setLong(6,recensione.getId());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idR;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM recensioni where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
            DBManager.getInstance().getPostDao().delete(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
