package Swiggy.Repository;

import Swiggy.Model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ArtistRepository extends CrudRepository<Artist,Long> {
    public Artist getArtistByArtistId(@Param(value = "id") Long Id);
}
