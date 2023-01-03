package org.example;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyContextListener implements ServletContextListener {

    private EntityManagerFactory emf;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        emf.close();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        emf = Persistence.createEntityManagerFactory("rate");
        ServletContext ctx = servletContextEvent.getServletContext();
        ctx.setAttribute("EntityManagerFactory", emf);
    }
}