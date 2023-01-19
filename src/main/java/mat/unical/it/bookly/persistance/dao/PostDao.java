package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Post;

public interface PostDao {
    public void saveUpdate(Post post);
    public Post findByPrimaryKey(Long id);

    public void delete(Long id);
    //TODO: nota bene per chiunque legge. il Post DEVE avere obbligatoriamente
    //TODO: lo stesso id della recensione o evento o commento
}
