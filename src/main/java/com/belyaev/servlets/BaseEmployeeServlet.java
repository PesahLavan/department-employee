package com.belyaev.servlets;

import com.belyaev.action.DepartmentAction;
import com.belyaev.action.EmployeeAction;
import com.belyaev.form.EmployeeForm;
import com.belyaev.model.Department;
import com.belyaev.model.Employee;
import com.belyaev.validator.EmployeeValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 01-Dec-17
 */
public abstract class BaseEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1573L;

    EmployeeAction employeeAction = new EmployeeAction();
    DepartmentAction departmentAction = new DepartmentAction();

    protected void redirect(HttpServletRequest request, HttpServletResponse response,
                            List<String> errors, EmployeeForm employeeForm)
            throws ServletException, IOException {
        int departmentId = Integer.parseInt(employeeForm.getDepartmentId());
        String nameDepartment = departmentAction.get(departmentId).getName();
        request.setAttribute("departments", departmentAction.getEmpAll());
        request.setAttribute("nameDepartment", nameDepartment);
        request.setAttribute("departmentId", departmentId);
        request.setAttribute("employeeForm", employeeForm);
        request.setAttribute("errors", errors);
        RequestDispatcher rd = request.getRequestDispatcher("/view/employee/EmployeeForm.jsp");
        rd.forward(request, response);
    }
    protected EmployeeForm createEmployeeForm(HttpServletRequest request){
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setId(request.getParameter("id"));
        employeeForm.setName(request.getParameter("name"));
        employeeForm.setDepartmentId(request.getParameter("departmentId"));
        employeeForm.setNumber(request.getParameter("number"));
        employeeForm.setEmail(request.getParameter("email"));
        employeeForm.setBirthDate(request.getParameter("birthday"));
        List<Department> departments = departmentAction.getEmpAll();
        employeeForm.setDepartments(departments);
        return employeeForm;
    }
    protected List<String> validate(EmployeeForm employeeForm){
        EmployeeValidator employeeValidator = new EmployeeValidator();
        List<String> errors = employeeValidator.validate(employeeForm);
        return errors;
    }
    protected Employee createEmployee(EmployeeForm employeeForm){
        Employee employee = new Employee();
        /** New Employee for insert DB*/
        if (!employeeForm.getId().isEmpty()) employee.setId(Integer.parseInt(employeeForm.getId()));
        employee.setName(employeeForm.getName());
        employee.setDepartmentId(Integer.parseInt(employeeForm.getDepartmentId()));
        employee.setNumber(Integer.parseInt(employeeForm.getNumber()));
        employee.setEmail(employeeForm.getEmail().toLowerCase());
        employee.setBirthDate(Date.valueOf(employeeForm.getBirthDate()));
        return employee;
    }
    protected EmployeeForm createEmployeeForm(Employee employee){
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
