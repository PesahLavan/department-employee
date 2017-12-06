package com.belyaev.validator;

import com.belyaev.form.DepartmentForm;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 27-Nov-17
 */
public class DepartmentValidator {

    public List<String> validate(DepartmentForm departmentForm)  {
        List<String> errors = new ArrayList<String>();
        String name = departmentForm.getName();

        if (name == null || name.trim().isEmpty()) {
            errors.add("Department must have a name");
        }

        return errors;
    }
}
