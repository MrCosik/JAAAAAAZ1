package pl.edu.pjwstk.jaz.zad2.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.zad2.entities.SectionEntity;
import pl.edu.pjwstk.jaz.zad2.request.SectionRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@Service
public class SectionService {

    EntityManager em;

    public SectionService(EntityManager em) {
        this.em = em;
    }

    public void createSection(SectionRequest sectionRequest){
        var sectionEntity = new SectionEntity();
        sectionEntity.setTitle(sectionRequest.getTitle());
        addCategoriesFromArray(sectionRequest, sectionEntity);
        em.persist(sectionEntity);
    }

    private void addCategoriesFromArray(SectionRequest sectionRequest, SectionEntity sectionEntity) {
        for(String e : sectionRequest.getCategories()){
            sectionEntity.addCategory(e);
        }
    }
}
