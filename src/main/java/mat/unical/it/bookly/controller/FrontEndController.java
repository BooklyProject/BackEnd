package mat.unical.it.bookly.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.*;
import mat.unical.it.bookly.persistance.model.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class FrontEndController {


    @GetMapping("/reports")
    public List<Segnalazione> getSegnalazioni(HttpServletRequest req, @RequestParam String sessionId){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        Amministratore amministratore = (Amministratore) session.getAttribute("user");
        SegnalazioneDao dao = DBManager.getInstance().getSegnalazioneDao();
        return dao.findByAdministrator(amministratore.getId());
    }

    @GetMapping("/addReport")
    public boolean aggiungiSegnalazione(HttpServletRequest req, @RequestParam String sessionId, @RequestParam String tipo,
                                   @RequestParam Long post, @RequestParam String descrizione){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        Utente user = (Utente) session.getAttribute("user");

        try {

            Segnalazione segnalazione = new Segnalazione();
            segnalazione.setTipo(tipo);
            segnalazione.setPost(post);
            segnalazione.setAmministratore(RandomUtils.nextLong( 1, DBManager.getInstance().getAmministratoreDao().findAdministratorsNum() + 1));
            segnalazione.setDescrizione(descrizione);
            segnalazione.setUtente(user.getId());

            DBManager.getInstance().getSegnalazioneDao().saveOrUpdate(segnalazione);

        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/deleteReport")
    public boolean cancellaSegnalazione(@RequestParam Long id){
        try {
            DBManager.getInstance().getSegnalazioneDao().delete(id);
        }catch(Exception e){
            return false;
        }

        return true;

    }

    @GetMapping("/banUser")
    public boolean banUtente(@RequestParam Long id){
        UtenteDao dao = DBManager.getInstance().getUtenteDao();
        Utente user = dao.findByPrimaryKey(id);
        user.setBanned(!user.getBanned());
        try {
            dao.saveOrUpdate(user);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @GetMapping("/events")
    public List<Evento> getEventi(){

        return DBManager.getInstance().getEventoDao().findAll();
    }

    @GetMapping("/addEvent")
    public boolean aggiungiEvento(HttpServletRequest req, @RequestParam String sessionId, @RequestParam String nome,
                                  @RequestParam String descrizione, @RequestParam Date data, @RequestParam String luogo){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        Utente user = (Utente) session.getAttribute("user");

        try {

            Evento evento = new Evento();
            evento.setNome(nome);
            evento.setDescrizione(descrizione);
            evento.setData((java.sql.Date) data);
            evento.setLuogo(luogo);
            DBManager.getInstance().getEventoDao().saveOrUpdate(evento, user.getId());

        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/myEvents")
    public List<Evento> getEventiCreati(HttpServletRequest req, @RequestParam String sessionId){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        Utente user = (Utente) session.getAttribute("user");

        return DBManager.getInstance().getEventoDao().findAllCreatedByUser(user.getId());
    }

    @GetMapping("/deleteEvent")
    public boolean cancellaEvento(@RequestParam Long id){
        try {
            DBManager.getInstance().getEventoDao().delete(id);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("myPartecipations")
    public List<Evento> getPartecipazioni(HttpServletRequest req, @RequestParam String sessionId) {
        return null;
    }

    @GetMapping("/partecipate")
    public boolean partecipaEvento(HttpServletRequest req, @RequestParam String sessionId, @RequestParam Long idEvento){
        return false;
    }

    @GetMapping("/deletePartecipation")
    public boolean cancellaPartecipazione(HttpServletRequest req, @RequestParam String sessionId, @RequestParam Long idEvento){
        return false;
    }

    @GetMapping("/getUser")
    public Utente getUtente(HttpServletRequest req, @RequestParam String jsessionid){

        System.out.println("sessionIdjyh: " + jsessionid);
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        return user;
    }







}