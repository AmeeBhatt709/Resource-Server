package com.example.resourceServer.repository;

import com.example.resourceServer.entity.RoleUSerMapping;
import com.example.resourceServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleUserMappingRepository extends JpaRepository<RoleUSerMapping,Long> {

Optional<RoleUSerMapping> findByUser(User user);

}
