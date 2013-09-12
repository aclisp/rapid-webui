/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.pzone.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import my.pzone.entity.Customer;
import my.pzone.util.EntityManagerCallback;
import my.pzone.util.EntityManagerHelper;

/**
 *
 * @author Huang
 */
@ManagedBean
@SessionScoped
public class UserBean {
    @ManagedProperty(value="#{entityManagerHelper}")
    private EntityManagerHelper entityManagerHelper;
    
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

    public void setEntityManagerHelper(EntityManagerHelper entityManagerHelper) {
        this.entityManagerHelper = entityManagerHelper;
    }
    
    // FIXME transaction rollback?
    public String addCustomer() {
        assert entityManagerHelper != null;
        EntityManager em = null;
        EntityTransaction tx = null;
        
        try {
            em = entityManagerHelper.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            Customer c = new Customer();
            c.setCustomerName(customerName);
            c.setCustomerPassword(customerPassword);
            em.persist(c);
            
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null && tx.isActive()) tx.rollback();
            addWarnMessage("Something wrong!", ex.getMessage());
            throw ex;
        } finally {
            if (em != null) em.close();
        }
        
        addInfoMessage("Add successfully!", "Hello " + customerName + ", Your data is saved.");
        return "addCustomer"; // TODO show output?
    }
    
    public String addCustomer1() {
        assert entityManagerHelper != null;
        
        entityManagerHelper.runWithinTransaction(new EntityManagerCallback() {
            public void run(EntityManager em) {
                Customer c = new Customer();
                c.setCustomerName(customerName);
                c.setCustomerPassword(customerPassword);
                em.persist(c);
            }


            public void onError(RuntimeException ex) {
                addWarnMessage("Something wrong!", ex.getMessage());
            }


            public void onSuccess() {
                addInfoMessage("Add successfully!", "Hello " + customerName + ", your data is saved.");
            }
        });
        
        return "addCustomer";
    }
    
    private void addInfoMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }
    
    private void addWarnMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }
}
