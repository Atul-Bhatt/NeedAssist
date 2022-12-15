package org.needassist.NeedAssist.controller;

import org.needassist.NeedAssist.model.Post;
import org.needassist.NeedAssist.model.User;
import org.needassist.NeedAssist.repository.PostRepository;
import org.needassist.NeedAssist.repository.UserRepository;
import org.needassist.NeedAssist.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        model.addAttribute("username", postService.getUserInfoFromContext("getUsername"));
        model.addAttribute("allPosts", postService.getAllPosts());
        return "home";
    }

    @GetMapping("/submit")
    public String submit(Model model) {
        model.addAttribute("post", new Post());
        return "submit";
    }

    @PostMapping("/submit")
    public ModelAndView postSubmit(@ModelAttribute Post post) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        post.setUserId(Integer.parseInt(postService.getUserInfoFromContext("getUserId")));
        postRepository.save(post);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/update/{postId}")
    public ModelAndView postUpdate(@ModelAttribute Post post, @PathVariable("postId") int postId) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        post.setUserId(Integer.parseInt(postService.getUserInfoFromContext("getUserId")));
        post.setId(postId);
        postRepository.save(post);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/myposts")
    public String myposts(Model model) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        model.addAttribute("myPosts", postService.getMyPosts());
        return "myposts";
    }

    @GetMapping("/delete/{postId}")
    public ModelAndView deletePost(@PathVariable("postId") int postId) {
        try {
            postRepository.deleteById(postId);
        } catch(EmptyResultDataAccessException e) {
            System.out.println("Post Id does not exist!");
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/update/{postId}")
    public String update(Model model, @PathVariable("postId") int postId) {
        Optional<Post> post = postRepository.findById(postId);
        model.addAttribute("post", post.orElse(new Post()));
        model.addAttribute("postId", postId);
        return "update";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userData", new User());
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@ModelAttribute User user) {
        if(!userRepository.userAlreadyExists(user.getUsername()).isEmpty()) {
            return new ModelAndView("redirect:/register");
        }
        user.setRoles("user_role");
        userRepository.save(user);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/post/{postId}")
    public String showPost(Model model, @PathVariable("postId") int postId) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Optional<Post> post = postRepository.findById(postId);
        model.addAttribute("post", post.orElse(new Post()));
        model.addAttribute("username", postService.getUserInfoFromContext("getUsername"));
        return "post";
    }

}
