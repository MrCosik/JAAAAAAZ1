package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class ReadinessController {
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("is-ready")
    public void readinessTest() {
//        var entity = new Test1Entity();
//        entity.setName("sdavsda");
//        em.persist(entity);
    }
}
