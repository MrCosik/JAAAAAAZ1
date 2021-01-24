package pl.edu.pjwstk.jaz.zad2.controler;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.zad2.exception.NoCategoryException;
import pl.edu.pjwstk.jaz.zad2.request.AuctionRequest;
import pl.edu.pjwstk.jaz.zad2.services.AuctionService;

@RestController
public class AuctionController {

    AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/createAuction")
    public void createNewAuction(@RequestBody AuctionRequest auctionRequest) throws NoCategoryException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsersId = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUsersId = authentication.getName();
            System.out.println(currentUsersId);
        }
        auctionService.createAuction(auctionRequest, currentUsersId);

    }

    @PostMapping("/editAuction/{id}")
    public void editAuction(@PathVariable int id, @RequestBody AuctionRequest auctionRequest){
        auctionService.editAuction(id,auctionRequest);
    }

}
