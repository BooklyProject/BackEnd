package mat.unical.it.bookly.persistance.dao.postgres;

import mat.unical.it.bookly.persistance.dao.ValutazioneCommentoDao;
import mat.unical.it.bookly.persistance.model.ValutazioneCommento;
import mat.unical.it.bookly.persistance.model.ValutazioneRecensione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ValutazioneCommentoDaoPostgress implements ValutazioneCommentoDao {

    Connection conn;

    @Override
    public ValutazioneCommento findByPrimaryKey(Long idCommento, Long idUtente) {
        ValutazioneCommento valutazioneCommento = null;
        String query = "select * from valutazionicommenti where commento = ? and utente = ?";
        try{
            PreparedStatement st = conn.prepareStatement(query);
            st.setLong(1, idCommento);
            st.setLong(2, idUtente);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                valutazioneCommento = new ValutazioneCommento();
                valutazioneCommento.setCommento(rs.getLong("commento"));
                valutazioneCommento.setUtente(rs.getLong("utente"));
                valutazioneCommento.setTipologia(rs.getString("tipo"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return valutazioneCommento;
    }

    @Override
    public void saveOrUpdate(ValutazioneCommento v) {
        if (findByPrimaryKey(v.getCommento(), v.getUtente()) == null) {

            String insertStr = "INSERT INTO valutazionicommenti VALUES (?,?,?)";
            try {
                PreparedStatement st = conn.prepareStatement(insertStr);

                st.setLong(1, v.getCommento());
                st.setLong(2, v.getUtente());
                st.setString(3, v.getTipologia());

                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Long idCommento, Long idUtente) {
        String deleteStr = "DELETE FROM valutazionicommenti WHERE commento = ? and utente = ?";
        try {
            PreparedStatement st = conn.prepareStatement(deleteStr);
            st.setLong(1, idCommento);
            st.setLong(2, idUtente);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
