package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Commento;


import java.util.List;

public interface CommentoDao {
    public List<Commento> findAllWroteByUser(Long idUtente);
    public Commento findByPrimaryKey(Long id);
    public Long findUserByComment(Long id);
    public List<Commento> findByReview(Long idRecensione);
    public void saveOrUpdate(Commento commento, Long idUtente);
    public void delete(Long id);
    public void deleteForReview(Long idRecensione);

}
