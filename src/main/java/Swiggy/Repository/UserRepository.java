package Swiggy.Repository;

import Swiggy.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {
    User getUserByNameIgnoreCase(@Param(value = "name")String name);

    User getUserByUserId(@Param(value = "userId")Long userId);
}
