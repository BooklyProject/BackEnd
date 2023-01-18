package mat.unical.it.bookly.persistance.security;

import mat.unical.it.bookly.controller.UserService;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.Provider;
import mat.unical.it.bookly.persistance.model.Raccolta;
import mat.unical.it.bookly.persistance.model.UserPrincipal;
import mat.unical.it.bookly.persistance.model.Utente;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    private UtenteDao userDao = DBManager.getInstance().getUtenteDao();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente user = userDao.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }

    public void processOAuthPostLogin(String email, String username) {
        Utente existUser = userDao.findByEmail(email);
        UserService userService = new UserService();

        if (existUser == null) {
            Utente newUser = new Utente();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setBanned(false);

            userDao.saveOrUpdate(newUser);
            userService.setCurrentUser(newUser);
        }
        else{
            userService.setCurrentUser(existUser);
        }


    }
}
