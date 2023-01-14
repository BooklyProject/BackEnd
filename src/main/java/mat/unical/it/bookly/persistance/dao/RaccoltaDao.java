package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Raccolta;
import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface RaccoltaDao {
    public List<Raccolta> findAllForUser(Long idUtente);
    public Raccolta findByPrimaryKey(Long id);
    public void saveOrUpdate(Raccolta raccolta);
    public void delete(Long id);
}
