package com.pi.restful_library.security;

import com.pi.restful_library.model.Members;
import com.pi.restful_library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Members members = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Members not found with email: " + email));
        return new CustomUserDetails(members);
    }
}