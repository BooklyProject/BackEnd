package mat.unical.it.bookly;


import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Raccolta;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RaccolteTests {
    @Test
    void contextLoads(){

    }

    @Test
    public void testFindAllForUser(){
        List<Raccolta> raccolte = DBManager.getInstance().getRaccoltaDao().findAllForUser(Long.valueOf(11));
        for(Raccolta raccolta: raccolte){
            assertNotNull(raccolta.getNome());
            System.out.println(raccolta.getNome());
        }

    }

    @Test
    public void testFindByPrimaryKey(){
        Raccolta raccolta = DBManager.getInstance().getRaccoltaDao().findByPrimaryKey(Long.valueOf(3));
        assertNotNull(raccolta.getId());
        System.out.println(raccolta.getNome());
        //prova per stampare il nome dell'utente a cui è associata la raccolta
        Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(raccolta.getUtente());
        assertNotNull(utente.getUsername());
        System.out.println(utente.getNome());
    }

    @Test
    public void testSaveOrUpdate(){
        Raccolta r = new Raccolta();
        r.setNome("Libri preferiti");
        r.setId(Long.valueOf(99));
        r.setUtente(Long.valueOf(11));
        DBManager.getInstance().getRaccoltaDao().saveOrUpdate(r);
    }

    @Test
    public void testDelete(){
        DBManager.getInstance().getRaccoltaDao().delete(Long.valueOf(1));
    }


}
