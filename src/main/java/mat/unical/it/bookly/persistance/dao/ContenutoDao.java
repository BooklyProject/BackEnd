package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Contenuto;
import mat.unical.it.bookly.persistance.model.Libro;

import java.util.List;

public interface ContenutoDao {

    public List<Libro> findBooksForCollection(Long idRaccolta);
    public Contenuto findByPrimaryKey(Long idRaccolta, String ISBNLibro);
    public void save(Long idRaccolta, String ISBNLibro);
    public void delete(Long idRaccolta, String ISBNLibro);
    public void deleteBooksForCollections(Long idRaccolta);
}
