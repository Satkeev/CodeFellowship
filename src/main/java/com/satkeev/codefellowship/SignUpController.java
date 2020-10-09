package com.satkeev.codefellowship;

import com.satkeev.codefellowship.ApplicationUser;
import com.satkeev.codefellowship.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class SignUpController {
    @Autowired
    private ApplicationUserRepository applicationUserRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Shows the signup page
    @GetMapping("/signup")
    public String show(Model m, Principal p) {
        m.addAttribute("title", "Sign Up");
        if (p == null) {
            m.addAttribute("loggedIn", false);
        } else {
            m.addAttribute("loggedIn", true);
            m.addAttribute("user", ((UsernamePasswordAuthenticationToken) p).getPrincipal());

        }
        return "signup";
    }


    //part of this comes from https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
    //Omitting the @RequestBody came from https://stackoverflow.com/questions/33796218/content-type-application-x-www-form-urlencodedcharset-utf-8-not-supported-for
    //Takes the user information from the form, hashes the password, and saves the new user into the database
    @PostMapping("/signup")
    public RedirectView create(ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepo.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }
}