package mat.unical.it.bookly.persistance.model;

import java.sql.Date;

public class Recensione {

    public Recensione(){
        numeroMiPiace = 0;
        numeroNonMiPiace = 0;
    }
    private Long id;
    private String descrizione;
    private Integer voto;
    private Integer numeroMiPiace;
    private Integer numeroNonMiPiace;
    private String isbnLibro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getNumeroMiPiace() { return numeroMiPiace; }

    public void setNumeroMiPiace(Integer numeroMiPiace) { this.numeroMiPiace = numeroMiPiace; }

    public Integer getNumeroNonMiPiace() { return numeroNonMiPiace; }

    public void setNumeroNonMiPiace(Integer numeroNonMiPiace) { this.numeroNonMiPiace = numeroNonMiPiace; }

    public String getLibro() { return isbnLibro; }

    public void setLibro(String isbnLibro) { this.isbnLibro = isbnLibro; }
}
