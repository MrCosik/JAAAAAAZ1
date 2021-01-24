package pl.edu.pjwstk.jaz.zad2.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.jaz.zad2.AppAuthentication;
import pl.edu.pjwstk.jaz.zad2.entities.AuctionEntity;
import pl.edu.pjwstk.jaz.zad2.entities.CategoryEntity;
import pl.edu.pjwstk.jaz.zad2.entities.UserEntity;
import pl.edu.pjwstk.jaz.zad2.exception.NoCategoryException;
import pl.edu.pjwstk.jaz.zad2.request.AuctionRequest;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Transactional
@Service
public class AuctionService {

    EntityManager em;
    CategoryService categoryService;


    public AuctionService(EntityManager em, CategoryService categoryService) {
        this.em = em;
        this.categoryService = categoryService;
    }

    public void createAuction(AuctionRequest auctionRequest, String currentUsersId) throws NoCategoryException {
        List<String> availableCategories = categoryService.showCategories();
        CategoryEntity category = getCategoryIdWithItsTitle(auctionRequest.getCategory());
        int photoPosition = 1;

        AuctionEntity newAuction = new AuctionEntity();
        newAuction.setTitle(auctionRequest.getTitle());
        newAuction.setDescription(auctionRequest.getDescription());
        newAuction.setCreatorsId(Long.parseLong(currentUsersId));


        if (availableCategories.contains(category.getTitle())) {
            newAuction.setCategoryId(category.getId());
        } else {
            throw new NoCategoryException("No such category");
        }
        for (String photoTitle : auctionRequest.getPhotos()) {
            newAuction.addPhoto(photoTitle, photoPosition);
            photoPosition++;
        }
        newAuction.setPrice(auctionRequest.getPrice());

        em.persist(newAuction);


    }


    public void editAuction(int id, AuctionRequest auctionRequest) {

    }

    public CategoryEntity getCategoryIdWithItsTitle(String categoryName) {
        try {
            return em.createQuery("SELECT ce FROM CategoryEntity ce WHERE ce.title = :title", CategoryEntity.class)
                    .setParameter("title", categoryName)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No such category");
        }
        return null;
    }


}
