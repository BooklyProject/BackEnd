package mat.unical.it.bookly.persistance.model;

public class Utente {
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Boolean isBanned;
    private String userImage;
    private String resetPasswordToken;


    @Override   //TODO: finish to complete equals
    public boolean equals(Object obj) {
        Utente u1 = (Utente) obj;
        return (u1.getId().equals(id) && u1.getUsername().equals(username) && u1.getNome().equals(nome));
    }

    public Utente(Long id, String username, String nome, String cognome, String email, String password, Boolean isBanned, String userImage) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.isBanned = isBanned;
        this.userImage = userImage;
    }
    public Utente(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBanned() { return isBanned; }

    public void setBanned(Boolean banned) { this.isBanned = banned; }

    public String getUserImage() { return userImage; }

    public void setUserImage(String userImage) { this.userImage = userImage; }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isBanned=" + isBanned +
                ", userImage='" + userImage + '\'' +
                '}';
    }
}
