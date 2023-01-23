package mat.unical.it.bookly.persistance.dao;

import mat.unical.it.bookly.persistance.model.Evento;
import mat.unical.it.bookly.persistance.model.Partecipazione;
import mat.unical.it.bookly.persistance.model.Utente;

import java.util.List;

public interface PartecipaDao {
    public List<Utente> usersToEvent(Long evento);
    public void createPartecipation(Long utente,Long evento);
    public Partecipazione singlePartecipation(Long utente, Long eveto);
    public void deletePartecipation(Long utente,Long evento);
    public List<Evento> eventFromUserList(Long utente); //ritorna gli eventi a cui parteciperù l'utente

    public void deleteAllEventPartecipations(Long evento);
}
