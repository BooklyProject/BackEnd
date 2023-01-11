package mat.unical.it.bookly.persistance.model;

public class Libro {
    private Integer isbn;
    private String nome;
    private String autore;
    private String generi;  //trattiamolo come string unica, nel caso di più autori questi vengono concatenati
    private Integer numeroPagine;
    private String lingua;

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
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
}
