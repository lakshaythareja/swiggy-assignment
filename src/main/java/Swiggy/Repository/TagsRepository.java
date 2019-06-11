package Swiggy.Repository;

import Swiggy.Model.Tags;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TagsRepository extends CrudRepository<Tags,Long> {

    Tags getTagsByTagId(@Param(value = "id")Long id);

    Tags getTagsByName(@Param(value = "name")String name);

}
