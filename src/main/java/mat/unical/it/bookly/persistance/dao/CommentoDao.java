package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Commento;

import java.util.List;

public interface CommentoDao {
    List<Commento> findAllWroteByUser(Long idUtente);
    Commento findByPrimaryKey(Long id);
    Long findUserByComment(Long id);
    List<Commento> findByReview(Long idRecensione);
    Long saveOrUpdate(Commento commento, Long idUtente);
    void delete(Long id);
    void deleteForReview(Long idRecensione);

}
