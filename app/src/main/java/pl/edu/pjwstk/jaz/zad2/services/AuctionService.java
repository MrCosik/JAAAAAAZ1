package pl.edu.pjwstk.jaz.zad2.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.jaz.zad2.entities.*;
import pl.edu.pjwstk.jaz.zad2.exception.NoCategoryException;
import pl.edu.pjwstk.jaz.zad2.request.AuctionRequest;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AuctionService {

    EntityManager em;
    CategoryService categoryService;


    public AuctionService(EntityManager em, CategoryService categoryService) {
        this.em = em;
        this.categoryService = categoryService;
    }

    public void createAuction(AuctionRequest auctionRequest) throws NoCategoryException {
        //check if category from request exists in DB
        List<String> availableCategories = categoryService.showCategories();
        CategoryEntity category = getCategoryIdWithItsTitle(auctionRequest.getCategory());
        int photoPosition = 1;

        AuctionEntity newAuction = new AuctionEntity();





        //get current logged in user's name
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsersName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUsersName = authentication.getName();
            System.out.println(currentUsersName);
        }

        //create new auction and set its parameters

        newAuction.setTitle(auctionRequest.getTitle());
        newAuction.setDescription(auctionRequest.getDescription());

        if (currentUsersName != null)
            newAuction.setCreatorsId(Long.parseLong(currentUsersName));


        if (availableCategories.contains(category.getTitle())) {
            newAuction.setCategoryId(category.getId());
        } else {
            throw new NoCategoryException("No such category");
        }

        //add photos' links to a set
        for (String photoTitle : auctionRequest.getPhotos()) {
            newAuction.addPhoto(photoTitle, photoPosition);
            photoPosition++;
        }
        newAuction.setPrice(auctionRequest.getPrice());

        em.persist(newAuction);

        auctionRequest.getParameters().forEach((k,v) -> {
                    ParameterEntity parameterEntity = getParameterEntity(k);
                    if(getParameterEntity(k) == null){
                        parameterEntity = new ParameterEntity(k);
                        em.persist(parameterEntity);
                    }

                    flushAndClear();
                    AuctionParameterEntity auctionParameterEntity = new AuctionParameterEntity(newAuction,parameterEntity,v);
                    auctionParameterEntity.setAuctionParameterKey(new AuctionParameterPk(newAuction.getId(),parameterEntity.getId() ));
                    newAuction.addAuctionParameter(parameterEntity,v, auctionParameterEntity);

//                em.persist(auctionParameterEntity);
                }
        );
        em.merge(newAuction);




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

    public ParameterEntity getParameterEntity(String parameterName){
        try {
            return em.createQuery("SELECT pe FROM ParameterEntity pe WHERE pe.key = :keyName", ParameterEntity.class)
                    .setParameter("keyName", parameterName)
                    .setMaxResults(1)
                    .getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void flushAndClear(){
        em.flush();
        em.clear();
    }


}
