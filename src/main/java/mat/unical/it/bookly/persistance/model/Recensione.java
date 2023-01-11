package mat.unical.it.bookly.persistance.model;

import java.sql.Date;

public class Recensione {

    public Recensione(){
        numeroMiPiace = 0;
        numeroNonMiPiace = 0;
    }
    Integer id;
    String descrizione;
    Integer voto;
    Date data;
    Integer numeroMiPiace;
    Integer numeroNonMiPiace;
    Libro libro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getNumeroMiPiace() {
        return numeroMiPiace;
    }

    public void setNumeroMiPiace(Integer numeroMiPiace) {
        this.numeroMiPiace = numeroMiPiace;
    }

    public Integer getNumeroNonMiPiace() {
        return numeroNonMiPiace;
    }

    public void setNumeroNonMiPiace(Integer numeroNonMiPiace) {
        this.numeroNonMiPiace = numeroNonMiPiace;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
