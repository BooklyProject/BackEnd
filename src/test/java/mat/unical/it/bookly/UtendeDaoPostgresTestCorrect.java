package mat.unical.it.bookly;


import mat.unical.it.bookly.persistance.dao.postgres.UtenteDaoPostgres;
import mat.unical.it.bookly.persistance.model.Utente;
import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertArrayEquals;

@SpringBootTest
public class UtendeDaoPostgresTestCorrect {

    //dati del Test
    Utente u1;
    Utente u2;

    @Mock
    public Connection mockConnection;
    @Mock
    public Statement mockStatement;
    @Mock
    public PreparedStatement mockPreparedStatement;
    @Mock
    public ResultSet mockResultSet;


    @Test
    void contextLoad() {
    }


    @BeforeEach
    public void  initialize(TestInfo testInfo){
        if (testInfo.getTestMethod().get().getName().equals("testFindAll")){
            System.out.println("inizializzazione testFindAll");
            u1 = new Utente();
            u2 = new Utente();
            u1.setId(Long.valueOf(82));
            u1.setNome("Francesco");
            u1.setCognome("Strangis");
            u1.setEmail("strangisfrancesco2@gmail.com");
            u1.setUsername("checco_stra");
            u1.setPassword("password");
            u1.setUserImage("image");
            u1.setBanned(false);
            u2.setId(Long.valueOf(84));
            u2.setNome("Gaetano");
            u2.setCognome("Prinzivalli");
            u2.setEmail("gaetanoprinzivalli99@gmail.com");
            u2.setUsername("Gtnprnz");
            u2.setPassword("password");
            u2.setUserImage("image");
            u2.setBanned(false);
        }else if(testInfo.getTestMethod().get().getName().equals("testFindByPrimaryKey")){
            System.out.println("inizializzazione testFindByPrimaryKey");
            u1 = new Utente();
            u2 = new Utente();
            u1.setId(Long.valueOf(82));
            u1.setNome("Francesco");
            u1.setCognome("Strangis");
            u1.setEmail("strangisfrancesco2@gmail.com");
            u1.setUsername("checco_stra");
            u1.setPassword("password");
            u1.setUserImage("image");
            u1.setBanned(false);
        }

    }

    @AfterClass()
    public static void afterClass(){
        System.out.println("Test completato");
    }

    @Test
    public void testFindAll() throws SQLException {
        //data test
        List<Utente> expectedUtenti = new ArrayList<>();
        expectedUtenti.add(u1);
        expectedUtenti.add(u2);

        //comportamento dei mock
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery("select * from utenti")).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("cognome")).thenReturn("Strangis","Prinzivalli");
        when(mockResultSet.getString("nome")).thenReturn("Francesco","Gaetano");
        when(mockResultSet.getString("username")).thenReturn("checco_stra","Gtnprnz");
        when(mockResultSet.getString("email")).thenReturn("strangisfrancesco2@gmail.com","gaetanoprinzivalli99@gmail.com");
        when(mockResultSet.getString("password")).thenReturn("password","password");
        when(mockResultSet.getString("user_image")).thenReturn("image","image");
        when(mockResultSet.getBoolean("is_banned")).thenReturn(false,false);
        when(mockResultSet.getLong("id")).thenReturn(Long.valueOf(82),Long.valueOf(84));

        UtenteDaoPostgres utenteDaoPostgres = new UtenteDaoPostgres(mockConnection);
        List<Utente> actualUtenti = utenteDaoPostgres.findAll();
        verify(mockStatement,times(1)).executeQuery("select * from utenti");

        assertEquals(expectedUtenti,actualUtenti);
    }

    @Test
    public void testFindByPrimaryKey() throws SQLException {
        Long expected = u1.getId();
        Long expectedId = 82L;

        //comportamento dei mock
        when(mockConnection.prepareStatement("select * from utenti where id = ?")).thenReturn(mockPreparedStatement);
        doNothing().when(mockPreparedStatement).setLong(1,expectedId);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("cognome")).thenReturn("Strangis");
        when(mockResultSet.getString("nome")).thenReturn("Francesco");
        when(mockResultSet.getString("username")).thenReturn("checco_stra");
        when(mockResultSet.getString("email")).thenReturn("strangisfrancesco2@gmail.com");
        when(mockResultSet.getString("password")).thenReturn("password");
        when(mockResultSet.getString("user_image")).thenReturn("image");
        when(mockResultSet.getBoolean("is_banned")).thenReturn(false);
        when(mockResultSet.getLong("id")).thenReturn(expectedId);

        UtenteDaoPostgres utenteDaoPostgres = new UtenteDaoPostgres(mockConnection);
        Long actual = utenteDaoPostgres.findByPrimaryKey(expectedId).getId();
        verify(mockPreparedStatement, times(1)).setLong(1, expectedId);
        verify(mockPreparedStatement, times(1)).executeQuery();

        assertEquals(expected,actual);


    }


}


