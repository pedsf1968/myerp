package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.util.Date;


import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ComptabiliteManagerImplTest extends BusinessTestCase {
    private Logger logger = LoggerFactory.getLogger(ComptabiliteManagerImplTest.class);
    private static ComptabiliteManagerImpl manager;
    private static ComptabiliteManager comptabiliteManager;
    private  EcritureComptable vEcritureComptable;
    private static Date date;


    @BeforeAll
    private static void beforeAll() {
        manager = new ComptabiliteManagerImpl();
        comptabiliteManager = getBusinessProxy().getComptabiliteManager();
        date = new Date();
    }

    @BeforeEach
    private void beforeEach() {

        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
    }



    @Test
    @Tag("checkEcritureComptableUnit")
    @DisplayName("Verify that no exception is thrown if the EcritureComptable is correct")
    void checkEcritureComptableUnit() throws FunctionalException {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));

        Assertions.assertThatCode( () -> manager.checkEcritureComptableUnit(vEcritureComptable))
              .doesNotThrowAnyException();

    }

    @Test
    @Tag("checkEcritureComptableUnitViolation")
    @DisplayName("Verify that null checkEcritureComptableUnit thrown FunctionalException")
    void checkEcritureComptableUnitViolation()  {
        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });
    }


    @Test
    @Tag("checkEcritureComptableRG2")
    @DisplayName("When total Debit is greater than total Credit checkEcritureComptableUnit thrown FunctionalException")
    void checkEcritureComptableRG2_throwFunctionalException_ofDebitGreaterThanCredit() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(1234),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableRG2(vEcritureComptable);
        });

    }

    @Test
    @Tag("checkEcritureComptableRG2")
    @DisplayName("When total Debit is less than total Credit checkEcritureComptableUnit thrown FunctionalException")
    void checkEcritureComptableRG2_throwFunctionalException_ofDebitLessThanCredit() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
              null, null,
              new BigDecimal(1234)));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableRG2(vEcritureComptable);
        });

    }

    @Test
    @Tag("checkEcritureComptableRG3")
    @DisplayName("When there are only one Debit LigneEcritureComptable checkEcritureComptableUnit thrown FunctionalException")
    void checkEcritureComptableRG3_returnFunctionalException_ofOnlyOneDebitLigneEcritureComptable() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(
              new LigneEcritureComptable(new CompteComptable(1),
                    null,
                    new BigDecimal(123),
                    null));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableRG3(vEcritureComptable);
        });
    }

    @Test
    @Tag("checkEcritureComptableRG3")
    @DisplayName("When there are only one Credit LigneEcritureComptable checkEcritureComptableUnit throw FunctionalException")
    void checkEcritureComptableRG3_returnFunctionalException_ofOnlyOneCrediLigneEcritureComptable() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(
              new LigneEcritureComptable(new CompteComptable(1),
                    null,
                    null,
                    new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableRG3(vEcritureComptable);
        });
    }

    @Test
    @Tag("checkEcritureComptableUnitRG4")
    @DisplayName("Accept negative values")
    void RG4_returnNoException_ofSameCreditAndDebitNegativeValues() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
              null, new BigDecimal(-123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
              null, null,
              new BigDecimal(-123)));

        Assertions.assertThatCode( () -> manager.checkEcritureComptableUnit(vEcritureComptable))
              .doesNotThrowAnyException();

    }


    @Test
    @Tag("checkEcritureComptableRG5")
    @DisplayName("Bad Reference Journal Code throw FunctionalException")
    void checkEcritureComptableRG5_throwFunctionalException_ofBadJournalCodeInReference() throws Exception {
        vEcritureComptable.setReference("BQ-2016/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
              null, null,
              new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableRG5(vEcritureComptable);
        });
    }

    @Test
    @Tag("checkEcritureComptableRG5")
    @DisplayName("Bad Reference Journal Year throw FunctionalException")
    void checkEcritureComptableRG5_throwFunctionalException_ofBadJournalYearInReference() throws Exception {
        vEcritureComptable.setReference("AC-2018/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
              null, null,
              new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableRG5(vEcritureComptable);
        });

    }

    @Test
    @Tag("checkEcritureComptableRG6")
    @DisplayName("Reference duplication Year throw FunctionalException")
    void checkEcritureComptableRG6_throwFunctionalException_ofReferenceDuplication() throws Exception {
        vEcritureComptable.setReference("AC-2020/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
              null, null,
              new BigDecimal(123)));

        //logger.info("manager : {}", manager.getListEcritureComptable());

        manager.getListEcritureComptable().add(vEcritureComptable);


        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableRG6(vEcritureComptable);
        });
    }

    @Test
    @Tag("checkEcritureComptableRG6")
    @DisplayName("Reference of closed Year throw FunctionalException")
    @Disabled
    void checkEcritureComptableRG6_throwFunctionalException_ofReferenceYearClosed() throws Exception {

            assertThrows(FunctionalException.class, () -> {
                manager.checkEcritureComptableRG6(vEcritureComptable);
            });
    }


}