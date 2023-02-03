package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Contenuto;
import mat.unical.it.bookly.persistance.model.Libro;

import java.util.List;

public interface ContenutoDao {

    List<Libro> findBooksForCollection(Long idRaccolta);
    Contenuto findByPrimaryKey(Long idRaccolta, String ISBNLibro);
    void save(Long idRaccolta, String ISBNLibro);
    void delete(Long idRaccolta, String ISBNLibro);
    void deleteBooksForCollections(Long idRaccolta);
}
