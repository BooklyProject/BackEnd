package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.ValutazioneRecensione;

public interface ValutazioneRecensioneDao {

    ValutazioneRecensione findByPrimaryKey(Long idRecensione, Long idUtente);
    void saveOrUpdate(ValutazioneRecensione v);
    void delete(Long idRecensione, Long idUtente);
    void deleteForReview(Long idRecensione);
}
