package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Commento;


import java.util.List;

public interface CommentoDao {
    public List<Commento> findAllWroteByUser(Long idUtente);
    public Commento findByPrimaryKey(Long id);
    public void saveOrUpdate(Commento recensione);
    public void delete(Long id);
}
