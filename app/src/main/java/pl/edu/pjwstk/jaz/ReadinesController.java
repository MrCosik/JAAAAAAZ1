package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.readiness.Test1Entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class ReadinesController {
    @PersistenceContext
    EntityManager em;


    @GetMapping("is-ready")
    @Transactional
    public void readinessTest() {
        var entity = new Test1Entity();
        entity.setName("sdavsda");

        em.persist(entity);
    }
}
