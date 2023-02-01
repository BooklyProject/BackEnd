package mat.unical.it.bookly.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.SegnalazioneDao;
import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/segnalazioni")
public class ReportController {

    @GetMapping("/reports")
    public List<Segnalazione> getSegnalazioni(HttpServletRequest req, @RequestParam String jsessionid){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Amministratore amministratore = (Amministratore) session.getAttribute("administrator");
        SegnalazioneDao dao = DBManager.getInstance().getSegnalazioneDao();
        return dao.findByAdministrator(amministratore.getId());
    }

    @PostMapping("/getUserId)")
    public Utente getUtente(@RequestBody HashMap<String, Long> u){
        return DBManager.getInstance().getUtenteDao().findByPrimaryKey(u.get("idUtente"));
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

    @PostMapping("/getPostDescription")
    public String getDescrizione(@RequestBody HashMap<String,Long> p){

        Post post = DBManager.getInstance().getPostDao().findByPrimaryKey(p.get("idPost"));
        String descrizione = "";
        if(post.getTipologia().equals("recensione")){
            Recensione recensione = DBManager.getInstance().getRecensioneDao().findByPrimaryKey(p.get("idPost"));
            descrizione = recensione.getDescrizione();
        }
        else if(post.getTipologia().equals("commento")){
            Commento commento = DBManager.getInstance().getCommentoDao().findByPrimaryKey(p.get("idPost"));
            descrizione = commento.getDescrizione();
        }
        else if(post.getTipologia().equals("evento")){
            Evento evento = DBManager.getInstance().getEventoDao().findByPrimaryKey(p.get("idPost"));
            descrizione = evento.getDescrizione();
        }

        return descrizione;
    }
}
