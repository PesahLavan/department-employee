package com.belyaev.dao.impl;

import com.belyaev.dao.EntityDAO;
import com.belyaev.dao.exception.DAOException;
import com.belyaev.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 05-Dec-17
 */
public class EmployeeDAOImpl extends BaseDAO implements EntityDAO<Employee> {

    private static final String GET_ALL_EMPLOYEE_SQL = "SELECT * FROM employee";
    @Override
    public List<Employee> getAll() throws DAOException {
            List<Employee> employees = new ArrayList<Employee>();
            Connection connection = null;
            PreparedStatement pStatement = null;
            ResultSet resultSet = null;
            try {
                connection = getConnection();
                pStatement = connection.prepareStatement(GET_ALL_EMPLOYEE_SQL);
                resultSet = pStatement.executeQuery();
                while (resultSet.next()) {
                    employees.add( getEmployee(resultSet));
                }
            } catch (SQLException e) {
                throw new DAOException("Error getting employees. " + e.getMessage());
            } finally {
                closeDBObjects(resultSet, pStatement, connection);
            }
            return employees;
    }

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee (name, department_id, number, email, birthday)  values ((?),(?),(?),(?),(?))";
    @Override
    public void insert(Employee emp) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL, Statement.RETURN_GENERATED_KEYS);
            setEmployee(pStatement, emp);
            pStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error adding employee. " + e.getMessage());
        } finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    private static final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET name = (?), department_id = (?), number = (?), email = (?), birthday = (?) WHERE uid = (?)";
    @Override
    public void update(Employee emp) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);
            setEmployee(pStatement, emp);
            pStatement.setLong(6, emp.getId());
            pStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error update employee. " + e.getMessage());
        } finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE uid = ?";
    @Override
    public void delete(Employee emp) throws DAOException {
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);
            pStatement.setLong(1, emp.getId());
            pStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Error delete employee. " + e.getMessage());
        } finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    private static final String GET_EMPLOYEE_SQL = "SELECT * FROM employee WHERE uid=(?)";
    @Override
    public Employee get(int id) throws DAOException {
        Employee employee = new Employee();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement(GET_EMPLOYEE_SQL);
            pStatement.setInt(1, id);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                employee = getEmployee(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Error getting employee. " + e.getMessage());
        } finally {
            closeDBObjects(resultSet, pStatement, connection);
        }
        return employee;
    }

    private static final String IS_EMAIL_SQL = "SELECT * FROM employee WHERE email=(?)";
    @Override
    public boolean isName(String email) throws DAOException {
        return isEntity(email, IS_EMAIL_SQL, "email");
    }

    private Employee getEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("uid"));
        employee.setName(resultSet.getString("name"));
        employee.setDepartmentId(resultSet.getInt("department_id"));
        employee.setNumber(resultSet.getInt("number"));
        employee.setEmail(resultSet.getString("email"));
        employee.setBirthDate(resultSet.getDate("birthday"));
        return employee;
    }

    private void setEmployee(PreparedStatement pStatement, Employee emp) throws SQLException{
        pStatement.setString(1, emp.getName());
        pStatement.setInt(2, emp.getDepartmentId());
        pStatement.setInt(3, emp.getNumber());
        pStatement.setString(4, emp.getEmail());
        pStatement.setDate(5, emp.getBirthDate());
    }
}
