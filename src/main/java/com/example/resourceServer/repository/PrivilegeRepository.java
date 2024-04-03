package com.example.resourceServer.repository;

import com.example.resourceServer.entity.Privilege;
import com.example.resourceServer.entity.Role;
import com.example.resourceServer.entity.RoleUSerMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Optional<Privilege> findByRoleUSerMapping(RoleUSerMapping roleUSerMapping);


}
