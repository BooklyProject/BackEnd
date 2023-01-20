package mat.unical.it.bookly.persistance.model;

import java.util.List;

public class Follow {
    Utente utente1;
    List<Utente> utentiSeguiti;

    public Utente getUtente1() {
        return utente1;
    }

    public void setUtente1(Utente utente1) {
        this.utente1 = utente1;
    }

    public List<Utente> getUtentiSeguiti() {
        return utentiSeguiti;
    }

    public void setUtentiSeguiti(List<Utente> utentiSeguiti) {
        this.utentiSeguiti = utentiSeguiti;
    }
}
