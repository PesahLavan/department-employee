package com.belyaev.filter;


import com.belyaev.dao.exception.DAOException;
import com.belyaev.dao.impl.BaseDAO;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 * @author Pavel Belyaev
 * @since 05-Dec-17
 */
@WebListener
public class JdbcDriverListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(JdbcDriverListener.class);


    @Override
    public void contextInitialized(ServletContextEvent sce){
        try {
            Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
            DataSource  ds = (DataSource) envCtx.lookup("jdbc/postgres");
            sce.getServletContext().setAttribute("DBCPool", ds);
        } catch(NamingException e){
            log.info("Could not register driver:".concat(e.getMessage()));
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        BaseDAO dbManager = (BaseDAO) ctx.getAttribute("DBCPool");
        try {
            dbManager.closeConnection();
        } catch (DAOException e) {
            log.info("deregister driver.".concat(e.getMessage()));
        }

    }



}
