package Swiggy.Controller;

import Swiggy.Model.Tags;
import Swiggy.Repository.TagsRepository;
import Swiggy.Utility.UtilityFunctions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagsController {

    private final TagsRepository tagsRepository;

    public TagsController(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    @RequestMapping(value = "/tags/create", method = RequestMethod.POST)
    String createTag(
            @RequestParam(value = "name")String name
    ){
        Tags tag = tagsRepository.save(new Tags(name));
        if(tag!= null)
            return UtilityFunctions.createMessage(200,"Created successfully").toString();
        else
            return UtilityFunctions.createMessage(500,"Unable to create tag").toString();

    }

}
