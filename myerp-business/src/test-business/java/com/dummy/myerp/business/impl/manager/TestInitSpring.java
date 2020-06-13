package com.dummy.myerp.business.impl.manager;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de test de l'initialisation du contexte Spring
 */
class TestInitSpring extends BusinessTestCase {

    /**
     * Constructeur.
     */
    public TestInitSpring() {
        super();
    }


    /**
     * Teste l'initialisation du contexte Spring
     */
    @Test
    void testInit() {
        SpringRegistry.init();
        assertThat(SpringRegistry.getBusinessProxy()).isNotNull();
        assertThat(SpringRegistry.getTransactionManager()).isNotNull();
    }
}
