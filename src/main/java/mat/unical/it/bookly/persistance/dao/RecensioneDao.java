package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Libro;
import mat.unical.it.bookly.persistance.model.Recensione;

import java.util.List;

public interface RecensioneDao {
    public List<Recensione> findAllWroteByUser(Long idUtente);
    public Recensione findByPrimaryKey(Long id);
    public Long findUserByReview(Long id);
    public List<Recensione> findReviewsByBook(Long idUtente, String ISBNBook);
    public String findPreferredResultByAttribute(Long idUtente, String attribute);
    public Long saveOrUpdate(Recensione recensione, Long idUtente);
    public void delete(Long id);
}
