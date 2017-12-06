package com.belyaev.dao.impl;

import com.belyaev.dao.EntityDAO;
import com.belyaev.dao.exception.DAOException;
import com.belyaev.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 05-Dec-17
 */
public class DepartmentDAOImpl extends BaseDAO implements EntityDAO<Department> {

    private static final String GET_ALL_DEPARTMENT_SQL = "SELECT * FROM departments";
    @Override
    public List<Department> getAll() throws DAOException {
        List<Department> departments = new ArrayList<Department>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_ALL_DEPARTMENT_SQL);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("uid"));
                department.setName(resultSet.getString("department"));
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new DAOException("Error getting departments. " + e.getMessage());
        } finally {
            closeDBObjects(resultSet, pStatement, connection);
        }
        return departments;
    }

    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO departments (department) VALUES (?)";
    @Override
    public void insert(Department emp) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(INSERT_DEPARTMENT_SQL, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, emp.getName());
            pStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error adding department. " + e.getMessage());
        } finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    private static final String UPDATE_DEPARTMENT_SQL = "UPDATE departments SET department = ? WHERE uid = ?";
    @Override
    public void update(Department emp) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(UPDATE_DEPARTMENT_SQL);
            pStatement.setString(1, emp.getName());
            pStatement.setLong(2, emp.getId());
            pStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error update department. " + e.getMessage());
        } finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    private static final String DELETE_DEPARTMENT_SQL = "DELETE FROM departments WHERE uid = ?";
    @Override
    public void delete(Department emp) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(DELETE_DEPARTMENT_SQL);
            pStatement.setLong(1, emp.getId());
            pStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error delete department. " + e.getMessage());
        } finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    private static final String GET_DEPARTMENT_SQL = "SELECT * FROM departments WHERE uid=(?)";
    @Override
    public Department get(int id) throws DAOException {
        Department department = new Department();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_DEPARTMENT_SQL);
            pStatement.setInt(1, id);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                department.setId(resultSet.getInt("uid"));
                department.setName(resultSet.getString("department"));
            }
        } catch (SQLException e) {
            throw new DAOException("Error getting department. " + e.getMessage());
        } finally {
            closeDBObjects(resultSet, pStatement, connection);
        }
        return department;
    }

    private static final String IS_DEPARTMENT_SQL = "SELECT * FROM departments WHERE department=(?)";
    @Override
    public boolean isName(String name) throws DAOException {
        return isEntity(name, IS_DEPARTMENT_SQL, "department");
    }
}
