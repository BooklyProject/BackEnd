package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.ValutazioneCommento;
import mat.unical.it.bookly.persistance.model.ValutazioneRecensione;

import java.sql.SQLException;

public interface ValutazioneCommentoDao {

    public ValutazioneCommento findByPrimaryKey(Long idCommento, Long idUtente);
    public void saveOrUpdate(ValutazioneCommento v) throws SQLException;
    public void delete(Long idCommento, Long idUtente);
    public void deleteForComment(Long idCommento);
}
