package com.ss.playo.webapp.web.dtos;

import com.ss.playo.common.annotation.ValidEmail;
import com.ss.playo.common.dto.PasswordDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDTO extends PasswordDTO {

    @ValidEmail(message = "Not a valid Email ID")
    @NotNull(message = "Email ID cannot be empty")
    @NotEmpty(message = "Email ID cannot be empty")
    private String emailId;

    @NotNull(message = "First Name cannot be empty")
    @NotEmpty(message = "First Name cannot be empty")
    private String firstName;

    @NotNull(message = "Last Name cannot be empty")
    @NotEmpty(message = "Last Name cannot be empty")
    private String lastName;



    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
