package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface UtenteDao {
    List<Utente> findAll();
    Utente findByPrimaryKey(Long id);
    Utente findByEmail(String email);
    void saveOrUpdate(Utente utente);
    void delete(Long id);
    Utente findByEmailAndPassword(String email,String password);
    Utente findByToken(String token);
}
