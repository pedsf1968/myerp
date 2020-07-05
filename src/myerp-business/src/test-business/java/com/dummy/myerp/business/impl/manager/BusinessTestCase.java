package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.impl.TransactionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe mère des classes de test d'intégration de la couche Business
 */
public abstract class BusinessTestCase {
    private static final Logger LOGGER = LogManager.getLogger(BusinessTestCase.class);


    static {
        LOGGER.debug("[DEBUT] static");
        SpringRegistry.init();
        LOGGER.debug("[FIN] static");
    }

    /** {@link BusinessProxy} */
    private static final BusinessProxy BUSINESS_PROXY = SpringRegistry.getBusinessProxy();
    /** {@link TransactionManager} */
    private static final TransactionManager TRANSACTION_MANAGER = SpringRegistry.getTransactionManager();


    // ==================== Constructeurs ====================
    /**
     * Constructeur.
     */
    public BusinessTestCase() {
    }


    // ==================== Getters/Setters ====================
    public static BusinessProxy getBusinessProxy() {
        return BUSINESS_PROXY;
    }

    public static TransactionManager getTransactionManager() {
        return TRANSACTION_MANAGER;
    }
}
