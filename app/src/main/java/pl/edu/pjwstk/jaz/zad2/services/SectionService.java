package pl.edu.pjwstk.jaz.zad2.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.zad2.entities.CategoryEntity;
import pl.edu.pjwstk.jaz.zad2.entities.SectionEntity;
import pl.edu.pjwstk.jaz.zad2.exception.NoEmptyCategoryException;
import pl.edu.pjwstk.jaz.zad2.request.SectionRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

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
        if(sectionRequest.getCategories() != null){
        for(String e : sectionRequest.getCategories()){
            sectionEntity.addCategory(e);
        }
        }else {
            System.out.println("Empty List");
        }
    }

    public void updateSection(Long id, SectionRequest sectionRequest){
        SectionEntity updatedSection = em.find(SectionEntity.class,id);
        updatedSection.setTitle(sectionRequest.getTitle());
        updatedSection.clearSet();
        addCategoriesFromArray(sectionRequest,updatedSection);
        em.merge(updatedSection);
        flushAndClear();
        //getCategoriesWithoutSection();
    }

    private void flushAndClear() {
        em.flush();
        em.clear();
    }

//
//    public void getCategoriesWithoutSection(){
//        em.createQuery("delete from CategoryEntity where sectionId IS NULL").executeUpdate();
//
//    }

}
