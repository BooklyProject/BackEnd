package mat.unical.it.bookly.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.*;
import mat.unical.it.bookly.persistance.model.*;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class FrontEndController {

    @GetMapping("/reports")
    public List<Segnalazione> getSegnalazioni(HttpServletRequest req, @RequestParam String jsessionid){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Amministratore amministratore = (Amministratore) session.getAttribute("user");
        SegnalazioneDao dao = DBManager.getInstance().getSegnalazioneDao();
        return dao.findByAdministrator(amministratore.getId());
    }

    @GetMapping("/addReport")
    public boolean creaSegnalazione(HttpServletRequest req, @RequestParam String sessionId, @RequestParam String tipo,
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
    public boolean aggiungiEvento(HttpServletRequest req, @RequestParam String jsessionid, @RequestParam String nome,
                                  @RequestParam String descrizione, @RequestParam Date data, @RequestParam String luogo){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        try {

            Evento evento = new Evento();
            evento.setNome(nome);
            evento.setDescrizione(descrizione);
            evento.setData((java.sql.Date) data);
            evento.setLuogo(luogo);
            evento.setPartecipanti(0);
            DBManager.getInstance().getEventoDao().saveOrUpdate(evento, user.getId());

        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/myEvents")
    public List<Evento> getEventiCreati(HttpServletRequest req, @RequestParam String jsessionid){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        return DBManager.getInstance().getEventoDao().findAllCreatedByUser(user.getId());
    }

    @GetMapping("/deleteEvent")
    public boolean cancellaEvento(@RequestParam Long id){
        try {
            DBManager.getInstance().getPartecipaDao().deleteAllEventPartecipations(id);
            DBManager.getInstance().getEventoDao().delete(id);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("myPartecipations")
    public List<Evento> getPartecipazioni(HttpServletRequest req, @RequestParam String jsessionid) {
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        return DBManager.getInstance().getPartecipaDao().eventFromUserList(user.getId());

    }

    @PostMapping("/partecipate")
    public boolean partecipaEvento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody Evento evento){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        try {
            DBManager.getInstance().getPartecipaDao().createPartecipation(user.getId(), evento.getId());
            evento.setPartecipanti(evento.getPartecipanti() + 1);
            DBManager.getInstance().getEventoDao().saveOrUpdate(evento, user.getId());
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/deletePartecipation")
    public boolean cancellaPartecipazione(HttpServletRequest req, @RequestParam String jsessionid, @RequestParam Long idEvento){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        try {
            DBManager.getInstance().getPartecipaDao().deletePartecipation(user.getId(), idEvento);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/getUser")
    public Utente getUtente(HttpServletRequest req, @RequestParam String jsessionid){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        return user;
    }

    @PostMapping("/sendBook")
    public Boolean prendiLibro(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody Libro libro) {

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        session.setAttribute("libro", libro);

        return true;
    }

    @GetMapping("/myCollections")
    public List<Raccolta> getRaccolteUtente(HttpServletRequest req, @RequestParam String jsessionid){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        return DBManager.getInstance().getRaccoltaDao().findAllForUser(user.getId());
    }

    @GetMapping("/createCollection")
    public Boolean creaRaccolta(HttpServletRequest req, @RequestParam String jsessionid, @RequestParam String nome){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        try {
            Raccolta raccolta = new Raccolta();
            raccolta.setNome(nome);
            raccolta.setUtente(user.getId());
            DBManager.getInstance().getRaccoltaDao().saveOrUpdate(raccolta);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/deleteCollection")
    public Boolean eliminaRaccolta(@RequestParam Long idRaccolta){

        try {
            DBManager.getInstance().getContenutoDao().deleteBooksForCollections(idRaccolta);
            DBManager.getInstance().getRaccoltaDao().delete(idRaccolta);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/getCollectionBooks")
    public List<Libro> getContenutoRaccolta(@RequestParam Long idRaccolta){

        return DBManager.getInstance().getContenutoDao().findBooksForCollection(idRaccolta);
    }

    @GetMapping("/addBook")
    public Boolean aggiungiLibroRaccolta(@RequestParam Long idRaccolta, @RequestParam String ISBN){
        try {
            DBManager.getInstance().getContenutoDao().save(idRaccolta, ISBN);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/deleteBook")
    public Boolean cancellaLibroRaccolta(@RequestParam Long idRaccolta, @RequestParam String ISBN){
        try {
            DBManager.getInstance().getContenutoDao().delete(idRaccolta, ISBN);
        }catch(Exception e){
            return false;
        }

        return true;
    }

}