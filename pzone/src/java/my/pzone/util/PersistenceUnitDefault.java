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
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;

/**
 *
 * @author Huang
 */
@ManagedBean(eager=true, name="pzonePU")
@ApplicationScoped
public class PersistenceUnitDefault extends EntityManagerTemplate {
    
    private static final Logger logger = Logger.getLogger(PersistenceUnitDefault.class.getName());
    private static final String PERSISTENCE_UNITNAME = "pzonePU";
        
    @PostConstruct
    public void initialize() {
        logger.info("#### init PersistenceUnitDefault - " + PERSISTENCE_UNITNAME);
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNITNAME);
        assert entityManagerFactory != null;
    }
    
    @PreDestroy
    public void shutdown() {
        logger.info("#### shutdown PersistenceUnitDefault - " + PERSISTENCE_UNITNAME);
        entityManagerFactory.close();
    }
    
    public static PersistenceUnitDefault getInstance() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{" + PERSISTENCE_UNITNAME + "}", PersistenceUnitDefault.class);
    }
}
