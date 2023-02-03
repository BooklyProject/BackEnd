package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Libro;

import java.util.List;

public interface LibroDao {
    List<Libro> findAll();
    Libro findByPrimaryKey(String isbn);
    void saveOrUpdate(Libro libro);
    void delete(String isbn);
}
