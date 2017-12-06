package com.belyaev.action;

import com.belyaev.dao.DAOFactory;
import com.belyaev.dao.EntityDAO;
import com.belyaev.dao.exception.DAOException;
import com.belyaev.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 28-Nov-17
 */
public class EmployeeAction implements Action<Employee> {

    private EntityDAO<Employee> employeeDAO = DAOFactory.getEmployeeDAO();
    private boolean isWrong;

    public void setProductDAO(EntityDAO<Employee> employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public boolean isWrong() {
        return isWrong;
    }

    @Override
    public List<Employee> getEmpAll() {
        List<Employee> employees = null;
        try {
            employees = employeeDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getEmpDep(int id) {
        List<Employee> employees = new ArrayList<Employee>();
        List<Employee> allEmployees = getEmpAll();
        for (Employee employee : allEmployees) {
            if (employee.getDepartmentId() == id) {
                employees.add(employee);
            }
        }
        return employees;
    }
    @Override
    public void insert(Employee employee) {
        isWrong = false;
        try {
            employeeDAO.insert(employee);
        } catch (DAOException e) {
            isWrong = isWrong(e.getMessage());
            if (!isWrong) e.printStackTrace();
        }
    }
    @Override
    public void update(Employee employee) {
        isWrong = false;
        try {
            employeeDAO.update(employee);
        } catch (DAOException e) {
            isWrong = isWrong(e.getMessage());
            if (!isWrong) e.printStackTrace();
        }
    }
    @Override
    public void delete(Employee employee) {
        try {
            employeeDAO.delete(employee);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee get(Integer id) {
        Employee employee = new Employee();
        try {
            employee = employeeDAO.get(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return employee;
    }

    protected boolean isWrong(String msg){
        boolean isWrong = false;
        String msgEmail = msg.substring(78, 96);
        if (msgEmail.equals("employee_email_key")){
            isWrong = true;
        }
        return isWrong;
    }
}
