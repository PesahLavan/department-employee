package com.belyaev.action;

import com.belyaev.dao.DAOFactory;
import com.belyaev.dao.EntityDAO;
import com.belyaev.dao.exception.DAOException;
import com.belyaev.model.Department;

import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 28-Nov-17
 */
public class DepartmentAction implements Action<Department>{

    private EntityDAO<Department> departmentDAO = DAOFactory.getDepartmentDAO();
    private boolean isWrong;

    public void setProductDAO(EntityDAO<Department> departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public boolean isWrong() {
        return isWrong;
    }

    public List<Department> getEmpAll() {
        List<Department> departments = null;
        try {
            departments = departmentDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return departments;
    }
    public void delete(Department department) {
        try {
            departmentDAO.delete(department);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    public void insert(Department department) {
        try {
            isWrong = false;
            departmentDAO.insert(department);
        } catch (DAOException e) {
            isWrong = isWrong(e.getMessage());
            if (!isWrong) e.printStackTrace();
        }
    }
    public Department get(Integer id) {
        Department department = new Department();
        try {
            department = departmentDAO.get(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return department;
    }
    public void update(Department department) {
        try {
            isWrong = false;
            departmentDAO.update(department);
        } catch (DAOException e) {
            isWrong = isWrong(e.getMessage());
            if (!isWrong) e.printStackTrace();
        }
    }

    protected boolean isWrong(String msg){
        boolean isWrong = false;
        String msgDep = msg.substring(80, 106);
        if (msgDep.equals("departments_department_key")){
            isWrong = true;
        }
        return isWrong;
    }
}
