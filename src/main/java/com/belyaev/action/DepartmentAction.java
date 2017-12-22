package com.belyaev.action;

import com.belyaev.dao.DAOFactory;
import com.belyaev.dao.EntityDAO;
import com.belyaev.dao.exception.DAOException;
import com.belyaev.model.Department;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 28-Nov-17
 */
public class DepartmentAction implements Action<Department>{

    private static final Logger log = Logger.getLogger(DepartmentAction.class);
    private EntityDAO<Department> departmentDAO = DAOFactory.getDepartmentDAO();
    private boolean isWrong;

    public boolean isWrong() {
        return isWrong;
    }

    public List<Department> getEmpAll() {
        List<Department> departments = null;
        try {
            departments = departmentDAO.getAll();
        } catch (DAOException e) {
            log.error("Error reade all department", e);
        }
        return departments;
    }
    public void delete(Department department) {
        try {
            departmentDAO.delete(department);
        } catch (DAOException e) {
            log.error("Error delete department", e);
        }
    }
    public void insert(Department department) {
        try {
            isWrong = false;
            departmentDAO.insert(department);
        } catch (DAOException e) {
            isWrong = isExist(e.getMessage());
            if (!isWrong) log.error("Error insert department", e);
        }
    }
    public Department get(Integer id) {
        Department department = new Department();
        try {
            department = departmentDAO.get(id);
        } catch (DAOException e) {
            log.error("Error get department", e);
        }
        return department;
    }
    public void update(Department department) {
        try {
            isWrong = false;
            departmentDAO.update(department);
        } catch (DAOException e) {
            isWrong = isExist(e.getMessage());
            if (!isWrong) log.error("Error update department", e);
        }
    }

    private boolean isExist(String msg){
        boolean isExist = false;
        String msgDep = msg.substring(80, 106);
        if (msgDep.equals("departments_department_key")){
            isExist = true;
        }
        return isExist;
    }
}
