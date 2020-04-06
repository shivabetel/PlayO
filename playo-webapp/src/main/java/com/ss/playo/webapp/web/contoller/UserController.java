package com.ss.playo.webapp.web.contoller;


import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.webapp.web.dtos.UserDTO;
import com.ss.playo.webapp.web.events.OnRegistrationCompleteEvent;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IUserService;
import com.ss.playo.webapp.service.IVerificationTokenService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Locale;


@RestController
@RequestMapping("/api/user")
public class UserController {


    IUserService userService;

    public UserController(IUserService userService, IVerificationTokenService verificationTokenService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.publisher = publisher;

        new RuntimeException().printStackTrace();
    }


    IVerificationTokenService verificationTokenService;


    private ApplicationEventPublisher publisher;

    @PostMapping("/registration")
    public void register(@RequestBody @Valid  UserDTO userDTO, BindingResult result) throws UserAlreadyExistsException {
        System.out.println("userservice:::"+userService);

        if(result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder("");
            result.getAllErrors().stream().forEach(objectError -> stringBuilder.append(objectError.getDefaultMessage() + ","));
            throw new ValidationException(stringBuilder.toString());
        }

        User user = userService.register(userDTO);
        if(user != null){
            publisher.publishEvent(new OnRegistrationCompleteEvent(user, Locale.ENGLISH, ""));
        }



    }

    @GetMapping("/registrationConfirmation")
    public void registrationComplete(@RequestParam(name = "token") String token){
       userService.verifyRegistration(token);
    }

}
