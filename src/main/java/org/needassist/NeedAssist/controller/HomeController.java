package org.needassist.NeedAssist.controller;

import org.needassist.NeedAssist.model.Post;
import org.needassist.NeedAssist.model.User;
import org.needassist.NeedAssist.repository.PostRepository;
import org.needassist.NeedAssist.repository.UserRepository;
import org.needassist.NeedAssist.service.JpaUserDetailsService;
import org.needassist.NeedAssist.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void postSubmit(@ModelAttribute Post post, Model model) {
        model.addAttribute("post", post);
        post.setUserId(1);
        postRepository.save(post);
    }

}
