package mat.unical.it.bookly.persistance.model;

public class ValutazioneRecensione {

    private Long recensione;
    private Long utente;
    private String tipo;


    public Long getRecensione() { return recensione; }

    public void setRecensione(Long recensione) { this.recensione = recensione; }

    public Long getUtente() { return utente; }

    public void setUtente(Long utente) { this.utente = utente; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo;}
}
