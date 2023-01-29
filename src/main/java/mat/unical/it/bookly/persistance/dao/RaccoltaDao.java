package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Raccolta;
import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface RaccoltaDao {
    public List<Raccolta> findAllForUser(Long idUtente);
    public List<Raccolta> findAll();
    public Raccolta findByPrimaryKey(Long id);
    public Long saveOrUpdate(Raccolta raccolta);
    public void delete(Long id);
    public void deleteAllForUser(Long idUtente);
}
