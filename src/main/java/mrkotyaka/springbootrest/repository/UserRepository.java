package mrkotyaka.springbootrest.repository;

import mrkotyaka.springbootrest.authorities.Authorities;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> userAuthorities = new ArrayList<>();

        if (user.equals("superadmin") && password.equals("superadmin")) {
            Collections.addAll(userAuthorities, Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        } else if (user.equals("admin") && password.equals("admin")) {
            Collections.addAll(userAuthorities, Authorities.READ, Authorities.WRITE);
        } else if (user.equals("guest") && password.equals("guest")) {
            Collections.addAll(userAuthorities, Authorities.READ);
        }
        return userAuthorities;
    }
}
