package com.example.resourceServer.repository;


import com.example.resourceServer.entity.Page;
import com.example.resourceServer.entity.Role;
import com.example.resourceServer.entity.RoleUSerMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PageRepository extends JpaRepository<Page,Long> {
    Optional<Page> findByNameAndRoleUSerMapping(String name, RoleUSerMapping roleUSerMapping);
}
