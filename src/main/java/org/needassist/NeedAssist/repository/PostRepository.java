package org.needassist.NeedAssist.repository;

import org.needassist.NeedAssist.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query(value="select * from posts where user_id=?1", nativeQuery = true)
    public Iterable<Post> findAllByUserId(int user_id);
}
