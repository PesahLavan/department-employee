package com.belyaev.action;

import com.belyaev.dao.DAOFactory;
import com.belyaev.dao.EntityDAO;
import com.belyaev.dao.exception.DAOException;
import com.belyaev.model.Employee;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 28-Nov-17
 */
public class EmployeeAction implements Action<Employee> {

    private static final Logger log = Logger.getLogger(EmployeeAction.class);
    private EntityDAO<Employee> employeeDAO = DAOFactory.getEmployeeDAO();
    private boolean isWrong;

    public boolean isWrong() {
        return isWrong;
    }

    @Override
    public List<Employee> getEmpAll() {
        List<Employee> employees = null;
        try {
            employees = employeeDAO.getAll();
        } catch (DAOException e) {
            log.error("Error reade all employee", e);
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
            isWrong = isExist(e.getMessage());
            if (!isWrong) log.error("Error insert employee", e);
        }
    }
    @Override
    public void update(Employee employee) {
        isWrong = false;
        try {
            employeeDAO.update(employee);
        } catch (DAOException e) {
            isWrong = isExist(e.getMessage());
            if (!isWrong) log.error("Error update employee", e);
        }
    }
    @Override
    public void delete(Employee employee) {
        try {
            employeeDAO.delete(employee);
        } catch (DAOException e) {
            log.error("Error delete employee", e);
        }
    }

    @Override
    public Employee get(Integer id) {
        Employee employee = new Employee();
        try {
            employee = employeeDAO.get(id);
        } catch (DAOException e) {
            log.error("Error get employee", e);
        }
        return employee;
    }

    private boolean isExist(String msg){
        boolean isExist = false;
        String msgEmail = msg.substring(78, 96);
        if (msgEmail.equals("employee_email_key")){
            isExist = true;
        }
        return isExist;
    }
}
