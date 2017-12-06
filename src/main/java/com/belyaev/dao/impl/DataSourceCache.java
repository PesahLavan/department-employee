package com.belyaev.dao.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 05-Dec-17
 */
public class DataSourceCache {
    private static DataSourceCache instance;
    private DataSource dataSource;
    static {
        instance = new DataSourceCache();
    }

    private DataSourceCache() {
        Context context = null;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup(
                    "java:comp/env/jdbc/postgres");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DataSourceCache getInstance() {
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
