package com.boussas.blog.security;

import com.boussas.blog.entities.User;
import com.boussas.blog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public class BlogUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public BlogUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        return new BlogUserDetails(user);
    }
}
