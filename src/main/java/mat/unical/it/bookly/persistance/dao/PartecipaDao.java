package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Evento;
import mat.unical.it.bookly.persistance.model.Partecipazione;
import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface PartecipaDao {
    List<Utente> usersToEvent(Long evento);
    void createPartecipation(Long utente,Long evento);
    Partecipazione singlePartecipation(Long utente, Long eveto);
    void deletePartecipation(Long utente,Long evento);
    List<Evento> eventFromUserList(Long utente);
    void deleteAllEventPartecipations(Long evento);
}
