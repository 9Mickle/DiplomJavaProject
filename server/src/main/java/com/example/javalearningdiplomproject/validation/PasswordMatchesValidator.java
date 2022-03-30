package com.example.javalearningdiplomproject.validation;

import com.example.javalearningdiplomproject.annotation.PasswordMatches;
import com.example.javalearningdiplomproject.payload.request.SignUpRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

   @Override
    public void initialize(PasswordMatches constraint) {
    }

   /**
    * Проверка равенства паролей.
    */
   @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
       SignUpRequest userSignUpRequest = (SignUpRequest) obj;
       return userSignUpRequest.getPassword().equals(userSignUpRequest.getConfirmPassword());
    }
}
