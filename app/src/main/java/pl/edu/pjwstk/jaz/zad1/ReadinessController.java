package pl.edu.pjwstk.jaz.zad1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.readiness.Test1Entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class ReadinessController {
    //@PersistenceContext
    private final EntityManager entityManager;

    public ReadinessController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PreAuthorize("hasAnyAuthority('view-readiness')")
    @Transactional
    @GetMapping("auth0/is-ready")
    public void readinessTest() {
        var entity = new Test1Entity();
        entity.setName("fdsafdsafdas");
        entityManager.persist(entity);
    }
}
