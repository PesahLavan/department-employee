package com.belyaev.dao;

import com.belyaev.dao.exception.DAOException;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author Pavel Belyaev
 * @since 05-Dec-17
 */
public interface DAO {
    void setDataSource(DataSource dataSource);
    Connection getConnection() throws DAOException;
}
