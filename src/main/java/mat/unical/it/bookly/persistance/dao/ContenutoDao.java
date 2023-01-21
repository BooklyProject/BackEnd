package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Contenuto;

public interface ContenutoDao {

    public Contenuto findByPrimaryKey(Long idRaccolta, String ISBNLibro);

    public void save(Contenuto contenuto);

    public void delete(Long idRaccolta, String ISBNLibro);
}
