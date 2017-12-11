package com.belyaev.dao.impl;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * @author Pavel Belyaev
 * @since 05-Dec-17
 */
public class DataSourceCache {
    private static final Logger log = Logger.getLogger(DataSourceCache.class);
    private static DataSourceCache instance;
    private DataSource dataSource;
    static {
        instance = new DataSourceCache();
    }

    private DataSourceCache() {
        Context context;
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup(
                    "java:comp/env/jdbc/postgres");
        } catch (NamingException e) {
            log.error("Error reade context", e);
        }
    }

    public static DataSourceCache getInstance() {
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
