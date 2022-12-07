package org.needassist.NeedAssist.controller;

import org.needassist.NeedAssist.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    PostService postService;

    @GetMapping("/")
    public String home() {
        postService.createNewPost();
        return "home";
    }

}
