/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.rapid;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Huang
 */
@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable {
    private String name;
    private String password;
    private int count = 0;
    private boolean loggedIn = false;
    
    //@PersistenceUnit(unitName="BasicJsfPU")
    //private EntityManagerFactory emf;
    
    //@Resource
    //private UserTransaction utx;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCount() {
        return count;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    
    
    public String login() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            NewEntity ne = new NewEntity(name, password);
            em.persist(ne);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        loggedIn = true;
        return "welcome";
    }
}
