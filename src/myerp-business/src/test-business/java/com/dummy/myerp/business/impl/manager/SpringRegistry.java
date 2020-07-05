package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.consumer.db.DataSourcesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.impl.TransactionManager;


/**
 * Registre des Beans Spring.
 */
public final class SpringRegistry {

    /** Logger Log4j pour la classe */
    private static final Logger LOGGER = LogManager.getLogger(SpringRegistry.class);

    /** Instance unique de la classe (design pattern Singleton) */
    private static final SpringRegistry INSTANCE = new SpringRegistry();

    /** Nom des fichiers de contexte de l'application */
    private static final String CONTEXT_PROD_APPLI_LOCATION
          = "classpath:/com/dummy/myerp/business/bootstrapContext.xml";
    private static final String CONTEXT_TEST_APPLI_LOCATION
          = "classpath:/com/dummy/myerp/testbusiness/business/bootstrapContext.xml";


    /** Le context spring de l'application */
    private ApplicationContext contextAppli;


    // ==================== ID des Beans Spring ====================


    /**
     * Constructeur.
     */
    private SpringRegistry() {
        super();
        SpringRegistry.LOGGER.debug("[DEBUT] constructor");

        String databaseType = "TEST";
        SpringRegistry.LOGGER.debug("[DEBUT] getProperty : {}", databaseType);
        try {
            databaseType = System.getProperty("databaseType");
        } catch (Exception exception) {
            LOGGER.info("getProperty Exception",exception);
        }
        SpringRegistry.LOGGER.debug("[FIN] getProperty : {}", databaseType);

        SpringRegistry.LOGGER.debug("[DEBUT] Initialisation du contexte Spring: {}", databaseType);
        if (databaseType.equals(DataSourcesEnum.MYERP.name())) {
            this.contextAppli = new ClassPathXmlApplicationContext(SpringRegistry.CONTEXT_PROD_APPLI_LOCATION);
        } else {
            this.contextAppli = new ClassPathXmlApplicationContext(SpringRegistry.CONTEXT_TEST_APPLI_LOCATION);
        }

        SpringRegistry.LOGGER.debug("[FIN] Initialisation du contexte Spring : {}", databaseType);
        SpringRegistry.LOGGER.debug("[FIN] constructor");
    }

    /**
     * Renvoie l'instance unique de la classe (design pattern Singleton).
     *
     * @return SpringRegistry
     */
    protected static final SpringRegistry getInstance() {
        return SpringRegistry.INSTANCE;
    }

    /**
     * Initialise et charge le contexte Spring
     *
     * @return ApplicationContext
     */
    public static final ApplicationContext init() {
        LOGGER.debug("[DEBUT] init");

        ApplicationContext applicationContext = null;
        // le fait d'appeler cette méthode, déclanche l'appel des initialisation static et donc le chargement du context
        try {
            applicationContext = getInstance().contextAppli;
        } catch (Exception exception) {
            LOGGER.info("init Exception : ", exception);
        }

        LOGGER.debug("[FIN] init");
        return applicationContext;
    }

    /**
     * Récupération d'un bean via Spring.
     *
     * @param pBeanId ID du bean
     * @return Object
     */
    protected static Object getBean(String pBeanId) {
        SpringRegistry.LOGGER.debug("[DEBUT] SpringRegistry.getBean() - Bean ID : {}", pBeanId);
        Object vBean = SpringRegistry.getInstance().contextAppli.getBean(pBeanId);
        SpringRegistry.LOGGER.debug("[FIN] SpringRegistry.getBean() - Bean ID : {}", pBeanId);
        return vBean;
    }


    /**
     * Renvoie l'instance de {@link BusinessProxy} de l'application
     *
     * @return {@link BusinessProxy}
     */
    public static BusinessProxy getBusinessProxy() {
        return (BusinessProxy) SpringRegistry.getBean("BusinessProxy");
    }


    /**
     * Renvoie l'instance de {@link TransactionManager} de l'application
     *
     * @return {@link TransactionManager}
     */
    public static TransactionManager getTransactionManager() {
        return (TransactionManager) SpringRegistry.getBean("TransactionManager");
    }
}
