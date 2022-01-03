package com.example.jwt.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewResource {

    @GetMapping("/error")
    public String viewerErrorPage() {
        return "error.html";
    }

}
