package Swiggy.Controller;

import Swiggy.Model.*;
import Swiggy.Repository.*;
import Swiggy.Utility.UtilityFunctions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SongController {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final TagsRepository tagsRepository;
    private final SongTagsRepository songTagsRepository;
    public SongController(SongRepository songRepository, AlbumRepository albumRepository, ArtistRepository artistRepository, TagsRepository tagsRepository, SongTagsRepository songTagsRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.tagsRepository = tagsRepository;
        this.songTagsRepository = songTagsRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/song/create")
    String createSong(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "artistId")Long artistId,
            @RequestParam(name = "albumId") Long albumId
    ){
        Album album = albumRepository.getAlbumByAlbumId(albumId);
        if(album==null)
            return UtilityFunctions.createMessage(500,"Unable to create song, no such album exists").toString();

        Artist artist = artistRepository.getArtistByArtistId(artistId);
        if(artist==null)
            return UtilityFunctions.createMessage(500,"Unable to create song, no such artist exists").toString();
        Song song = songRepository.save(new Song(name,album,artist));
        if(song!=null)
            albumRepository.updateTracks(albumId);
        if(song != null)
            return UtilityFunctions.createMessage(200,"Created successfully").toString();
        else
            return UtilityFunctions.createMessage(500,"Unable to create song").toString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/song/delete")
    String deleteSong(
            @RequestParam(name = "name", required=false)String name
    ){

        Song song = songRepository.getSongByNameIgnoreCase(name);
        if(song != null) {
            songRepository.delete(song);
            return UtilityFunctions.createMessage(200, "Deleted successfully").toString();
        }
        else
            return UtilityFunctions.createMessage(500,"Unable to delete song, since its not present").toString();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/song/get")
    String getSong(
            @RequestParam(name = "name")String name
    ){
        Song song = songRepository.getSongByNameIgnoreCase(name);
        if(song!=null) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("code",200);
                jsonObject.put("song",new JSONObject(song));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject.toString();
        }
        else
            return UtilityFunctions.createMessage(500,"Error, song doesn't exist").toString();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/song/tag")
    String tagSong(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "tag") Long tagId
    ){
        Song song = songRepository.getSongByNameIgnoreCase(name);
        if(song==null)
            return UtilityFunctions.createMessage(500,"Error, no such song exists").toString();
        Tags tag = tagsRepository.getTagsByTagId(tagId);
        if(tag == null)
            return UtilityFunctions.createMessage(500,"Error, no such tag exists").toString();

        SongTags songTag = songTagsRepository.save(new SongTags(song,tag));
        if(songTag!=null)
            return UtilityFunctions.createMessage(200, "Created succesfully").toString();

        return UtilityFunctions.createMessage(500, "Error creating song tag").toString();
    }

    @RequestMapping(value = "/explore", method = RequestMethod.GET)
    @ResponseBody
    String explore(
            @RequestParam(value = "tags")List<Long> tags
    ){
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<String> localTags= new ArrayList<>();
        for(Long tag : tags){
            List<Song> local = songRepository.selectSongsByTag(tag);
            Tags localTag = tagsRepository.getTagsByTagId(tag);
            if(localTag!=null)
                localTags.add(localTag.getName());
            else
                System.out.println("tag is empty "+tag);
            songs.addAll(local);
        }
        List<Song> songsWithoutDuplicates = songs.stream()
                .distinct()
                .sorted((o1, o2) -> {
                    if(o1.getPlays()>o2.getPlays())
                        return -1;
                    else if(o1.getPlays()<o2.getPlays())
                        return 1;
                    else{
                        return Integer.compare(o2.getLikes(), o1.getLikes());
                    }
                })
                .collect(Collectors.toList());
        JSONObject jsonObject = new JSONObject();

        ArrayList<String> tagsToReturn = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();

        for(Song song : songsWithoutDuplicates){
            List<String> local = songRepository.selectTagsBySong(song.getSongId());
            jsonArray.put(new JSONObject(song));
            tagsToReturn.addAll(local);
        }
        List<String> tagsWithoutDuplicates = tagsToReturn.stream()
                .distinct()
                .collect(Collectors.toList());


        tagsWithoutDuplicates.removeAll(localTags);

        System.out.println(tagsWithoutDuplicates);
        try {
            jsonObject.put("tags",tagsWithoutDuplicates);
            jsonObject.put("songs",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
