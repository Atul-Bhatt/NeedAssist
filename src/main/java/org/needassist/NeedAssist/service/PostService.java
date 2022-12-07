package org.needassist.NeedAssist.service;

import org.needassist.NeedAssist.model.Post;
import org.needassist.NeedAssist.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    Post post = new Post();

    public void createNewPost() {
        post.setHeading("My First Post.");
        post.setBody("Even since I started learning to program, I haven't slept.");
        postRepository.save(post);
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
