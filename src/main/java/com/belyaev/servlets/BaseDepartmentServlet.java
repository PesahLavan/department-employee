package com.belyaev.servlets;

import com.belyaev.action.DepartmentAction;
import com.belyaev.form.DepartmentForm;
import com.belyaev.model.Department;
import com.belyaev.validator.DepartmentValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 01-Dec-17
 */
public abstract class BaseDepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1573L;
    DepartmentAction departmentAction = new DepartmentAction();


    protected void redirect(HttpServletRequest request, HttpServletResponse response,
                            List<String> errors, DepartmentForm departmentForm)
            throws ServletException, IOException {
        request.setAttribute("errors", errors);
        request.setAttribute("departmentForm", departmentForm);
        RequestDispatcher rd = request.getRequestDispatcher("/view/department/DepartmentForm.jsp");
        rd.forward(request, response);
    }

    protected DepartmentForm createDepartmentForm(HttpServletRequest request){
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setId(request.getParameter("id"));
        departmentForm.setName(request.getParameter("name"));
        return departmentForm;
    }
    protected List<String> validate(DepartmentForm departmentForm){
        DepartmentValidator departmentValidator = new DepartmentValidator();
        List<String> errors = departmentValidator.validate(departmentForm);
        return errors;
    }
    protected Department createDepartment(DepartmentForm departmentForm){
        Department department = new Department();
        /** New Department for insert DB*/
        if (!departmentForm.getId().isEmpty()) department.setId(Integer.parseInt(departmentForm.getId()));
        department.setName(departmentForm.getName());
        return department;
    }
    protected DepartmentForm createDepartmentForm(Department department){
        DepartmentForm departmentForm = new DepartmentForm();
        departmentForm.setId(String.valueOf(department.getId()));
        departmentForm.setName(department.getName());
        return departmentForm;
    }

}
