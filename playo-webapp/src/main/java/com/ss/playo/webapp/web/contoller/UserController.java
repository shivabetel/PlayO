package com.ss.playo.webapp.web.contoller;


import com.ss.playo.common.web.exception.UserAlreadyExistsException;
import com.ss.playo.webapp.web.dtos.UserDTO;
import com.ss.playo.webapp.web.events.OnRegistrationCompleteEvent;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IUserService;
import com.ss.playo.webapp.service.IVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Locale;


@RestController
@RequestMapping("/api/user")
public class UserController {


    IUserService userService;

    PasswordEncoder passwordEncoder;


    public UserController(IUserService userService, IVerificationTokenService verificationTokenService, ApplicationEventPublisher publisher,
                         PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
        this.publisher = publisher;
        this.passwordEncoder = passwordEncoder;

        new RuntimeException().printStackTrace();
    }


    IVerificationTokenService verificationTokenService;


    private ApplicationEventPublisher publisher;

    @PostMapping()
    @CrossOrigin()
    @ResponseStatus(code = HttpStatus.CREATED)
    public String register(@RequestBody @Valid  UserDTO userDTO, BindingResult result) {
        System.out.println("userservice:::"+userService);

        if(result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder("");
            result.getAllErrors().stream().forEach(objectError -> stringBuilder.append(objectError.getDefaultMessage() + ","));
            throw new ValidationException(stringBuilder.toString());
        }
       userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userService.register(userDTO);
        if(user != null){
            publisher.publishEvent(new OnRegistrationCompleteEvent(user, Locale.ENGLISH, ""));
        }

        return "{\"responseCode\": 201, \"responseMessage\": \" user created successfully\"}";



    }

    @GetMapping("/checkAvailability")
    @CrossOrigin()
    public String validateUser(@RequestParam(name = "emailId") String emailId) {
      return userService.findByEmailId(emailId).isPresent() ? "{\"available\": false }" : "{\"available\": true }";
    }

    @GetMapping("/registrationConfirmation")
    public void registrationComplete(@RequestParam(name = "token") String token){
       userService.verifyRegistration(token);
    }

}
