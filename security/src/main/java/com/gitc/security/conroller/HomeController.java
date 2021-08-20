package com.gitc.security.conroller;

import com.gitc.security.entity.UserEntity;
import com.gitc.security.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  private final UserService userService;

  public HomeController(UserService userService) {
    this.userService = userService;
  }

//  @PreAuthorize("ADMIN")
  @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
  public ModelAndView adminHome() {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserEntity user = userService.findUserByEmail(auth.getName());
    modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
    modelAndView.setViewName("admin/home");
    return modelAndView;
  }

//  @PreAuthorize("USER")
  @RequestMapping(value = "/user/home", method = RequestMethod.GET)
  public ModelAndView userHome() {
    ModelAndView modelAndView = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserEntity user = userService.findUserByEmail(auth.getName());
    modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
    modelAndView.addObject("userMessage", "Content Available Only for Users with User Role");
    modelAndView.setViewName("user/home");
    return modelAndView;
  }
}
