package mat.unical.it.bookly;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UtenteTests {

    @Test
    void contextLoads() {
    }


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
        //TODO: TESTARE UPDATE --> è stato testata solo la insert
        Utente u = new Utente();
        u.setUsername("Strangis333343");
        u.setNome("Francesco4343");
        u.setEmail("mhhh443343");
        u.setPassword("ciao3");
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
