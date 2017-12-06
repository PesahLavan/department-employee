package com.belyaev.dao;

import com.belyaev.dao.impl.DepartmentDAOImpl;
import com.belyaev.dao.impl.EmployeeDAOImpl;
import com.belyaev.model.Department;
import com.belyaev.model.Employee;

public class DAOFactory {

    public static EntityDAO<Department> getDepartmentDAO() {
        return new DepartmentDAOImpl();
    }
    public static EntityDAO<Employee> getEmployeeDAO() {
        return new EmployeeDAOImpl();
    }
}
