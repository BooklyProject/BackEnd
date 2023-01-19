package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Raccolta;
import mat.unical.it.bookly.persistance.model.Segnalazione;

import java.util.List;

public interface SegnalazioneDao {

    public List<Segnalazione> findAll();
    public Segnalazione findByPrimaryKey(Long id);
    public void saveOrUpdate(Segnalazione segnalazione);
    public List<Segnalazione> findByAdministrator(Long amministratore);
    public void delete(Long id);
}
