package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Follow;
import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface FollowDao {

    public List<Utente> followList(Long id); //return list of users that utente 1 follows
    public List<Utente> followByList(Long id); //return list of users that utente1 is followed by
    public Follow singleFollow(Long utente1, Long utente2); //return true if utente1 follows utente2
    public void createFollow(Long utente1,Long utente2); //create follow utente1 --> utente2
    public void deleteFollow(Long utente1,Long utente2); //delete follow utente1 --> utente2
}
