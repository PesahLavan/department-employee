package com.belyaev.filter;


import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TODO: comment
 *
 * @author Pavel Belyaev
 * @since 05-Dec-17
 */
@WebListener
public class JdbcDriverListener implements ServletContextListener {

    private Driver driver;
    private DriverManager driverManager;
    private Logger logger;
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Context initContext = null;
        Context envContext  = null;
        try {
            initContext = new InitialContext();
            envContext = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/postgres");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            driver = driverManager.getDriver("jdbc:postgresql://localhost:5432/test1");
            driverManager.deregisterDriver(driver);
        } catch (SQLException ex) {
            logger.info("Could not deregister driver:".concat(ex.getMessage()));
        }
        dataSource = null;
    }

}
