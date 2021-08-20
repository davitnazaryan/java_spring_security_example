package com.gitc.security.conroller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/api/admin/preauthorize/admin")
  public String preAuthorizeAdmin() {
    return "preauthorize/admin";
  }

  @PostAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/api/admin/postauthorize/admin")
  public String postAuthorizeAdmin() {
    System.out.println("postauthorize/admin");
    return "postauthorize/admin";
  }
}
