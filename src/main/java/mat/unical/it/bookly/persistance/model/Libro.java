package mat.unical.it.bookly.persistance.model;

public class Libro {
    private String isbn;
    private String nome;
    private String descrizione;
    private String autore;
    private String generi;
    private Integer numeroPagine;
    private String lingua;
    private String copertina;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGeneri() {
        return generi;
    }

    public void setGeneri(String generi) {
        this.generi = generi;
    }

    public Integer getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(Integer numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getDescrizione() { return descrizione; }

    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public String getCopertina() { return copertina; }

    public void setCopertina(String copertina) { this.copertina = copertina; }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + isbn +
                ", title=" + nome +
                ", authors=" +  autore +
                ", generi=" + generi +
                ", lingua=" + lingua +
                ", numPagine=" + numeroPagine +
                ", description=" + descrizione  +
                ", copertina=" + copertina +
                '}';
    }
}
