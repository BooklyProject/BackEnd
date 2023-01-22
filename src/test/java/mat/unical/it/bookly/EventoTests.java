package mat.unical.it.bookly;


import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Evento;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;

@SpringBootTest
public class EventoTests {

    @Test
    void contextLoads(){}


    @Test
    public void testSaveUpdate(){
        Evento evento = new Evento();
        evento.setId(Long.valueOf(99));
        evento.setNome("Cronoscalata del reventino");
        evento.setDescrizione("Facciamo le gincane e ci divertiamo");
        evento.setData(Date.valueOf("2025-12-03"));
        evento.setLuogo("Lamezia Terme");
        DBManager.getInstance().getEventoDao().saveOrUpdate(evento,Long.valueOf(49));

    }



    @Test
    public void testFindCreatedByUser(){
        List<Evento> eventi = DBManager.getInstance().getEventoDao().findAllCreatedByUser(Long.valueOf(30));
        for(Evento evento: eventi){
            assertNotNull(evento.getId());
            System.out.println(evento.getDescrizione());
        }
    }

    @Test
    public void testDelete(){
        DBManager.getInstance().getEventoDao().delete(Long.valueOf(37));
    }
}
