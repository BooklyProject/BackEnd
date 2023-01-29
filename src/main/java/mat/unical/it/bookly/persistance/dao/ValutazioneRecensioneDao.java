package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Contenuto;
import mat.unical.it.bookly.persistance.model.ValutazioneRecensione;

public interface ValutazioneRecensioneDao {

    public ValutazioneRecensione findByPrimaryKey(Long idRecensione, Long idUtente);
    public void saveOrUpdate(ValutazioneRecensione v);
    public void delete(Long idRecensione, Long idUtente);
}
