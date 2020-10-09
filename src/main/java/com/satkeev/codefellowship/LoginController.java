package com.satkeev.codefellowship;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String show(Model m, Principal p) {
        m.addAttribute("title", "Login");
        if (p == null) {
            m.addAttribute("loggedIn", false);
        } else {
            m.addAttribute("loggedIn", true);
            m.addAttribute("user", ((UsernamePasswordAuthenticationToken) p).getPrincipal());
        }
        return "login";
    }
}
