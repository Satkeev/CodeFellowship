package com.satkeev.codefellowship;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import java.security.Principal;

    @Entity
    public class ApplicationUser implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(unique = true)
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String bio;

       @OneToMany(mappedBy = "postedBy")
        private List<Post> posts;
        @ManyToMany
        @JoinTable(
                name="followed_users",
                joinColumns = {@JoinColumn(name="follower_id")},
                inverseJoinColumns = {@JoinColumn(name = "followee_id")}
        )
        public Set<ApplicationUser> followedUsers;

        public long getId() {
            return this.id;
        }

        public String getUsername() {
            return this.username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        public String getPassword() {
            return this.password;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public String getDateOfBirth() {
            return this.dateOfBirth;
        }

        public String getBio() {
            return this.bio;
        }

        public String toString() {
            return this.username;
        }

        public static ApplicationUser convertPrincipal(Principal p, ApplicationUserRepository repo) {
            ApplicationUser user = (ApplicationUser) (((UsernamePasswordAuthenticationToken) p).getPrincipal());
            user = repo.findById(user.getId()).get();

            return user;
        }

        public List<Post> getPosts() {
            return this.posts;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

