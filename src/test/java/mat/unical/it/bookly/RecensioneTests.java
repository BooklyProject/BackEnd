package mat.unical.it.bookly;


import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Recensione;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
public class RecensioneTests {

    @Test
    void contextLoads(){

    }

    @Test
    public void testSaveUpdate(){
        Recensione r = new Recensione();
        r.setId(Long.valueOf(99));
        r.setDescrizione("Questo libro è davvero molto interessante");
        r.setVoto(8);
        r.setData(Date.valueOf("2021-12-12"));
        r.setLibro("1234");
        DBManager.getInstance().getRecensioneDao().saveOrUpdate(r);
    }
}
