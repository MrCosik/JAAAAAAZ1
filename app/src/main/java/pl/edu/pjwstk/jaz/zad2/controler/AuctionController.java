package pl.edu.pjwstk.jaz.zad2.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.zad2.entities.AuctionEntity;
import pl.edu.pjwstk.jaz.zad2.exception.NoCategoryException;
import pl.edu.pjwstk.jaz.zad2.request.AuctionRequest;
import pl.edu.pjwstk.jaz.zad2.request.PhotoRequest;
import pl.edu.pjwstk.jaz.zad2.services.AuctionService;

import java.util.List;

@RestController
public class AuctionController {



    AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    @PreAuthorize("hasAnyAuthority('user')")
    @PostMapping("/createAuction")
    public void createNewAuction(@RequestBody AuctionRequest auctionRequest) throws NoCategoryException {
        auctionService.createAuction(auctionRequest);

    }

    @PreAuthorize("hasAnyAuthority('user')")
    @PostMapping("/editAuction/{id}")
    public void editAuction(@PathVariable Long id, @RequestBody AuctionRequest auctionRequest){
        auctionService.editAuction(id,auctionRequest);
    }

    @PreAuthorize("hasAnyAuthority('user')")
    @GetMapping("/Auctions")
    public List listAuction(){
        return auctionService.showAuctionsWithOnePhoto();
    }

    @PreAuthorize("hasAnyAuthority('user')")
    @PostMapping("/editPhoto/{id}")
    public void editAuctionsPhoto(@PathVariable Long id, @RequestBody PhotoRequest photoRequest){
        auctionService.editPhotosInAuction(id,photoRequest);
    }

}
