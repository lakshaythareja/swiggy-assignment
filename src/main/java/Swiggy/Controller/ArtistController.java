package Swiggy.Controller;

import Swiggy.Model.Artist;
import Swiggy.Repository.ArtistRepository;
import Swiggy.Utility.UtilityFunctions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/artist/create")
    String createArtist(
            @RequestParam(name = "name")String name
    ){
        try {
            Artist artist = artistRepository.save(new Artist(name));
            if (artist != null)
                return UtilityFunctions.createMessage(200, "Created successfully").toString();
            else
                return UtilityFunctions.createMessage(500, "Unable to create artist").toString();
        }catch (Exception e){
            return UtilityFunctions.createMessage(500, "Unable to create artist").toString();
        }
    }
}
