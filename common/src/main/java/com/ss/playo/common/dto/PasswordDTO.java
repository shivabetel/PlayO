package com.ss.playo.common.dto;

import com.ss.playo.common.annotation.PasswordMatches;
import com.ss.playo.common.interfaces.IDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class PasswordDTO implements IDto {

    @NotNull(message = "Password cannot be empty")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "Confirm Password cannot be empty")
    @NotEmpty(message = "Confirm Password cannot be empty")
    private String confirmPassword;

    public PasswordDTO(@NotNull(message = "Password cannot be empty") @NotEmpty(message = "Password cannot be empty") String password, @NotNull(message = "Confirm Password cannot be empty") @NotEmpty(message = "Confirm Password cannot be empty") String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public PasswordDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
