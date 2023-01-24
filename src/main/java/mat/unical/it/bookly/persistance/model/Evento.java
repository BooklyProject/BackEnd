package mat.unical.it.bookly.persistance.model;

import java.sql.Date;

public class Evento {
    private Long id;
    private String nome;
    private String descrizione;
    private Date data;
    private String luogo;
    private Integer partecipanti;

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

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public Integer getPartecipanti() { return partecipanti; }

    public void setPartecipanti(Integer partecipanti) { this.partecipanti = partecipanti; }
}
