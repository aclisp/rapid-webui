/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pzone.util;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Huang
 */
@ManagedBean(eager=true, name="entityManagerHelper")
@ApplicationScoped
public class EntityManagerHelper {
    
    private static final Logger logger = Logger.getLogger(EntityManagerHelper.class.getName());
    private EntityManagerFactory entityManagerFactory;
    
    /**
     * Creates a new instance of EntityManagerHelper
     */
    public EntityManagerHelper() {
    }
    
    @PostConstruct
    public void initialize() {
        logger.info("#### init EntityManagerHelper");
        entityManagerFactory = Persistence.createEntityManagerFactory("pzonePU");
    }
    
    @PreDestroy
    public void shutdown() {
        logger.info("#### shutdown EntityManagerHelper");
        entityManagerFactory.close();
    }
    
    public EntityManager createEntityManager() {
        assert entityManagerFactory != null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        assert entityManager != null;
        return entityManager;
    }
    
    public void runWithinTransaction(Callback callback) {
        EntityManager em = this.createEntityManager();
        EntityTransaction tx = null;
        
        try {
            tx = em.getTransaction();
            tx.begin();
            callback.run(em);            
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
    
    public void runWithoutTransaction(Callback callback) {
        EntityManager em = this.createEntityManager();
        
        try {
            callback.run(em);            
        } finally {
            em.close();
        }        
    }
}
