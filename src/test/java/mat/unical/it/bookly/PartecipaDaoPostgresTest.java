package mat.unical.it.bookly;


import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Evento;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PartecipaDaoPostgresTest {

    @Test
    void contextLoad(){

    }

    @Test
    public void TestUsersToEvent(){
        List<Utente> utenti = DBManager.getInstance().getPartecipaDao().usersToEvent(Long.valueOf(52));
        Evento evento = DBManager.getInstance().getEventoDao().findByPrimaryKey(Long.valueOf(52));
        System.out.println("I seguenti utenti partecipano a questo evento: " + evento.getNome());
        for(Utente utente: utenti){
            System.out.println(utente.getNome());
        }
    }

    @Test
    public void TestCreatePartecipation(){
        DBManager.getInstance().getPartecipaDao().createPartecipation(Long.valueOf(50),Long.valueOf(53));
    }

    @Test
    public void TestEventFromUserList(){
        List<Evento> eventi = DBManager.getInstance().getPartecipaDao().eventFromUserList(Long.valueOf(50));
        Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(Long.valueOf(50));
        System.out.println("L'utente: " + utente.getNome() + " partecipa ai seguenti eventi:");
        for(Evento e: eventi){
            System.out.println(e.getNome());
        }
    }

    @Test
    public void TestDeletePartecipation(){
        DBManager.getInstance().getPartecipaDao().deletePartecipation(Long.valueOf(50),Long.valueOf(53));
    }
    

}
