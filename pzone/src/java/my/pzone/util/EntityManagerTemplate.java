/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pzone.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Huang
 */
public class EntityManagerTemplate {
    
    protected EntityManagerFactory entityManagerFactory;
    
    public EntityManager createEntityManager() {
        assert entityManagerFactory != null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        assert entityManager != null;
        return entityManager;
    }
    
    public void runWithinTransaction(EntityManagerCallback callback) {
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean success = false;
        
        try {
            em = createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            callback.run(em);   
            
            tx.commit();
            success = true;
        } catch (RuntimeException ex) {

            callback.onError(ex);
            
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            if (em != null) em.close();
        }
        
        if (success) callback.onSuccess();
    }
    
    public void runWithoutTransaction(EntityManagerCallback callback) {
        EntityManager em = null;
        boolean success = false;
        
        try {
            em = createEntityManager();
            
            callback.run(em);   
            
            success = true;
        } catch (RuntimeException ex) {
            
            callback.onError(ex);
            
        } finally {
            if (em != null) em.close();
        }       
        
        if (success) callback.onSuccess();
    }
    
}
