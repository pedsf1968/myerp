package com.dummy.myerp.consumer.dao.impl.db.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Classe de test de l'initialisation du contexte Spring
 */
class TestInitSpring extends ConsumerTestCase {

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
    @Tag("testInit")
    @DisplayName("Verify the initialisation of the context")
    void testInit() {

        SpringRegistry.init();
        assertThat(SpringRegistry.getDaoProxy()).isNotNull();
    }
}
