package com.example.resourceServer.service.impl;


import com.example.resourceServer.dto.UserRequestDto;
import com.example.resourceServer.entity.Privilege;
import com.example.resourceServer.entity.Role;
import com.example.resourceServer.entity.RoleUSerMapping;
import com.example.resourceServer.entity.User;
import com.example.resourceServer.repository.PrivilegeRepository;
import com.example.resourceServer.repository.RoleRepository;
import com.example.resourceServer.repository.RoleUserMappingRepository;
import com.example.resourceServer.repository.UserRepository;
import com.example.resourceServer.service.UserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleUserMappingRepository roleUserMappingRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PrivilegeRepository privilegeRepository;

    @Override
    public void addUser(UserRequestDto userRequestDto) {

        User user = new User(null, userRequestDto.getEmail(), passwordEncoder.encode(userRequestDto.getPassword()));
        Role role = this.getUserRole("USER");
        RoleUSerMapping roleUSerMapping = new RoleUSerMapping(null, role, user);
        roleUserMappingRepository.save(roleUSerMapping);
        userRepository.save(user);
    }

    @Override
    public List<UserRequestDto> getAllUser() {
       return userRepository.findAll().stream().map(user -> new UserRequestDto(user.getId(),user.getEmail())).collect(Collectors.toList());
    }

    @Override
    public void updatePrivileges(List<UserRequestDto> userRequestDto) {

        List<User> users = userRequestDto.stream()
                .map(userRequestDto1 -> this.userRepository.findById(userRequestDto1.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        for (User user : users) {
            RoleUSerMapping roleUSerMapping = this.roleUserMappingRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("RoleUserMapping not found for user: " + user.getId()));

            Privilege privilege = this.privilegeRepository.findByRoleUSerMapping(roleUSerMapping)
                    .orElse(new Privilege(null, false, false, false, roleUSerMapping));

            // Assuming userRequestDto contains the privileges for the current user
            UserRequestDto currentUserRequestDto = userRequestDto.stream()
                    .filter(dto -> dto.getId().equals(user.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("UserRequestDto not found for user: " + user.getId()));

            privilege.setRemoveAccess(currentUserRequestDto.getRemove() != null && currentUserRequestDto.getRemove());
            privilege.setWriteAccess(currentUserRequestDto.getWrite() != null && currentUserRequestDto.getWrite());
            privilege.setReadAccess(currentUserRequestDto.getRead() != null && currentUserRequestDto.getRead());
            privilege.setRoleUSerMapping(roleUSerMapping);

            System.out.println("privilege.getReadAccess() = " + privilege.getReadAccess());
            privilegeRepository.save(privilege);
        }
    }


    private Role getUserRole(String user) {
        return roleRepository.findByName(user).orElse(null);
    }
}
