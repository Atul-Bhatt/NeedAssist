package org.needassist.NeedAssist.controller;

import org.needassist.NeedAssist.model.Post;
import org.needassist.NeedAssist.repository.PostRepository;
import org.needassist.NeedAssist.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@Controller
public class HomeController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("allPosts", postService.getAllPosts());
        return "home";
    }

    @GetMapping("/submit")
    public String submit(Model model) {
        model.addAttribute("post", new Post());
        return "submit";
    }

    @PostMapping("/submit")
    public void postSubmit(@ModelAttribute Post post, Model model) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        model.addAttribute("post", post);
        post.setUserId(postService.getUserIdFromContext());
        postRepository.save(post);
    }

    @GetMapping("/myposts")
    public String myposts(Model model) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        model.addAttribute("myPosts", postService.getMyPosts());
        return "myposts";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable("postId") int postId) {
        try {
            postRepository.deleteById(postId);
        } catch(EmptyResultDataAccessException e) {
            System.out.println("Post Id does not exist!");
        }
        return "home";
    }

}
