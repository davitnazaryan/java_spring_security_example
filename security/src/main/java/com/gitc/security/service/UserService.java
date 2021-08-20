package com.gitc.security.service;

import com.gitc.security.entity.RoleEntity;
import com.gitc.security.entity.UserEntity;
import com.gitc.security.repository.RoleRepository;
import com.gitc.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service("userService")
public class UserService {

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository,
                     RoleRepository roleRepository,
                     PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserEntity findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public void saveUser(UserEntity user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setActive(1);
    RoleEntity userRole = roleRepository.findByRole("ADMIN");
    user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
    userRepository.save(user);
  }

}