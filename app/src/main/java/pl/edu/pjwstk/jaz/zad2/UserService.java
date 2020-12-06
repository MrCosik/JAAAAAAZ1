package pl.edu.pjwstk.jaz.zad2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.readiness.UserEntity;

import javax.persistence.EntityManager;

@Service
public class UserService {

    private final EntityManager em;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(EntityManager em) {
        this.em = em;
    }

    public void saveUser(String username, String password){
        var userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        em.persist(userEntity);

    }

    public UserEntity findUserByUsername(String username){
        return em.createQuery("select ue from UserEntity ue where ue.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getSingleResult();
    }


}
