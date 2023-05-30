package org.needassist.NeedAssist.repository;

import org.needassist.NeedAssist.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Query(value="select * from comments where post_id=?1", nativeQuery = true)
    public Iterable<Comment> findAllByPostId(int postId);
}
