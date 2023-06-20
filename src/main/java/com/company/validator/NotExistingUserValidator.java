package com.company.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.dto.SignupRequest;
import com.company.service.UserService;

public class NotExistingUserValidator implements ConstraintValidator<NotExistingUser, SignupRequest>{
    @Autowired
	private UserService userService;

	@Override
	public boolean isValid(SignupRequest value, ConstraintValidatorContext context) {
		if (userService.userWithUsernameExists(value.getUsername())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Este nombre de usuario ya existe.")
                   .addPropertyNode("username")
                   .addConstraintViolation();

            return false;
        }

        return true;
	}

}
