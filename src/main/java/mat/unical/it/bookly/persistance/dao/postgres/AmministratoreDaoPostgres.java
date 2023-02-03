package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.AmministratoreDao;
import mat.unical.it.bookly.persistance.model.Amministratore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmministratoreDaoPostgres implements AmministratoreDao {

    Connection conn;
    public AmministratoreDaoPostgres(Connection conn){  //attraverso il costruttore settiamo l'oggetto di tipo Connection
        this.conn = conn;
    }

    @Override
    public List<Amministratore> findAll() {
        List<Amministratore> amministratori = new ArrayList<>();
        String query = "select * from amministratori";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                Amministratore amministratore = new Amministratore();
                amministratore.setId(rs.getLong("id"));
                amministratore.setNome(rs.getString("nome"));
                amministratore.setCognome(rs.getString("cognome"));
                amministratore.setEmail(rs.getString("email"));
                amministratore.setPassword(rs.getString("password"));

                amministratori.add(amministratore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amministratori;
    }


    @Override
    public Amministratore findByPrimaryKey(Long id) {
        Amministratore amministratore = null;
        String query = "select * from amministratori where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                amministratore = new Amministratore();
                amministratore.setId(rs.getLong("id"));
                amministratore.setNome(rs.getString("nome"));
                amministratore.setCognome(rs.getString("cognome"));
                amministratore.setEmail(rs.getString("email"));
                amministratore.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amministratore;
    }

    @Override
    public Amministratore findByEmail(String email) {
        Amministratore a = null;
        String query = "SELECT * FROM amministratori where email = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                a = new Amministratore();
                a.setId(rs.getLong("id"));
                a.setCognome(rs.getString("cognome"));
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));
                a.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return a;
    }

    @Override
    public Integer findAdministratorsNum() {
        Integer count = 0;
        String query = "select count(*) from amministratori";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    @Override
    public void saveOrUpdate(Amministratore amministratore) {
        if(findByPrimaryKey(amministratore.getId()) == null){
            String insertStr = "INSERT INTO amministratori VALUES (?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);
                Long newId = IdBroker.getId(conn);

                st.setLong(1,newId);
                st.setString(2,amministratore.getNome());
                st.setString(3,amministratore.getCognome());
                st.setString(4,amministratore.getEmail());
                st.setString(5,amministratore.getPassword());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE amministratori set nome = ?," +
                    "cognome = ?," +
                    "email = ?," +
                    "password = ?" +
                    "where id = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,amministratore.getNome());
                st.setString(2,amministratore.getCognome());
                st.setString(3,amministratore.getEmail());
                st.setString(4,amministratore.getPassword());
                st.setLong(5,amministratore.getId());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM amministratori where id = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
