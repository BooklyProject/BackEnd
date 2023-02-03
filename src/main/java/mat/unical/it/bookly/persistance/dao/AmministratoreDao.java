package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Amministratore;

import java.util.List;

public interface AmministratoreDao {
    List<Amministratore> findAll();
    Amministratore findByPrimaryKey(Long id);
    Amministratore findByEmail(String email);
    Integer findAdministratorsNum();
    void saveOrUpdate(Amministratore amministratore);
    void delete(Long id);

}
