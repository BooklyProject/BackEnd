package mat.unical.it.bookly;


import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Recensione;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RecensioneTests {

    @Test
    void contextLoads(){

    }

    @Test
    public void testSaveUpdate(){
        Recensione r = new Recensione();
        r.setId(Long.valueOf(99));
        r.setDescrizione("Questo libro è bellissimo xd, Hitler vita mia");
        r.setVoto(9);
        r.setData(Date.valueOf("2021-12-12"));
        r.setLibro("1235");
        DBManager.getInstance().getRecensioneDao().saveOrUpdate(r);
    }

    @Test
    public void testFindAllWroteByUser(){
        List<Recensione> recensioni = DBManager.getInstance().getRecensioneDao().findAllWroteByUser(Long.valueOf(23));
        for(Recensione recensione: recensioni){
            assertNotNull(recensione.getId());
            System.out.println(recensione.getDescrizione());
        }
    }

    @Test
    public void testDelete(){
        DBManager.getInstance().getRecensioneDao().delete(Long.valueOf(32));
    }
}
