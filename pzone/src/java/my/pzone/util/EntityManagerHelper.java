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
        assert entityManagerFactory != null;
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
    
    public void runWithinTransaction(EntityManagerCallback callback) {
        EntityManager em = null;
        EntityTransaction tx = null;
        
        try {
            em = this.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            callback.run(em);   
            
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            
            callback.onError(ex);
            
            throw ex;
        } finally {
            if (em != null) em.close();
        }
        
        callback.onSuccess();
    }
    
    public void runWithoutTransaction(EntityManagerCallback callback) {
        EntityManager em = null;
        
        try {
            em = this.createEntityManager();
            callback.run(em);            
        } catch (RuntimeException ex) {
            
            callback.onError(ex);
            throw ex;
        } finally {
            if (em != null) em.close();
        }       
        
        callback.onSuccess();
    }
}
