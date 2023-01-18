package mat.unical.it.bookly.persistance.model;

public class Raccolta {
    Long id;
    String nome;
    Long utente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getUtente() {
        return utente;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }

}
