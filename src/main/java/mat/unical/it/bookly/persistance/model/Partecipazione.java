package mat.unical.it.bookly.persistance.model;

import java.util.List;

public class Partecipazione {
    List<Utente> utenti;
    Evento evento;

    public List<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
