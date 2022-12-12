package org.needassist.NeedAssist.repository;

import org.needassist.NeedAssist.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value="select * from users where username=?1", nativeQuery = true)
    Optional<User> userAlreadyExists(String username);
}
