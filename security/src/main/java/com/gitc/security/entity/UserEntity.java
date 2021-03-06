package com.gitc.security.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Email(message = "*Please provide a valid Email")
  @NotEmpty(message = "*Please provide an email")
  private String email;

  @Length(min = 5, message = "*Your password must have at least 5 characters")
  @NotEmpty(message = "*Please provide your password")
  private String password;

  @NotEmpty(message = "*Please provide your name")
  private String name;


  @Column(name = "last_name")
  @NotEmpty(message = "*Please provide your last name")
  private String lastName;


  @Column(name = "active")
  private int active;


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> roles;
}
