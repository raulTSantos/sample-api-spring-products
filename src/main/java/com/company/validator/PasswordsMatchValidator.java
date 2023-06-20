package com.company.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.company.dto.SignupRequest;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, SignupRequest> {
	
	@Override
    public void initialize(PasswordsMatch constraintAnnotation) {
    }

	@Override
	public boolean isValid(SignupRequest value, ConstraintValidatorContext context) {
		if (!value.getPassword().equals(value.getMatchingPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Las contraseñas no coinciden.")
                   .addPropertyNode("matchingPassword")
                   .addConstraintViolation();

            return false;
        }

        return true;
	}

}
