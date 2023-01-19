package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Amministratore;
import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface AmministratoreDao {
    public List<Amministratore> findAll();
    public Amministratore findByPrimaryKey(Long id);
    public int findAdministratorsNum();
    public void saveOrUpdate(Amministratore amministratore);
    public void delete(Long id);
}
