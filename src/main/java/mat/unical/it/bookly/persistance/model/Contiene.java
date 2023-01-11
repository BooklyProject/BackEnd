package mat.unical.it.bookly.persistance.model;

import java.util.List;

public class Contiene {
    Raccolta raccolta;
    List<Libro> libri;

    public Raccolta getRaccolta() {
        return raccolta;
    }

    public void setRaccolta(Raccolta raccolta) {
        this.raccolta = raccolta;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }
}
