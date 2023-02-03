package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Raccolta;

import java.util.List;

public interface RaccoltaDao {
    List<Raccolta> findAllForUser(Long idUtente);
    List<Raccolta> findAll();
    Raccolta findByPrimaryKey(Long id);
    Long saveOrUpdate(Raccolta raccolta);
    void delete(Long id);
}
