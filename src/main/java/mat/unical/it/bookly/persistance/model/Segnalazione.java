package mat.unical.it.bookly.persistance.model;

public class Segnalazione {
    Integer id;
    String tipo;
    Post post;
    Utente utente;
    Amministratore amministratore;
    //TODO: mettere campo descrizione sia qua che su db
}
