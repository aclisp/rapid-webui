/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.rapid;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Huang
 */
@WebListener()
public class EMF implements ServletContextListener {

    private static EntityManagerFactory emf;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("BasicJsfPU");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        emf.close();
    }
    
    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }    
}
