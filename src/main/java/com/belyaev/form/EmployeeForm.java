package com.belyaev.form;

import com.belyaev.model.Department;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 27-Nov-17
 */
public class EmployeeForm implements Serializable {
    private static final long serialVersionUID = 748392332L;

    private String id;
    private String name;
    private String departmentId;
    private String number;
    private String email;
    private String birthDate;
    private List<Department> departments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
