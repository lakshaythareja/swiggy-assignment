package Swiggy.Repository;

import Swiggy.Model.Song;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SongRepository extends CrudRepository<Song,Long> {

    Song getSongBySongId(@Param("songId")Long songId);

    Song getSongByNameIgnoreCase(@Param("name")String name);

    @Modifying
    @Transactional
    @Query("UPDATE  song SET likes = likes + 1 where song_id = :id")
    void updateLikes(@Param(value = "id")Long id);

    @Modifying
    @Transactional
    @Query("UPDATE  song SET plays = plays + 1 where song_id = :id")
    void updatePlay(@Param(value = "id")Long id);

    @Query(value = "SELECT * from song WHERE song_id IN ( select song_id FROM song_tags where tag_id = :tagId)",nativeQuery = true)
    List<Song> selectSongsByTag(@Param(value = "tagId")Long tagId);

    @Query(value = "SELECT distinct(t.name)from tags t JOIN song_tags st ON t.tag_id = st.tag_id WHERE st.song_id = :songId",nativeQuery = true)
    List<String> selectTagsBySong(@Param("songId") Long songId);
}
