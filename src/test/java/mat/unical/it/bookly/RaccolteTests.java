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
        List<Raccolta> raccolte = DBManager.getInstance().getRaccoltaDao().findAllForUser(Long.valueOf(4));
        for(Raccolta raccolta: raccolte){
            assertNotNull(raccolta.getNome());
            System.out.println(raccolta.getNome());
        }

    }
}
