package com.guide.run.global.security.user;

import com.guide.run.user.entity.User;
import com.guide.run.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String socialId) throws UsernameNotFoundException {
        System.out.println("야호");
        User user = userRepository.findById(socialId).orElseThrow();
        System.out.println("야호");
        return new CustomUserDetails(user);
    }
}
