package mat.unical.it.bookly.persistance.model;

public class Statistiche {

    private Integer libriLetti;

    private String generePreferito;

    private String autorePreferito;

    private Integer eventiPartecipati;


    public Integer getLibriLetti() {
        return libriLetti;
    }

    public void setLibriLetti(Integer libriLetti) {
        this.libriLetti = libriLetti;
    }

    public String getGenerePreferito() {
        return generePreferito;
    }

    public void setGenerePreferito(String generePreferito) {
        this.generePreferito = generePreferito;
    }

    public String getAutorePreferito() {
        return autorePreferito;
    }

    public void setAutorePreferito(String autorePreferito) {
        this.autorePreferito = autorePreferito;
    }

    public Integer getEventiPartecipati() {
        return eventiPartecipati;
    }

    public void setEventiPartecipati(Integer eventiPartecipati) {
        this.eventiPartecipati = eventiPartecipati;
    }

}
