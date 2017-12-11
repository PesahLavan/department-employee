package com.belyaev.servlets;

import com.belyaev.form.DepartmentForm;
import com.belyaev.model.Department;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 01-Dec-17
 */
public abstract class BaseDepartmentServlet extends HttpServlet {

    void redirect(HttpServletRequest request, HttpServletResponse response,
                            List<String> errors, DepartmentForm departmentForm)
            throws ServletException, IOException {
        request.setAttribute("errors", errors);
        request.setAttribute("departmentForm", departmentForm);
        RequestDispatcher rd = request.getRequestDispatcher("/view/department/DepartmentForm.jsp");
        rd.forward(request, response);
    }

    DepartmentForm createDepartmentForm(HttpServletRequest request){
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setId(request.getParameter("id"));
        departmentForm.setName(request.getParameter("name"));
        return departmentForm;
    }
    Department createDepartment(DepartmentForm departmentForm){
        Department department = new Department();
        if (!departmentForm.getId().isEmpty()) department.setId(Integer.parseInt(departmentForm.getId()));
        department.setName(departmentForm.getName());
        return department;
    }
    DepartmentForm createDepartmentForm(Department department){
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setId(String.valueOf(department.getId()));
        departmentForm.setName(department.getName());
        return departmentForm;
    }

}
