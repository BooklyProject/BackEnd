package mat.unical.it.bookly.persistance.model;

public class ValutazioneCommento {

    private Long commento;
    private Long utente;
    private String tipologia;

    public Long getCommento() {
        return commento;
    }

    public void setCommento(Long commento) {
        this.commento = commento;
    }

    public Long getUtente() {
        return utente;
    }

    public void setUtente(Long utente) {
        this.utente = utente;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }


}
