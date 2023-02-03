package mat.unical.it.bookly.persistance.model;

import java.sql.Date;
import java.util.List;

public class Commento{
     public Commento() {
          numeroMiPiace = 0;
          numeroNonMiPiace = 0;
     }

     private Long id;
     private String descrizione;
     private Integer numeroMiPiace;
     private Integer numeroNonMiPiace;
     private Long recensione;

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

     public Long getRecensioni() {
          return recensione;
     }

     public void setRecensioni(Long recensione) {
          this.recensione = recensione;
     }
}
