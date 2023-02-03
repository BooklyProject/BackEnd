package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.dao.PostDao;
import mat.unical.it.bookly.persistance.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDaoPostgres implements PostDao {
    Connection conn;
    public PostDaoPostgres(Connection conn){
        this.conn = conn;
    }


    @Override
    public void saveUpdate(Post post) {
        if(findByPrimaryKey(post.getId()) == null){
            String insertStr = "INSERT INTO post VALUES (?,?,?)";
            PreparedStatement st;
            try{
                //System.out.println("tipologiaaa: " + post.getTipologia());
                st = conn.prepareStatement(insertStr);
                st.setLong(1,post.getId());
                st.setLong(2,post.getIdUtente());
                st.setString(3,post.getTipologia());

                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Post findByPrimaryKey(Long id) {
        Post post = null;
        String query = "select * from post where id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                //System.out.println("tipologia db: " + rs.getString("tipologia"));
                post = new Post();
                post.setId(rs.getLong("id"));
                post.setIdUtente(rs.getLong("utente"));
                post.setTipologia(rs.getString("tipologia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM post where id = ?";

        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
