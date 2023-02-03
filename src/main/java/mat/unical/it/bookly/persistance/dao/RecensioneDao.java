package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Recensione;

import java.util.List;

public interface RecensioneDao {
    List<Recensione> findAllWroteByUser(Long idUtente);
    Recensione findByPrimaryKey(Long id);
    Long findUserByReview(Long id);
    List<Recensione> findReviewsByBook(Long idUtente, String ISBNBook);
    String findPreferredResultByAttribute(Long idUtente, String attribute);
    Long saveOrUpdate(Recensione recensione, Long idUtente);
    void delete(Long id);
}
