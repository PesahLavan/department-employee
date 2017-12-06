package com.belyaev.servlets;

import com.belyaev.form.EmployeeForm;
import com.belyaev.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 04-Dec-17
 */
@WebServlet(name = "EmployeeServletCU",
        urlPatterns = {"/employee_create", "/employee_update"})
public class EmployeeServletCU extends BaseEmployeeServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        List<String> errors = new ArrayList<String>();
        EmployeeForm employeeForm = createEmployeeForm(request);
        errors = validate(employeeForm);
        if (errors.isEmpty()){
            Employee employee = createEmployee(employeeForm);
            if(action.equals("employee_create")){
                employeeAction.insert(employee);
            } else if (action.equals("employee_update")){
                employeeAction.update(employee);
            }
            if (this.employeeAction.isWrong()){
                errors.add("Email : " + employee.getEmail() + " is exist");
                if (action.equals("employee_create")){
                    employeeForm.setId(null);
                }
                redirect(request, response, errors, employeeForm);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/employee_read");
                rd.forward(request, response);
            }
        } else {
            redirect(request, response, errors, employeeForm);

        }
    }
}
