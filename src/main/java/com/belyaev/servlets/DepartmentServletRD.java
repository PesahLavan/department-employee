package com.belyaev.servlets;

import com.belyaev.action.DepartmentAction;
import com.belyaev.form.DepartmentForm;
import com.belyaev.model.Department;
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
 * @since 30-Nov-17
 */
@WebServlet(name = "DepartmentServletRD",
        urlPatterns = {"/department_read", "/", "/department_delete", "/department_input", "/department_input_update"})
public class DepartmentServletRD extends BaseDepartmentServlet {
    private static final Logger log = Logger.getLogger(DepartmentServletRD.class);

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
        DepartmentAction departmentAction = new DepartmentAction();
        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf('/');
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        if (action.equals("department_read")|| action.isEmpty()) {
            List<Department> departments = departmentAction.getEmpAll();
            request.setAttribute("departments", departments);
            dispatchUrl = "/view/department/DepartmentList.jsp";
        } else if (action.equals("department_delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Department department = departmentAction.get(id);
            departmentAction.delete(department);
            dispatchUrl = "/department_read";
        } else if (action.equals("department_input")) {
            DepartmentForm departmentForm = new DepartmentForm();
            request.setAttribute("departmentForm", departmentForm);
            dispatchUrl = "/view/department/DepartmentForm.jsp";
        } else  if (action.equals("department_input_update")){
            int id = Integer.parseInt(request.getParameter("id"));
            Department department = departmentAction.get(id);
            DepartmentForm departmentForm = createDepartmentForm(department);
            request.setAttribute("departmentForm", departmentForm);
            dispatchUrl = "/view/department/DepartmentForm.jsp";
        }
        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }
}
