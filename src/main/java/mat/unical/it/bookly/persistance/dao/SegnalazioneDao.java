package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Segnalazione;

import java.util.List;

public interface SegnalazioneDao {
    List<Segnalazione> findAll();
    public Segnalazione findByPrimaryKey(Long id);
    void saveOrUpdate(Segnalazione segnalazione);
    List<Segnalazione> findByAdministrator(Long amministratore);
    void delete(Long id);
    void deleteForPost(Long idPost);
}
