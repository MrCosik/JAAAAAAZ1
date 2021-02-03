package pl.edu.pjwstk.jaz.zad2.services;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jaz.zad2.entities.CategoryEntity;
import pl.edu.pjwstk.jaz.zad2.entities.SectionEntity;
import pl.edu.pjwstk.jaz.zad2.exception.NoEmptyCategoryException;
import pl.edu.pjwstk.jaz.zad2.exception.NoSectionException;
import pl.edu.pjwstk.jaz.zad2.exception.WrongSectionRequestException;
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

    public void createSection(SectionRequest sectionRequest) {

        if (sectionRequest.getTitle() != null && !sectionRequest.getCategories().isEmpty() && !sectionRequest.getTitle().equals("")) {
            var sectionEntity = new SectionEntity();
            sectionEntity.setTitle(sectionRequest.getTitle());
            addCategoriesFromArray(sectionRequest, sectionEntity);
            em.persist(sectionEntity);
            flushAndClear();
            System.out.println("Sectione added");
        } else throw new WrongSectionRequestException("Not enough data");


    }


    public void updateSection(Long id, SectionRequest sectionRequest) {

            SectionEntity updatedSection = em.find(SectionEntity.class, id);
            if (updatedSection != null || !sectionRequest.getTitle().equals("") || !sectionRequest.getCategories().isEmpty()) {
                if(sectionRequest.getTitle() != null)
                    updatedSection.setTitle(sectionRequest.getTitle());
                else throw new NoSectionException("Empty title");
                updatedSection.clearSet();
                addCategoriesFromArray(sectionRequest, updatedSection);
                em.merge(updatedSection);
                flushAndClear();
            } else {
                throw new NoSectionException("No section under this id");
            }
        //getCategoriesWithoutSection();
    }

    private void flushAndClear() {
        em.flush();
        em.clear();
    }

    private void addCategoriesFromArray(SectionRequest sectionRequest, SectionEntity sectionEntity) {
        if (sectionRequest.getCategories() != null) {
            for (String e : sectionRequest.getCategories()) {
                sectionEntity.addCategory(e);
            }
        } else {
            System.out.println("Empty List");
        }
    }


}
