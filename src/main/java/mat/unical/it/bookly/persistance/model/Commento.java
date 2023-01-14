package mat.unical.it.bookly.persistance.model;

import java.sql.Date;
import java.util.List;

public class Commento{
     public Commento() {
          numeroMiPiace = 0;
          numeroNonMiPiace = 0;
     }

     Integer id;
     String descrizione;
     Date data; //utilizzo java.sql.Date
     Integer numeroMiPiace;
     Integer numeroNonMiPiace;
     Long rencesione;


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

     public Long getRecensioni() {
          return rencesione;
     }

     public void setRecensioni(Long recensione) {
          this.rencesione = recensione;
     }
}
