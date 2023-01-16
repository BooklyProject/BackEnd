package mat.unical.it.bookly;


import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Commento;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;

@SpringBootTest
public class CommentoTests {

    @Test
    void contextLoads(){

    }

    @Test
    public void testSaveUpdate(){
        Commento c = new Commento();
        c.setId(Long.valueOf(99));
        c.setDescrizione("amore mio ti amo");
        c.setData(Date.valueOf("2023-01-16"));
        c.setRecensioni(Long.valueOf(33));
        DBManager.getInstance().getCommentoDao().saveOrUpdate(c);
    }

    @Test
    public void testFindAllWroteByUser(){
        List<Commento> commenti = DBManager.getInstance().getCommentoDao().findAllWroteByUser(Long.valueOf(29));
        for(Commento commento: commenti){
            assertNotNull(commento.getId());
            System.out.println(commento.getDescrizione());
        }
    }

    @Test
    public void testDelete(){
        DBManager.getInstance().getCommentoDao().delete(Long.valueOf(35));
    }
}
