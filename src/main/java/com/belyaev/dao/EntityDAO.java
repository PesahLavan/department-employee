package com.belyaev.dao;

import com.belyaev.dao.exception.DAOException;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author Pavel Belyaev
 * @since 27-Nov-17
 */
public interface EntityDAO<T>{
    List<T> getAll() throws DAOException;

    void insert(final T emp) throws DAOException;

    void update(final T emp) throws DAOException;

    void delete(final T emp) throws DAOException;

    T get(final int id) throws DAOException;

    boolean isName(String name) throws DAOException;

    void setDataSource(DataSource dataSource);
}
