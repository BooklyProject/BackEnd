package mat.unical.it.bookly;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Amministratore;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AmministratoreTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void testAmministratoreChiavePrimaria(){
        Amministratore amministratore = DBManager.getInstance().getAmministratoreDao().findByPrimaryKey(Long.valueOf(1));
        assertNotNull(amministratore.getId());
        System.out.println(amministratore.getNome());
    }

    @Test
    public void testSaveUpdateTest(){
        Amministratore a = new Amministratore();
        a.setNome("Marco");
        a.setCognome("Rossi");
        String password = "password";
        String hashed = BCrypt.hashpw(password,BCrypt.gensalt(12));
        a.setPassword(hashed);
        a.setEmail("booklyproject2023@gmail.com");
        a.setId(Long.valueOf(1));
        DBManager.getInstance().getAmministratoreDao().saveOrUpdate(a);
    }

    @Test
    public void testCancellazioneAmministratore(){
        DBManager.getInstance().getAmministratoreDao().delete(Long.valueOf(1));
    }

    @Test
    public void testTrovaNumeroAmministratore(){
        int count = DBManager.getInstance().getAmministratoreDao().findAdministratorsNum();
        System.out.println(count);
        System.out.println(RandomUtils.nextLong( 1, DBManager.getInstance().getAmministratoreDao().findAdministratorsNum() + 1) );
    }

    @Test
    public void testAmministratori() {
        List<Amministratore> amministratori = DBManager.getInstance().getAmministratoreDao().findAll();
        for (Amministratore amministratore : amministratori) {
            assertNotNull(amministratore.getNome());
            System.out.println(amministratore.getNome());
        }
    }



}
