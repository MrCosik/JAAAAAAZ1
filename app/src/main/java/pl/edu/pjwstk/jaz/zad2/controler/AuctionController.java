package pl.edu.pjwstk.jaz.zad2.controler;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.zad2.entities.AuctionEntity;
import pl.edu.pjwstk.jaz.zad2.exception.NoCategoryException;
import pl.edu.pjwstk.jaz.zad2.request.AuctionRequest;
import pl.edu.pjwstk.jaz.zad2.services.AuctionService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class AuctionController {

    AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/createAuction")
    public void createNewAuction(@RequestBody AuctionRequest auctionRequest) throws NoCategoryException {
        auctionService.createAuction(auctionRequest);

    }

    @PostMapping("/editAuction/{id}")
    public void editAuction(@PathVariable Long id, @RequestBody AuctionRequest auctionRequest){
        auctionService.editAuction(id,auctionRequest);
    }

    @GetMapping("/api/auctions")
    public List<AuctionEntity> listAuction(){
        return auctionService.returnAuctions();
    }


}
