package com.ss.playo.webapp.contoller;

import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.webapp.dtos.UserDTO;
import com.ss.playo.webapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody @Valid UserDTO user) throws UserAlreadyExistsException {
          //userService.register(user);
    }
}
