package pl.edu.pjwstk.jaz.zad2.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Service
public class CategoryService {

    EntityManager em;

    public CategoryService(EntityManager em) {
        this.em = em;
    }

    public List showCategories(){
        return em.createQuery("select ce.title from CategoryEntity ce group by ce.title")
                .getResultList();
    }
}
