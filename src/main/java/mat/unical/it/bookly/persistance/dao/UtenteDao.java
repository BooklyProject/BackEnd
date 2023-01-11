package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface UtenteDao {
    public List<Utente> findAll();
    public Utente findByPrimaryKey(Integer id);
    public void saveOrUpdate(Utente utente);
    public void delete(Utente utente);
}
