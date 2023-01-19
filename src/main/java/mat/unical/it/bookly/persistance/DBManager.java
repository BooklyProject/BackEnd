package mat.unical.it.bookly.persistance;

import mat.unical.it.bookly.persistance.dao.*;
import mat.unical.it.bookly.persistance.dao.postgres.*;
import mat.unical.it.bookly.persistance.model.Utente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager { //classe Singleton che gestice la connessione con il DB

    private DBManager(){};
    private static DBManager instance = null;

    public static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    Connection conn = null; //definiamo un oggetto di tipo Connection nullo

    public Connection getConnection(){
        if (conn == null){
            try{
                conn = DriverManager.getConnection("jdbc:postgresql://manny.db.elephantsql.com:5432/ktwiwmsl","ktwiwmsl","ciao");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public UtenteDao getUtenteDao(){
        return new UtenteDaoPostgres(getConnection());
    }
    public AmministratoreDao getAmministratoreDao(){return new AmministratoreDaoPostgres(getConnection());}
    public RaccoltaDao getRaccoltaDao(){return new RaccoltaDaoPostgress(getConnection());}
    public LibroDao getLibroDao(){return new LibroDaoPostgres(getConnection());}
    public RecensioneDao getRecensioneDao(){return new RecensioneDaoPostgres(getConnection());}
    public PostDao getPostDao(){return new PostDaoPostgres(getConnection());}
    public CommentoDao getCommentoDao(){return new CommentoDaoPostgress(getConnection());}
    public EventoDao getEventoDao(){return new EventoDaoPostgres(getConnection());}
    public SegnalazioneDao getSegnalazioneDao() { return new SegnalazioneDaoPostgres(getConnection()); }

}



