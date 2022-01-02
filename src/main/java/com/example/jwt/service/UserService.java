package com.example.jwt.service;

import com.example.jwt.domain.Authority;
import com.example.jwt.domain.User;
import com.example.jwt.enums.AuthorityNames;
import com.example.jwt.repository.AuthorityRepository;
import com.example.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    @Transactional
    public void initUserData() {

        Set<Authority> authorities = new HashSet<>();

        authorities.add(Authority.builder().name(AuthorityNames.ADMIN.getValue()).build());
        authorities.add(Authority.builder().name(AuthorityNames.USER.getValue()).build());

        authorityRepository.saveAll(authorities);

        User entity = User.builder()
                .email("admin")
                .password(passwordEncoder.encode("12345"))
                .authorities(authorities)
                .build();

        userRepository.save(entity);

    }

}
