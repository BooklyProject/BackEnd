package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Post;

public interface PostDao {
    void saveUpdate(Post post);
    Post findByPrimaryKey(Long id);
    void delete(Long id);

}
