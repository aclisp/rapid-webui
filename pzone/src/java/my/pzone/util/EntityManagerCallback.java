/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pzone.util;

import javax.persistence.EntityManager;

/**
 *
 * @author ehaohug
 */
public interface EntityManagerCallback {
    
    public void run(EntityManager em);
    
    public void onError(RuntimeException ex);
    
    public void onSuccess();
    
}
