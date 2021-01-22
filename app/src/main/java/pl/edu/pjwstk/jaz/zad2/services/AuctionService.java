package pl.edu.pjwstk.jaz.zad2.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.jaz.zad2.entities.AuctionEntity;
import pl.edu.pjwstk.jaz.zad2.request.AuctionRequest;

import javax.persistence.EntityManager;
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

    public void createAuction(AuctionRequest auctionRequest){
        List<String> availableCategories = categoryService.showCategories();
        int photoPosition = 1;

        AuctionEntity newAuction = new AuctionEntity();
        newAuction.setTitle(auctionRequest.getTitle());
        newAuction.setDescription(auctionRequest.getDescription());
        for(String availableCategory : availableCategories){
            for(String receivedCategory : auctionRequest.getCategoryEntity()){
                if(receivedCategory.equals(availableCategory)){
//                    newAuction.addCategory(receivedCategory);
                }
            }
        }

        for (String photoTitle : auctionRequest.getPhotos()){
            newAuction.addPhoto(photoTitle, photoPosition);
            photoPosition++;
        }
        em.persist(newAuction);





    }

    public void editAuction(int id, AuctionRequest auctionRequest) {

    }
}
