package org.needassist.NeedAssist.service;

import org.needassist.NeedAssist.model.Comment;
import org.needassist.NeedAssist.model.Post;
import org.needassist.NeedAssist.repository.CommentRepository;
import org.needassist.NeedAssist.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    Post post = new Post();

    public String getUserInfoFromContext(String fieldName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Method method = auth.getPrincipal().getClass().getMethod(fieldName, null);
        return String.valueOf(method.invoke(auth.getPrincipal(),null));
    }

    public void createNewPost() {
        post.setHeading("My First Post.");
        post.setBody("Even since I started learning to program, I haven't slept.");
        postRepository.save(post);
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAllPostsWithUsername();
    }

    public Iterable<Post> getMyPosts() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return postRepository.findAllByUserId(Integer.parseInt(getUserInfoFromContext("getUserId")));
    }

}
