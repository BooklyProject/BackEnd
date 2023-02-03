package mat.unical.it.bookly.persistance.model;

import java.util.List;

public class Partecipazione {

    private Long utente;
    private Long evento;

    public Long getUtente() {
        return utente;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }

    public Long getEvento() {
        return evento;
    }

    public void setEvento(Long evento) {
        this.evento = evento;
    }
}
