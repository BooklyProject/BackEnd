package mat.unical.it.bookly.persistance.model;

public class Statistiche {

    private Integer libriLetti;

    private String generePreferito;

    private Integer raccolteCreate;

    private String autorePreferito;

    private Integer seguiti;

    private Integer followers;

    private Integer eventiCreati;

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

    public Integer getRaccolteCreate() {
        return raccolteCreate;
    }

    public void setRaccolteCreate(Integer raccolteCreate) {
        this.raccolteCreate = raccolteCreate;
    }

    public String getAutorePreferito() {
        return autorePreferito;
    }

    public void setAutorePreferito(String autorePreferito) {
        this.autorePreferito = autorePreferito;
    }

    public Integer getSeguiti() {
        return seguiti;
    }

    public void setSeguiti(Integer seguiti) {
        this.seguiti = seguiti;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getEventiCreati() {
        return eventiCreati;
    }

    public void setEventiCreati(Integer eventiCreati) {
        this.eventiCreati = eventiCreati;
    }

    public Integer getEventiPartecipati() {
        return eventiPartecipati;
    }

    public void setEventiPartecipati(Integer eventiPartecipati) {
        this.eventiPartecipati = eventiPartecipati;
    }

    @Override
    public String toString() {
        return "Statistiche{" +
                "libriLetti=" + libriLetti +
                ", generePreferito='" + generePreferito + '\'' +
                ", raccolteCreate=" + raccolteCreate +
                ", autorePreferito='" + autorePreferito + '\'' +
                ", seguiti=" + seguiti +
                ", followers=" + followers +
                ", eventiCreati=" + eventiCreati +
                ", eventiPartecipati=" + eventiPartecipati +
                '}';
    }
}
