package com.belyaev.validator;

import com.belyaev.form.EmployeeForm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 27-Nov-17
 */
public class EmployeeValidator {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", Pattern.CASE_INSENSITIVE);

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public List<String> validate(EmployeeForm employeeForm) {
        List<String> errors = new ArrayList<String>();
        String name = employeeForm.getName();
        if (name == null || name.trim().isEmpty()) {
            errors.add("Employee must have a name");
        }
        String number = employeeForm.getNumber();
        if (number == null || number.trim().isEmpty()) {
            errors.add("Employee must have a number");
        } else {
            try {
                Integer.parseInt(number);
            } catch (NumberFormatException e) {
                errors.add("Invalid number value");
            }
        }
        String email = employeeForm.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.add("Employee must have a email");
        } else if (!validateEmail(email)){
            errors.add("Invalid email value");
        }
        String birthDate = employeeForm.getBirthDate();
        if (birthDate == null || birthDate.trim().isEmpty()) {
            errors.add("Employee must have a birthday");
        } else {
            try {
                Date.valueOf(birthDate);
            } catch (IllegalArgumentException e) {
                errors.add("Invalid birthday value");
            }
        }
        return errors;
    }

}
