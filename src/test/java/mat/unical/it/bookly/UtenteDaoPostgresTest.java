package mat.unical.it.bookly;

import jakarta.xml.bind.SchemaOutputResolver;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UtenteDaoPostgresTest {

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
        DBManager.getInstance().getUtenteDao().delete(Long.valueOf(239));
    }

    @Test
    public void testSaveUpdateTest(){
        Utente u = new Utente();
        u.setUsername("dottore");
        u.setNome("Antonio");
        u.setEmail("mobydick");
        u.setPassword("ciao");
        u.setCognome("Romano");
        u.setId(Long.valueOf(15));
        u.setUserImage("image");
        u.setBanned(true);
        //u.setProvider(Provider.LOCAL);
        DBManager.getInstance().getUtenteDao().saveOrUpdate(u);
    }

    @Test
    public void testFindByEmailAndPassword(){
        Utente utente = DBManager.getInstance().getUtenteDao().findByEmailAndPassword("checco0110@gmail.com","sooca");
        assertNotNull(utente.getId());
        System.out.println(utente.getNome());
    }


    @Test
    public void testUtenti(){
        List<Utente> utenti = DBManager.getInstance().getUtenteDao().findAll();
        for(Utente utente: utenti){
            assertNotNull(utente.getUsername());
            System.out.println(utente);
        }
    }





    //TODO: fare in modo che una volta che viene cancellato un utente, vengono cancellate tutte le raccolte ad esso associate

}
