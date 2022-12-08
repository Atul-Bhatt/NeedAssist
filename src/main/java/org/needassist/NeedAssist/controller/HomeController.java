package org.needassist.NeedAssist.controller;

import org.needassist.NeedAssist.model.User;
import org.needassist.NeedAssist.repository.UserRepository;
import org.needassist.NeedAssist.service.JpaUserDetailsService;
import org.needassist.NeedAssist.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("allPosts", postService.getAllPosts());
        return "home";
    }

    @GetMapping("/submit")
    public String home() {
        return "submit";
    }

}
