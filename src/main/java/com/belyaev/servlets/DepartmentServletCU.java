package com.belyaev.servlets;

import com.belyaev.form.DepartmentForm;
import com.belyaev.model.Department;

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
 * @since 01-Dec-17
 */
@WebServlet(name = "DepartmentServletCU",
        urlPatterns = {"/department_create", "/department_update"})
public class DepartmentServletCU extends BaseDepartmentServlet {

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
        String dispatchUrl = null;
        List<String> errors = new ArrayList<String>();
        DepartmentForm departmentForm = createDepartmentForm(request);
        errors = validate(departmentForm);
        if (errors.isEmpty()){
            Department department = createDepartment(departmentForm);
            if(action.equals("department_create")){
                departmentAction.insert(department);
            } else if (action.equals("department_update")){
                departmentAction.update(department);
            }
            if (this.departmentAction.isWrong()){
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
        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}

