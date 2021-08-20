package com.gitc.security.repository;

import com.gitc.security.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
  RoleEntity findByRole(String role);
}
