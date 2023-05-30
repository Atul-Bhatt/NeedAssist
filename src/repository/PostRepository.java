package org.needassist.NeedAssist.repository;

import org.needassist.NeedAssist.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends CrudRepository<Post, Integer> {
    @Query(value="select * from posts where user_id=?1", nativeQuery = true)
    public Iterable<Post> findAllByUserId(int user_id);

    @Query(value="select posts.id, posts.heading, posts.body, posts.creation_date, last_updated, posts.user_id, users.username from posts inner join users on posts.user_id = users.id", nativeQuery = true)
    public Iterable<Post> findAllPostsWithUsername();

//    @Query(value="select username from users where users.id in (select user_id from posts where posts.id=?1)", nativeQuery = true)
//    public Post findUsernameByPostId(int post_id);

}
