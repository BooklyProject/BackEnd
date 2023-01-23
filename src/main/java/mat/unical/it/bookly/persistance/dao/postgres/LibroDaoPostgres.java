package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.IdBroker;
import mat.unical.it.bookly.persistance.dao.LibroDao;
import mat.unical.it.bookly.persistance.model.Libro;
import mat.unical.it.bookly.persistance.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDaoPostgres implements LibroDao {
    Connection conn;
    public LibroDaoPostgres (Connection conn){this.conn = conn;}


    @Override
    public List<Libro> findAll() {
        List<Libro> libri = new ArrayList<>();
        String query = "select * from libri";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                Libro libro = new Libro();
                libro.setIsbn(rs.getString("isbn"));
                libro.setNome(rs.getString("nome"));
                libro.setAutore(rs.getString("autore"));
                libro.setGeneri(rs.getString("generi"));
                libro.setNumeroPagine(rs.getInt("num_pagine"));
                libro.setLingua(rs.getString("lingua"));
                libro.setDescrizione(rs.getString("descrizione"));
                libro.setCopertina("copertina");

                libri.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libri;
    }

    @Override
    public Libro findByPrimaryKey(String isbn) {
        Libro libro = null;
        String query = "select * from libri where isbn = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,isbn);
            ResultSet rs = st.executeQuery();

            if (rs.next()){
                libro = new Libro();
                libro.setIsbn(rs.getString("isbn"));
                libro.setNome(rs.getString("nome"));
                libro.setAutore(rs.getString("autore"));
                libro.setGeneri(rs.getString("generi"));
                libro.setNumeroPagine(rs.getInt("num_pagine"));
                libro.setLingua(rs.getString("lingua"));
                libro.setDescrizione(rs.getString("descrizione"));
                libro.setCopertina("copertina");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public void saveOrUpdate(Libro libro) {
        if(findByPrimaryKey(libro.getIsbn()) == null){
            String insertStr = "INSERT INTO libri VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(insertStr);
                st.setString(1,libro.getIsbn());
                st.setString(2,libro.getNome());
                st.setString(3,libro.getAutore());
                st.setString(4, libro.getGeneri());
                st.setInt(5,libro.getNumeroPagine());
                st.setString(6,libro.getLingua());
                st.setString(7,libro.getDescrizione());
                st.setString(8, libro.getCopertina());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String updateStr = "UPDATE libri set nome = ?," +
                    "autore = ?," +
                    "generi = ?," +
                    "num_pagine = ?," +
                    "lingua = ?," +
                    "descrizione = ?," +
                    "copertina = ?" +
                    "where isbn = ?";
            PreparedStatement st;
            try{
                st = conn.prepareStatement(updateStr);
                st.setString(1,libro.getNome());
                st.setString(2,libro.getAutore());
                st.setString(3,libro.getGeneri());
                st.setInt(4,libro.getNumeroPagine());
                st.setString(5,libro.getLingua());
                st.setString(6,libro.getDescrizione());
                st.setString(7, libro.getCopertina());
                st.setString(8,libro.getIsbn());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String isbn) {
        String query = "DELETE FROM libri where isbn = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1,isbn);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //TODO: fare query lista recensioni che ha un libro

}

