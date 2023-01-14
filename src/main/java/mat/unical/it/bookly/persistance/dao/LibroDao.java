package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Libro;
import mat.unical.it.bookly.persistance.model.Raccolta;

import java.util.List;

public interface LibroDao {
    public List<Libro> findAll();
    public Libro findByPrimaryKey(String isbn);
    public void saveOrUpdate(Libro libro);
    public void delete(String isbn);
}
