package com.belyaev.servlets;

import com.belyaev.action.DepartmentAction;
import com.belyaev.form.DepartmentForm;
import com.belyaev.model.Department;
import com.belyaev.validator.DepartmentValidator;
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
 * @since 01-Dec-17
 */
@WebServlet(name = "DepartmentServletCU",
        urlPatterns = {"/department_create", "/department_update"})
public class DepartmentServletCU extends BaseDepartmentServlet {
    private static final Logger log = Logger.getLogger(DepartmentServletCU.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            process(request, response);
        } catch (IOException e) {
            log.error("IO error doGet process", e);
        } catch (ServletException e) {
            log.error("Servlet error doGet process", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            process(request, response);
        } catch (IOException e) {
            log.error("IO error doPost process", e);
        } catch (ServletException e) {
            log.error("Servlet error doPost process", e);
        }
    }
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DepartmentAction departmentAction = new DepartmentAction();
        DepartmentValidator departmentValidator = new DepartmentValidator();
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf('/');
        String action = uri.substring(lastIndex + 1);
        DepartmentForm departmentForm = createDepartmentForm(request);
        List<String> errors = departmentValidator.validate(departmentForm);
        if (errors.isEmpty()){
            Department department = createDepartment(departmentForm);
            if(action.equals("department_create")){
                departmentAction.insert(department);
            } else if (action.equals("department_update")){
                departmentAction.update(department);
            }
            if (departmentAction.isWrong()){
                errors.add(department.getName() + " is exist");
                if (action.equals("department_create")){
                   departmentForm.setId(null);
                }
                redirect(request, response, errors, departmentForm);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/department_read");
                rd.forward(request, response);
            }
        } else {
            redirect(request, response, errors, departmentForm);

        }
    }
}

