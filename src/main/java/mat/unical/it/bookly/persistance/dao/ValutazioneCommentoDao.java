package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.ValutazioneCommento;

import java.sql.SQLException;

public interface ValutazioneCommentoDao {

    ValutazioneCommento findByPrimaryKey(Long idCommento, Long idUtente);
    void saveOrUpdate(ValutazioneCommento v) throws SQLException;
    void delete(Long idCommento, Long idUtente);
    void deleteForComment(Long idCommento);
}
