/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pzone.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import my.pzone.entity.Customer;
import my.pzone.util.EntityManagerHelper;

/**
 *
 * @author Huang
 */
@ManagedBean
@SessionScoped
public class UserBean {
    @ManagedProperty(value="#{entityManagerHelper}")
    private EntityManagerHelper emh;
    
    // FIXME
    private String customerName;
    private String customerPassword;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    
    
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public void setEmh(EntityManagerHelper emh) {
        this.emh = emh;
    }
    
    // FIXME transaction rollback?
    public String addCustomer() {
        assert emh != null;
        EntityManager em = emh.createEntityManager();
        assert em != null;
        
        try {
            em.getTransaction().begin();
            
            Customer c = new Customer();
            c.setCustomerName(customerName);
            c.setCustomerPassword(customerPassword);
            
            em.persist(c);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        return "addCustomer"; // TODO show output?
    }
}
