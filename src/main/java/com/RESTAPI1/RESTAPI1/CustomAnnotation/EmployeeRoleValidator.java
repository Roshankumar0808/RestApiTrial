package com.RESTAPI1.RESTAPI1.CustomAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Annotation;
import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeCustomAnnotation, String>{

    @Override
    public boolean isValid(String inputrole, ConstraintValidatorContext constraintValidatorContext) {
       List<String> role=List.of("USER","ADMIN");
       return role.contains(inputrole);
    }
}
