package com.belyaev.servlets;

import com.belyaev.action.EmployeeAction;
import com.belyaev.form.EmployeeForm;
import com.belyaev.model.Employee;
import com.belyaev.validator.EmployeeValidator;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 04-Dec-17
 */
@WebServlet(name = "EmployeeServletCU",
        urlPatterns = {"/employee_create", "/employee_update"})
public class EmployeeServletCU extends BaseEmployeeServlet {
    private static final Logger log = Logger.getLogger(EmployeeServletCU.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            process(request, response);
        } catch (IOException e) {
            log.error("IO error doGet process", e);
        } catch (ServletException e) {
            log.error("Servlet error doGet process", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            process(request, response);
        } catch (IOException e) {
            log.error("IO error doPost process", e);
        } catch (ServletException e) {
            log.error("Servlet error doPost process", e);
        }
    }
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EmployeeAction employeeAction = new EmployeeAction();
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf('/');
        String action = uri.substring(lastIndex + 1);
        EmployeeForm employeeForm = createEmployeeForm(request);
        List<String> errors = new EmployeeValidator().validate(employeeForm);
        if (errors.isEmpty()){
            Employee employee = createEmployee(employeeForm);
            if(action.equals("employee_create")){
                employeeAction.insert(employee);
            } else if (action.equals("employee_update")){
                employeeAction.update(employee);
            }
            if (employeeAction.isWrong()){
                errors.add("Email : " + employee.getEmail() + " is exist");
                if (action.equals("employee_create")){
                    employeeForm.setId(null);
                }
                redirectForm(request, response, errors, employeeForm);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/employee_read");
                rd.forward(request, response);
            }
        } else {
            redirectForm(request, response, errors, employeeForm);
        }
    }
}
