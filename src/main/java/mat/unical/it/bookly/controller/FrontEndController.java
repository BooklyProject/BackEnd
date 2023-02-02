package mat.unical.it.bookly.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.*;
import mat.unical.it.bookly.persistance.model.*;
import mat.unical.it.bookly.persistance.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class FrontEndController {

    @GetMapping("/addReport")
    public boolean creaSegnalazione(HttpServletRequest req, @RequestParam String sessionId, @RequestParam String tipo,
                                    @RequestParam Long post, @RequestParam String descrizione){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        Utente user = (Utente) session.getAttribute("user");

        try {

            Segnalazione segnalazione = new Segnalazione();
            segnalazione.setTipo(tipo);
            segnalazione.setPost(post);
            segnalazione.setAmministratore(Long.valueOf(280));
            segnalazione.setDescrizione(descrizione);
            segnalazione.setUtente(user.getId());

            DBManager.getInstance().getSegnalazioneDao().saveOrUpdate(segnalazione);

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @GetMapping("/events")
    public List<Evento> getEventi(HttpServletRequest req, @RequestParam String jsessionid){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        return DBManager.getInstance().getEventoDao().findAvailableEvents(user.getId());
    }

    @PostMapping("/addEvent")
    public boolean aggiungiEvento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody Evento evento){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        try {
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
        List<Evento> mieiEventi = DBManager.getInstance().getEventoDao().findAllCreatedByUser(user.getId());

        return mieiEventi;
    }

    @PostMapping("/deleteEvent")
    public boolean cancellaEvento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> e){

        Long idEvento = e.get("idEvento");
        System.out.println("id evento: " + idEvento);
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        try {
            DBManager.getInstance().getSegnalazioneDao().deleteForPost(idEvento);
            DBManager.getInstance().getPartecipaDao().deleteAllEventPartecipations(idEvento);
            DBManager.getInstance().getEventoDao().delete(idEvento);

        }catch(Exception ex){
            return false;
        }

        return true;
    }

    @GetMapping("/myPartecipations")
    public List<Evento> getPartecipazioni(HttpServletRequest req, @RequestParam String jsessionid) {
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        return DBManager.getInstance().getPartecipaDao().eventFromUserList(user.getId());

    }

    @PostMapping("/partecipate")
    public boolean partecipaEvento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> e){

        Long idEvento = e.get("idEvento");
        System.out.println("id evento: " + idEvento);
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        try {
            Evento evento = DBManager.getInstance().getEventoDao().findByPrimaryKey(idEvento);
            DBManager.getInstance().getPartecipaDao().createPartecipation(user.getId(), evento.getId());
            evento.setPartecipanti(evento.getPartecipanti() + 1);
            DBManager.getInstance().getEventoDao().saveOrUpdate(evento, user.getId());
        }catch(Exception ex){
            return false;
        }

        return true;
    }


    @PostMapping("/deletePartecipation")
    public boolean cancellaPartecipazione(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> evento){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        try {
            DBManager.getInstance().getPartecipaDao().deletePartecipation(user.getId(), evento.get("idEvento"));
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
    public Boolean prendiLibroCatalogo(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody Libro libro) {
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        session.setAttribute("libro", libro);
        return true;
    }

    @PostMapping("/getBookFromCollection")
    public Boolean mostraLibroRaccolta(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Libro> l) {
        Libro libro = l.get("libro");
        System.out.println("libro: " + libro.getNome());
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

    @PostMapping("/createCollection")
    public Long creaRaccolta(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap <String,String> nome){
        Long idRaccolta = null;
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        try {
            Raccolta raccolta = new Raccolta();
            raccolta.setNome(nome.get("nome"));
            raccolta.setUtente(user.getId());
            idRaccolta = DBManager.getInstance().getRaccoltaDao().saveOrUpdate(raccolta);
        }catch(Exception e){
            e.printStackTrace();
        }
        return idRaccolta;
    }

    @PostMapping("/deleteCollection")
    public Boolean eliminaRaccolta(@RequestBody HashMap <String, Long> r){
        Long idRaccolta = r.get("idRaccolta");
        try {
            DBManager.getInstance().getContenutoDao().deleteBooksForCollections(idRaccolta);
            DBManager.getInstance().getRaccoltaDao().delete(idRaccolta);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/getCollectionBooks")
    public List<Libro> getContenutoRaccolta(@RequestBody HashMap<String, Long> r){
        Long idRaccolta = r.get("idRaccolta");
        return DBManager.getInstance().getContenutoDao().findBooksForCollection(idRaccolta);
    }

    @PostMapping("/addBook")
    public Boolean aggiungiLibroRaccolta(@RequestParam Long idRaccolta, @RequestBody Libro libro){
        try {
            DBManager.getInstance().getLibroDao().saveOrUpdate(libro);
            DBManager.getInstance().getContenutoDao().save(idRaccolta, libro.getIsbn());
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

    @GetMapping("/getStats")
    public Statistiche mostraStatistiche(HttpServletRequest req, @RequestParam String jsessionid){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");

        Statistiche stats = new Statistiche();

        stats.setRaccolteCreate(DBManager.getInstance().getRaccoltaDao().findAllForUser(user.getId()).size());
        stats.setEventiCreati(DBManager.getInstance().getEventoDao().findAllCreatedByUser(user.getId()).size());
        stats.setEventiPartecipati(DBManager.getInstance().getPartecipaDao().eventFromUserList(user.getId()).size());
        stats.setLibriLetti(DBManager.getInstance().getRecensioneDao().findAllWroteByUser(user.getId()).size());
        stats.setAutorePreferito(DBManager.getInstance().getRecensioneDao().findPreferredResultByAttribute(user.getId(), "autore"));
        stats.setGenerePreferito(DBManager.getInstance().getRecensioneDao().findPreferredResultByAttribute(user.getId(), "generi"));

        return stats;
    }

    @PostMapping("/modifyProfile")
    public Boolean modificaProfilo(@RequestBody HashMap<String, Utente> u){
        try {
            Utente utente = u.get("utente");
            System.out.println("utente id: " + utente.getId());
            System.out.println("username: " + utente.getUsername());
            DBManager.getInstance().getUtenteDao().saveOrUpdate(utente);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @PostMapping("/addReview")
    public Long aggiungiRecensione(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody Recensione recensione){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);

        Utente user = (Utente) session.getAttribute("user");
        Libro libro = (Libro) session.getAttribute("libro");
        recensione.setLibro(libro.getIsbn());
        Long idR = null;
        try {
            DBManager.getInstance().getLibroDao().saveOrUpdate(libro);
            idR = DBManager.getInstance().getRecensioneDao().saveOrUpdate(recensione, user.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
        return idR;
    }

    @PostMapping("/deleteReview")
    public Boolean cancellaRecensione(@RequestBody HashMap<String, Long> r){
        Long idRecensione = r.get("idRecensione");
        try {
            DBManager.getInstance().getSegnalazioneDao().deleteForPost(idRecensione);
            DBManager.getInstance().getValutazioneRecensioneDao().deleteForReview(idRecensione);
            DBManager.getInstance().getCommentoDao().deleteForReview(idRecensione);
            DBManager.getInstance().getRecensioneDao().delete(idRecensione);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/getReviews")
    public List<Recensione> mostraRecensioni(HttpServletRequest req, @RequestParam String jsessionid){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Libro libro = (Libro) session.getAttribute("libro");

        List<Recensione> lista = DBManager.getInstance().getRecensioneDao().findReviewsByBook(user.getId(), libro.getIsbn());
        for(int i = 0; i < lista.size(); i++) {
            System.out.println("id rec:" + lista.get(i).getId());
        }
        System.out.println();
        return lista;
    }

    @PostMapping("/getReviewWriter")
    public Utente mostraScrittoreRecensione(@RequestBody HashMap<String, Long> r){
        Long idRecensione = r.get("idRecensione");
        System.out.println("idRec: " + idRecensione);
        Long idUtente = DBManager.getInstance().getRecensioneDao().findUserByReview(idRecensione);
        return DBManager.getInstance().getUtenteDao().findByPrimaryKey(idUtente);
    }

    @PostMapping("/getComments")
    public List<Commento> mostraCommenti(@RequestBody HashMap<String, Long> r){
        Long idRecensione = r.get("idRecensione");

        List<Commento> lista = DBManager.getInstance().getCommentoDao().findByReview(idRecensione);
        for(Commento comm: lista) {
            System.out.println("idComm: " + comm.getId());
        }
        return lista;
    }

    @PostMapping("/addComment")
    public Long aggiungiCommento(HttpServletRequest req, @RequestParam String jsessionid, @RequestParam Long idRec, @RequestBody HashMap<String, String> c){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        System.out.println("session: " + session.getId());
        System.out.println("idRecensione: " + idRec);
        Utente user = (Utente) session.getAttribute("user");
        Long idC = null;
        Commento commento = new Commento();
        String descrizione = c.get("descrizione");
        System.out.println("comm: " + descrizione);
        commento.setRecensioni(idRec);
        commento.setDescrizione(descrizione);
        try {
            idC = DBManager.getInstance().getCommentoDao().saveOrUpdate(commento, user.getId());
        }catch(Exception e){
            e.printStackTrace();
        }

        return idC;
    }

    @PostMapping("/deleteComment")
    public Boolean cancellaCommento(@RequestBody HashMap<String, Long> c){
        Long idCommento = c.get("idCommento");
        try {
            DBManager.getInstance().getSegnalazioneDao().deleteForPost(idCommento);
            DBManager.getInstance().getValutazioneCommentoDao().deleteForComment(idCommento);
            DBManager.getInstance().getCommentoDao().delete(idCommento);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/getCommentWriter")
    public Utente mostraScrittoreCommento(@RequestBody HashMap<String, Long> c){
        Long idCommento = c.get("idCommento");
        Long idUtente = DBManager.getInstance().getCommentoDao().findUserByComment(idCommento);
        return DBManager.getInstance().getUtenteDao().findByPrimaryKey(idUtente);
    }

    @GetMapping("/createDefaultCollection")
    public Boolean creaRaccolteDefault(HttpServletRequest req, @RequestParam String jsessionid){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        try {

            Raccolta raccoltaDaLeggere = new Raccolta();
            raccoltaDaLeggere.setNome("Da leggere");
            raccoltaDaLeggere.setUtente(user.getId());
            DBManager.getInstance().getRaccoltaDao().saveOrUpdate(raccoltaDaLeggere);
            Raccolta raccoltaPreferiti = new Raccolta();
            raccoltaPreferiti.setNome("Preferiti");
            raccoltaPreferiti.setUtente(user.getId());
            DBManager.getInstance().getRaccoltaDao().saveOrUpdate(raccoltaPreferiti);

        }catch(Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/getCommentLike")
    public Boolean mostraLikeCommento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> c){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idCommento = c.get("idCommento");
        ValutazioneCommento v = DBManager.getInstance().getValutazioneCommentoDao().findByPrimaryKey(idCommento, user.getId());
        if(v != null && v.getTipo().equals("like")) {
            return true;
        }
        else{
            return  false;
        }
    }

    @PostMapping("/addCommentLike")
    public Boolean aggiungiLikeCommento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> c){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idCommento = c.get("idCommento");
        try {
            ValutazioneCommento v = new ValutazioneCommento();
            v.setCommento(idCommento);
            v.setUtente(user.getId());
            v.setTipo("like");
            DBManager.getInstance().getValutazioneCommentoDao().saveOrUpdate(v);
            Commento commento = DBManager.getInstance().getCommentoDao().findByPrimaryKey(idCommento);
            commento.setNumeroMiPiace(commento.getNumeroMiPiace() + 1);
            DBManager.getInstance().getCommentoDao().saveOrUpdate(commento, user.getId());
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/removeCommentLike")
    public Boolean rimuoviLikeCommento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> c){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idCommento = c.get("idCommento");
        try {
            DBManager.getInstance().getValutazioneCommentoDao().delete(idCommento, user.getId());
            Commento commento = DBManager.getInstance().getCommentoDao().findByPrimaryKey(idCommento);
            commento.setNumeroMiPiace(commento.getNumeroMiPiace() - 1);
            DBManager.getInstance().getCommentoDao().saveOrUpdate(commento, user.getId());
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/getCommentDislike")
    public Boolean mostraDislikeCommento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> c){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idCommento = c.get("idCommento");
        ValutazioneCommento v = DBManager.getInstance().getValutazioneCommentoDao().findByPrimaryKey(idCommento, user.getId());
        if(v != null && v.getTipo().equals("dislike")) {
            return true;
        }
        else{
            return  false;
        }
    }

    @PostMapping("/addCommentDislike")
    public Boolean aggiungiDislikeCommento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> c){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idCommento = c.get("idCommento");
        try {
            ValutazioneCommento v = new ValutazioneCommento();
            v.setCommento(idCommento);
            v.setUtente(user.getId());
            v.setTipo("dislike");
            DBManager.getInstance().getValutazioneCommentoDao().saveOrUpdate(v);
            Commento commento = DBManager.getInstance().getCommentoDao().findByPrimaryKey(idCommento);
            commento.setNumeroNonMiPiace(commento.getNumeroNonMiPiace() + 1);
            DBManager.getInstance().getCommentoDao().saveOrUpdate(commento, user.getId());
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/removeCommentDislike")
    public Boolean rimuoviDislikeCommento(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> c){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idCommento = c.get("idCommento");
        try {
            DBManager.getInstance().getValutazioneCommentoDao().delete(idCommento, user.getId());
            Commento commento = DBManager.getInstance().getCommentoDao().findByPrimaryKey(idCommento);
            commento.setNumeroNonMiPiace(commento.getNumeroNonMiPiace() - 1);
            DBManager.getInstance().getCommentoDao().saveOrUpdate(commento, user.getId());
        } catch (Exception e){
            return false;
        }

        return true;
    }
    @PostMapping("/getReviewLike")
    public Boolean mostraLikeRecensione(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> r){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idRecensione = r.get("idRecensione");
        ValutazioneRecensione v = DBManager.getInstance().getValutazioneRecensioneDao().findByPrimaryKey(idRecensione, user.getId());
        if(v != null && v.getTipo().equals("like")) {
            System.out.println("liked");
            return true;
        }
        else{
            return  false;
        }
    }

    @PostMapping("/addReviewLike")
    public Boolean aggiungiLikeRecensione(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> r){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idRecensione = r.get("idRecensione");
        try {
            ValutazioneRecensione v = new ValutazioneRecensione();
            v.setRecensione(idRecensione);
            v.setUtente(user.getId());
            v.setTipo("like");
            DBManager.getInstance().getValutazioneRecensioneDao().saveOrUpdate(v);
            Recensione recensione = DBManager.getInstance().getRecensioneDao().findByPrimaryKey(idRecensione);
            recensione.setNumeroMiPiace(recensione.getNumeroMiPiace() + 1);
            DBManager.getInstance().getRecensioneDao().saveOrUpdate(recensione, user.getId());
            Recensione recensione2 = DBManager.getInstance().getRecensioneDao().findByPrimaryKey(idRecensione);
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/removeReviewLike")
    public Boolean rimuoviLikeRecensione(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> r){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idRecensione = r.get("idRecensione");
        try {
            DBManager.getInstance().getValutazioneRecensioneDao().delete(idRecensione, user.getId());
            Recensione recensione = DBManager.getInstance().getRecensioneDao().findByPrimaryKey(idRecensione);
            recensione.setNumeroMiPiace(recensione.getNumeroMiPiace() - 1);
            DBManager.getInstance().getRecensioneDao().saveOrUpdate(recensione, user.getId());

        } catch (Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/getReviewDislike")
    public Boolean mostraDislikeRecensione(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> r){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idRecensione = r.get("idRecensione");
        ValutazioneRecensione v = DBManager.getInstance().getValutazioneRecensioneDao().findByPrimaryKey(idRecensione, user.getId());
        if(v != null && v.getTipo().equals("dislike")) {
            System.out.println("disliked");

            return true;
        }
        else{
            return  false;
        }
    }

    @PostMapping("/addReviewDislike")
    public Boolean aggiungiDislikeRecensione(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> r){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idRecensione = r.get("idRecensione");
        try {
            ValutazioneRecensione v = new ValutazioneRecensione();
            v.setRecensione(idRecensione);
            v.setUtente(user.getId());
            v.setTipo("dislike");
            DBManager.getInstance().getValutazioneRecensioneDao().saveOrUpdate(v);
            Recensione recensione = DBManager.getInstance().getRecensioneDao().findByPrimaryKey(idRecensione);
            recensione.setNumeroNonMiPiace(recensione.getNumeroNonMiPiace() + 1);
            DBManager.getInstance().getRecensioneDao().saveOrUpdate(recensione, user.getId());
        } catch (Exception e){
            return false;
        }

        return true;
    }

    @PostMapping("/removeReviewDislike")
    public Boolean rimuoviDislikeRecensione(HttpServletRequest req, @RequestParam String jsessionid, @RequestBody HashMap<String, Long> r){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Utente user = (Utente) session.getAttribute("user");
        Long idRecensione = r.get("idRecensione");
        try {
            DBManager.getInstance().getValutazioneRecensioneDao().delete(idRecensione, user.getId());
            Recensione recensione = DBManager.getInstance().getRecensioneDao().findByPrimaryKey(idRecensione);
            recensione.setNumeroNonMiPiace(recensione.getNumeroNonMiPiace() - 1);
            DBManager.getInstance().getRecensioneDao().saveOrUpdate(recensione, user.getId());
            Recensione recensione2 = DBManager.getInstance().getRecensioneDao().findByPrimaryKey(idRecensione);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @GetMapping("/reports")
    public List<Segnalazione> getSegnalazioni(HttpServletRequest req, @RequestParam String jsessionid){

        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        Amministratore amministratore = (Amministratore) session.getAttribute("administrator");
        SegnalazioneDao dao = DBManager.getInstance().getSegnalazioneDao();
        return dao.findByAdministrator(amministratore.getId());
    }

    @PostMapping("/getUserId")
    public Utente getUtente(@RequestBody HashMap<String, Long> u){
        return DBManager.getInstance().getUtenteDao().findByPrimaryKey(u.get("idUtente"));
    }

    @GetMapping("/deleteReport")
    public boolean cancellaSegnalazione(@RequestParam Long id){
        try {
            System.out.println("sono in cancella segn");
            DBManager.getInstance().getSegnalazioneDao().delete(id);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    @GetMapping("/deleteReportsAndPost")
    public boolean cancellaSegnalazioniEPost(@RequestParam Long id){
        Segnalazione segnalazione = DBManager.getInstance().getSegnalazioneDao().findByPrimaryKey(id);
        Long idPost = segnalazione.getPost();
        Post post = DBManager.getInstance().getPostDao().findByPrimaryKey(idPost);
        String tipologia = post.getTipologia();
        System.out.println("entro in cancella segn e post");
        try {
            DBManager.getInstance().getSegnalazioneDao().deleteForPost(idPost);
            if(tipologia.equals("recensione")) {
                DBManager.getInstance().getValutazioneRecensioneDao().deleteForReview(idPost);
                DBManager.getInstance().getCommentoDao().deleteForReview(idPost);
                DBManager.getInstance().getRecensioneDao().delete(idPost);
            }
            else if(tipologia.equals("commento")) {
                DBManager.getInstance().getValutazioneCommentoDao().deleteForComment(idPost);
                DBManager.getInstance().getCommentoDao().delete(idPost);
            }
            else if(tipologia.equals("evento")) {
                DBManager.getInstance().getPartecipaDao().deleteAllEventPartecipations(idPost);
                DBManager.getInstance().getEventoDao().delete(idPost);
            }
        }catch(Exception e){
            return false;
        }

        return true;

    }

    @GetMapping("/banUser")
    public boolean banUtente(@RequestParam Long idPost){
        Post post = DBManager.getInstance().getPostDao().findByPrimaryKey(idPost);
        Utente user = DBManager.getInstance().getUtenteDao().findByPrimaryKey(post.getIdUtente());
        user.setBanned(true);
        try {
            DBManager.getInstance().getUtenteDao().saveOrUpdate(user);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    @PostMapping("/getPostDescription")
    public String getDescrizione(@RequestBody HashMap<String,Long> p){
        Long id = p.get("idPost");
        Post post = DBManager.getInstance().getPostDao().findByPrimaryKey(id);
        String descrizione = "";
        String tipo = post.getTipologia();
        System.out.println("post: " + post.getId() + " " + post.getIdUtente() + " " + tipo);
        if(tipo.equals("recensione")){
            Recensione recensione = DBManager.getInstance().getRecensioneDao().findByPrimaryKey(id);
            descrizione = recensione.getDescrizione();
        }
        else if(tipo.equals("commento")){
            Commento commento = DBManager.getInstance().getCommentoDao().findByPrimaryKey(id);
            descrizione = commento.getDescrizione();
        }
        else if(tipo.equals("evento")){
            Evento evento = DBManager.getInstance().getEventoDao().findByPrimaryKey(id);
            descrizione = evento.getDescrizione();
        }

        return descrizione;
    }
}