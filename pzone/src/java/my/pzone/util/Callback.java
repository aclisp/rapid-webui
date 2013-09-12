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
public interface Callback {

    public void run(EntityManager em);
    
}
