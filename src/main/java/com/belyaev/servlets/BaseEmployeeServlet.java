package com.belyaev.servlets;

import com.belyaev.action.DepartmentAction;
import com.belyaev.form.EmployeeForm;
import com.belyaev.model.Department;
import com.belyaev.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 01-Dec-17
 */
public abstract class BaseEmployeeServlet extends HttpServlet {

    private final List<Department> departments = new DepartmentAction().getEmpAll();


    private int getDepartmentId(HttpServletRequest request, EmployeeForm employeeForm){
        int id;
        if (employeeForm.getDepartmentId() == null){
            id = Integer.parseInt(request.getParameter("departmentId"));
        }else {
            id = Integer.parseInt(employeeForm.getDepartmentId());
        }
        return id;
    }

    void redirectForm(HttpServletRequest request, HttpServletResponse response,
                                List<String> errors, EmployeeForm employeeForm)
            throws ServletException, IOException {
        int departmentId = getDepartmentId(request, employeeForm);
        String nameDepartment = new DepartmentAction().get(departmentId).getName();
        request.setAttribute("departments", departments);
        request.setAttribute("nameDepartment", nameDepartment);
        request.setAttribute("departmentId", departmentId);
        request.setAttribute("employeeForm", employeeForm);
        request.setAttribute("errors", errors);
        RequestDispatcher rd = request.getRequestDispatcher("/view/employee/EmployeeForm.jsp");
        rd.forward(request, response);
    }
    EmployeeForm createEmployeeForm(HttpServletRequest request){
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(request.getParameter("id"));
        employeeForm.setName(request.getParameter("name"));
        employeeForm.setDepartmentId(String.valueOf(getDepartmentId(request, new EmployeeForm())));
        employeeForm.setNumber(request.getParameter("number"));
        employeeForm.setEmail(request.getParameter("email"));
        employeeForm.setBirthDate(request.getParameter("birthday"));
        employeeForm.setDepartments(departments);
        return employeeForm;
    }
    Employee createEmployee(EmployeeForm employeeForm){
        Employee employee = new Employee();
        if (!employeeForm.getId().isEmpty()) employee.setId(Integer.parseInt(employeeForm.getId()));
        employee.setName(employeeForm.getName());
        employee.setDepartmentId(Integer.parseInt(employeeForm.getDepartmentId()));
        employee.setNumber(Integer.parseInt(employeeForm.getNumber()));
        employee.setEmail(employeeForm.getEmail().toLowerCase());
        employee.setBirthDate(Date.valueOf(employeeForm.getBirthDate()));
        return employee;
    }
    EmployeeForm createEmployeeForm(Employee employee){
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(String.valueOf(employee.getId()));
        employeeForm.setName(employee.getName());
        employeeForm.setDepartmentId(String.valueOf(employee.getDepartmentId()));
        employeeForm.setNumber(String.valueOf(employee.getNumber()).toLowerCase());
        employeeForm.setEmail(employee.getEmail());
        employeeForm.setBirthDate(String.valueOf(employee.getBirthDate()));
        return employeeForm;
    }
}
