package mat.unical.it.bookly;


import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Follow;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FollowDaoPostgresTest {

    @Test
    void contextLoad(){}


    @Test
    public void testFollowList(){
        List<Utente> utenti = DBManager.getInstance().getFollowDao().followList(Long.valueOf(46));
        Utente utente1 = DBManager.getInstance().getUtenteDao().findByPrimaryKey(Long.valueOf(46));
        System.out.println("L'utente :" + utente1.getNome() + " segue i seguenti profili");
        for(Utente utente : utenti){
            System.out.println( "nome: " + utente.getNome() + "-- codice: " + utente.getId());
        }
    }

    @Test
    public void testSingleFollow(){
        Follow follow = DBManager.getInstance().getFollowDao().singleFollow(Long.valueOf(49),Long.valueOf(50));
        if(follow != null){
            Utente utente1 = DBManager.getInstance().getUtenteDao().findByPrimaryKey(follow.getUtente1());
            Utente utente2 = DBManager.getInstance().getUtenteDao().findByPrimaryKey(follow.getUtente2());
            System.out.println(utente1.getNome() + " segue: " + utente2.getNome());
        }else{
            System.out.println("non esiste questo follow");
        }
    }

    @Test
    public void testCreateFollow(){
        DBManager.getInstance().getFollowDao().createFollow(Long.valueOf(49),Long.valueOf(50));
    }

    @Test
    public void testDeleteFollow(){
        DBManager.getInstance().getFollowDao().deleteFollow(Long.valueOf(49),Long.valueOf(50));
    }
}
