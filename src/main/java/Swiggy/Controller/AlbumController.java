package Swiggy.Controller;

import Swiggy.Model.Album;
import Swiggy.Repository.AlbumRepository;
import Swiggy.Utility.UtilityFunctions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumController {

    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository= albumRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/album/create")
    String createAlbum(
            @RequestParam(name = "name")String name
    ){
        Album album = albumRepository.save(new Album(name,0));
        if(album != null)
            return UtilityFunctions.createMessage(200,"Created successfully").toString();
        else
            return UtilityFunctions.createMessage(500,"Unable to create album").toString();
    }
}

