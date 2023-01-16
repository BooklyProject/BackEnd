package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface UtenteDao {
    public List<Utente> findAll();
    public Utente findByPrimaryKey(Long id);
    public Utente findByEmail(String email);
    public void saveOrUpdate(Utente utente);
    public void delete(Long id);
}
