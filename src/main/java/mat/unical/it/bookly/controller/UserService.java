package mat.unical.it.bookly.controller;

import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UtenteDao utenteDao = DBManager.getInstance().getUtenteDao();

    private Utente utente;

    public void addEmployee(Utente utente) {
        utenteDao.saveOrUpdate(utente);
    }

    public Utente getCurrentUser() { return this.utente; }

    public void setCurrentUser(Utente utente){ this.utente = utente; }

    public List<Utente> findAllEmployees() {
        return utenteDao.findAll();
    }

    public void updateEmployee(Utente utente) {
        utenteDao.saveOrUpdate(utente);
    }

    public Utente findEmployeeById(Long id) {
        return utenteDao.findByPrimaryKey(id);
    }

    public void deleteEmployee(Long id){
        utenteDao.delete(id);
    }
}
