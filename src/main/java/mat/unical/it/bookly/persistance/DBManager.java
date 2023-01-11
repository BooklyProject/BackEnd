package mat.unical.it.bookly.persistance;

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
                conn = DriverManager.getConnection("jdbc:postgresql://manny.db.elephantsql.com:5432","postgres","postgres");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }


}
