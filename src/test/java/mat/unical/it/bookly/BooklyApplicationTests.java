package mat.unical.it.bookly;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BooklyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testUtenti(){
        Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(2);
        assertNotNull(utente.getId());
        System.out.println(utente.getUsername());
    }

}
