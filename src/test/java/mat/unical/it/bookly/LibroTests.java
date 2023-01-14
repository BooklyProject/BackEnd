package mat.unical.it.bookly;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Amministratore;
import mat.unical.it.bookly.persistance.model.Libro;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LibroTests {

    @Test
    void contextLoads(){

    }

    @Test
    public void testFindAll(){
        List<Libro> libri = DBManager.getInstance().getLibroDao().findAll();
        for(Libro libro: libri){
            assertNotNull(libro.getNome());
            System.out.println(libro.getNome());
        }
    }

    @Test
    public void testFindByPrimaryKey(){
        Libro libro = DBManager.getInstance().getLibroDao().findByPrimaryKey("1234");
        assertNotNull(libro.getIsbn());
        System.out.println(libro.getNome());
    }

    @Test
    public void testDelete(){
        DBManager.getInstance().getLibroDao().delete("1234");
    }

    @Test
    public void testSaveUpdate(){
        //TODO: da provare update
        Libro l = new Libro();
        l.setIsbn("1235");
        l.setLingua("inglese");
        l.setNome("Robison Crusoe");
        l.setNumeroPagine(700);
        l.setAutore("Martin Garrix");
        l.setGeneri("Fantasia");
        DBManager.getInstance().getLibroDao().saveOrUpdate(l);

    }


}
