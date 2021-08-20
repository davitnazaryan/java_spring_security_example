package com.gitc.security.conroller;

import com.gitc.security.entity.UserEntity;
import com.gitc.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

  private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView registration() {
    ModelAndView modelAndView = new ModelAndView();
    UserEntity user = new UserEntity();
    modelAndView.addObject("userEntity", user);
    modelAndView.setViewName("register");
    return modelAndView;
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView createNewUser(@Valid UserEntity user, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();
    UserEntity userExists = userService.findUserByEmail(user.getEmail());
    if (userExists != null) {
      bindingResult
          .rejectValue("email", "error.userEntity",
              "There is already a user registered with the email provided");
    }
    if (!bindingResult.hasErrors()) {
      userService.saveUser(user);
      modelAndView.addObject("successMessage", "UserEntity has been registered successfully");
      modelAndView.addObject("userEntity", new UserEntity());
    }
    modelAndView.setViewName("register");
    return modelAndView;
  }
}
