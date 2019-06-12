package Swiggy.Controller;

import Swiggy.Model.Song;
import Swiggy.Model.SongLike;
import Swiggy.Model.SongPlay;
import Swiggy.Model.User;
import Swiggy.Repository.SongLikeRepository;
import Swiggy.Repository.SongPlayRepository;
import Swiggy.Repository.SongRepository;
import Swiggy.Repository.UserRepository;
import Swiggy.Utility.UtilityFunctions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    private final SongRepository songRepository;

    private final SongLikeRepository songLikeRepository;

    private final SongPlayRepository songPlayRepository;

    public UserController(UserRepository userRepository, SongRepository songRepository, SongLikeRepository songLikeRepository, SongPlayRepository songPlayRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.songLikeRepository = songLikeRepository;
        this.songPlayRepository = songPlayRepository;
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    String createUser(
            @RequestParam(value = "name")String name
    ){
        try {
            User user = userRepository.save(new User(name));
            if (user != null)
                return UtilityFunctions.createMessage(200, "Created successfully").toString();
            else
                return UtilityFunctions.createMessage(500, "Unable to create user").toString();
        }catch (Exception e){
            return UtilityFunctions.createMessage(500, "Unable to create user").toString();
        }
    }

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    String like(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "songId") Long songId
    ){
        Song song = songRepository.getSongBySongId(songId);
        if(song==null)
            return UtilityFunctions.createMessage(500,"Song doesn't exist").toString();

        User user = userRepository.getUserByUserId(userId);
        if(user==null)
            return UtilityFunctions.createMessage(500,"User doesn't exist").toString();

        try {
            SongLike songLike = songLikeRepository.save(new SongLike(song, user));
            if (songLike != null) {
                songRepository.updateLikes(songId);
                return UtilityFunctions.createMessage(200, "Like successfully").toString();
            } else
                return UtilityFunctions.createMessage(500, "Unable to like").toString();
        }catch (Exception e){
            return UtilityFunctions.createMessage(500, "Unable to like").toString();
        }
    }


    @RequestMapping(value = "/play", method = RequestMethod.POST)
    String play(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "songId") Long songId
    ){
        Song song = songRepository.getSongBySongId(songId);
        if(song==null)
            return UtilityFunctions.createMessage(500,"Song doesn't exist").toString();

        User user = userRepository.getUserByUserId(userId);
        if(user==null)
            return UtilityFunctions.createMessage(500,"User doesn't exist").toString();

        try {
            SongPlay songPlay = songPlayRepository.save(new SongPlay(song, user));
            if (songPlay != null) {
                songRepository.updatePlay(songId);
                return UtilityFunctions.createMessage(200, "Played successfully").toString();
            } else
                return UtilityFunctions.createMessage(500, "Unable to play").toString();
        }catch (Exception e){
            return UtilityFunctions.createMessage(500, "Unable to play").toString();
        }
    }
}
