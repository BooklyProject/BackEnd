package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Commento;
import mat.unical.it.bookly.persistance.model.Evento;

import java.util.List;

public interface EventoDao {

    public List<Evento> findAll();
    public List<Evento> findAllCreatedByUser(Long idUtente);
    public Evento findByPrimaryKey(Long id);
    public void saveOrUpdate(Evento evento, Long idUtente);
    public void delete(Long id);
}
