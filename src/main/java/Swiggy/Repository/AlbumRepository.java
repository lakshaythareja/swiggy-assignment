package Swiggy.Repository;

import Swiggy.Model.Album;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AlbumRepository extends CrudRepository<Album,Long> {
    Album getAlbumByAlbumId(@Param(value = "id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE  album SET tracks = tracks + 1 where album_id = :id")
    void updateTracks(@Param(value = "id")Long id);
}
