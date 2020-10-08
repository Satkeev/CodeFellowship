package com.satkeev.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ApplicationUserRepository appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser appUser = this.appUserRepo.findByUsername(username);

        if (appUser == null) {
            System.out.println("Could not find user with Username " + username);
            throw new UsernameNotFoundException("Could not find an entry for " + username + " in the database");
        }

        System.out.println("Found user: " + appUser.getUsername());

        return appUser;
    }
}