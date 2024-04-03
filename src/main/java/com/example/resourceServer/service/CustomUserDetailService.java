//package com.example.resourceServer.service;
//
//import com.example.resourceServer.entity.Privilege;
//import com.example.resourceServer.entity.Role;
//import com.example.resourceServer.entity.User;
//import com.example.resourceServer.repository.PrivilegeRepository;
//import com.example.resourceServer.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final PrivilegeRepository privilegeRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder(11);
//    }
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmail(username);
//
//
//        if (user == null) {
//            throw new UsernameNotFoundException("No user found");
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                true,
//                true,
//                true,
//                true,
//                getAuthorities(user.getRoles())
//
//        );
//
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//                List<String> strings=privilegeRepository.findByRoles(roles).stream().map(Privilege::getName).toList();
//
//        for (String privilege :strings) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }
//}
