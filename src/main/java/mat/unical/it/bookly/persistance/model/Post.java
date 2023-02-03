package mat.unical.it.bookly.persistance.model;

public class Post {

    private Long id;
    private Long idUtente;
    private String tipologia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public String getTipologia() { return tipologia; }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
}
