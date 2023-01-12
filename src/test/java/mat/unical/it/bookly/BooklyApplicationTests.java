package mat.unical.it.bookly;

import ch.qos.logback.core.net.SyslogOutputStream;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BooklyApplicationTests {

    @Test
    void contextLoads() {
    }


    //test utenti
    @Test
    public void testUtenteChiavePrimaria(){
        Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(Long.valueOf(3));
        assertNotNull(utente.getId());
        System.out.println(utente.getUsername());
    }


    @Test
    public void testCancellazioneUtente() {
        DBManager.getInstance().getUtenteDao().delete(Long.valueOf(1));
    }

    @Test
    public void testSaveUpdateTest(){
        Utente u = new Utente();
        u.setUsername("Checco2222");
        u.setNome("Francesco");
        u.setEmail("chifdeho@gmail.coem");
        u.setPassword("ciao");
        u.setCognome("Strangis");
        u.setId(Long.valueOf(15));
        DBManager.getInstance().getUtenteDao().saveOrUpdate(u);
    }

    @Test
    public void testUtenti(){
        List<Utente> utenti = DBManager.getInstance().getUtenteDao().findAll();
        for(Utente utente: utenti){
            assertNotNull(utente.getUsername());
            System.out.println(utente.getUsername());
        }

    }

}
