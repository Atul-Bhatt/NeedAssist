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
    UserRepository userRepository;

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        userRepository.save(new User("user1", "password1", "user_role"));
        userRepository.save(new User("user2", "password2", "admin_role"));
        model.addAttribute("allPosts", postService.getAllPosts());
        return "home";
    }

}
