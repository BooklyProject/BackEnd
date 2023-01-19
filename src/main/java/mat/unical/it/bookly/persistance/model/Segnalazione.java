package mat.unical.it.bookly.persistance.model;

public class Segnalazione {

    Long id;

    String tipo;

    Long post;

    Long utente;

    Long amministratore;

    String descrizione;

    Boolean isDone;

    public Long getId() { return id;}

    public String getTipo() { return tipo;}

    public Long getPost() { return post; }

    public Long getUtente() { return utente; }

    public Long getAmministratore() { return amministratore; }

    public String getDescrizione() { return descrizione; }

    public void setId(Long id) { this.id = id; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public void setPost(Long post) { this.post = post; }

    public void setUtente(Long utente) { this.utente = utente; }

    public void setAmministratore(Long amministratore) { this.amministratore = amministratore; }

    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public Boolean getDone() { return isDone; }

    public void setDone(Boolean done) { this.isDone = done; }
}
