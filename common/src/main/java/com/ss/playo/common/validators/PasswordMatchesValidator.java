package com.ss.playo.common.validators;

import com.ss.playo.common.annotation.PasswordMatches;
import com.ss.playo.common.dto.PasswordDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, PasswordDTO> {

    @Override
    public boolean isValid(PasswordDTO o, ConstraintValidatorContext constraintValidatorContext) {
        return o.getPassword().equals(o.getConfirmPassword());
    }
}
