package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Evento;

import java.util.List;

public interface EventoDao {

    List<Evento> findAll();
    List<Evento> findAllCreatedByUser(Long idUtente);
    Evento findByPrimaryKey(Long id);
    void saveOrUpdate(Evento evento, Long idUtente);
    void delete(Long id);
    List<Evento> findAvailableEvents(Long idUtente);
}
