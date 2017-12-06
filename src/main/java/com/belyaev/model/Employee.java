package com.belyaev.model;


import java.io.Serializable;
import java.sql.Date;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 26-Oct-17
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 748392332L;

    private long id;
    private String name;
    private int departmentId;
    private int number;
    private String email;
    private Date birthDate;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !Employee.class.isAssignableFrom(o.getClass())) {
            return false;
        }

        Employee emp = (Employee) o;

        return id == emp.id;
    }
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
