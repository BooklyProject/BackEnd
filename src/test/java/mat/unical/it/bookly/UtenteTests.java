package mat.unical.it.bookly;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Provider;
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
        Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(Long.valueOf(11));
        assertNotNull(utente.getId());
        System.out.println(utente.getUsername());
    }


    @Test
    public void testCancellazioneUtente() {
        DBManager.getInstance().getUtenteDao().delete(Long.valueOf(38));
    }

    @Test
    public void testSaveUpdateTest(){
        Utente u = new Utente();
        u.setUsername("jacopo");
        u.setNome("Jacopino");
        u.setEmail("femifoef");
        u.setPassword("ifoe");
        u.setCognome("Garofalo");
        u.setId(Long.valueOf(15));
        u.setUserImage("image");
        u.setBanned(true);
        u.setProvider(Provider.LOCAL);
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


    //TODO: fare in modo che una volta che viene cancellato un utente, vengono cancellate tutte le raccolte ad esso associate

}
