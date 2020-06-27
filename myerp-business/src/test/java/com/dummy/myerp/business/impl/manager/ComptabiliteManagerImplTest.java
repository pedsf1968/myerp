package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;

import com.dummy.myerp.technical.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ComptabiliteManagerImplTest extends BusinessTestCase {
    private static final Logger logger = LoggerFactory.getLogger(ComptabiliteManagerImplTest.class);
    private static ComptabiliteManagerImpl manager;
    private static CompteComptable compteComptable1;
    private static CompteComptable compteComptable2;
    private  EcritureComptable vEcritureComptable;
    private static Date date;


    @BeforeAll
    private static void beforeAll() {
        compteComptable1 = getBusinessProxy().getComptabiliteManager().getListCompteComptable().get(1);
        compteComptable2 = getBusinessProxy().getComptabiliteManager().getListCompteComptable().get(2);
        manager = new ComptabiliteManagerImpl();
        date = Date.valueOf("2016-06-11");
    }

    @BeforeEach
    private void beforeEach() {
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate( date);
        vEcritureComptable.setLibelle("Libelle");
    }



    @Test
    @Tag("checkEcritureComptableUnit")
    @DisplayName("Verify that no exception is thrown if the EcritureComptable is correct")
    void checkEcritureComptableUnit() {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
                                                                                 null, null,
                                                                                 new BigDecimal(123)));

        Assertions.assertThatCode( () -> manager.checkEcritureComptableUnit(vEcritureComptable))
              .doesNotThrowAnyException();

    }

    @Test
    @Tag("checkEcritureComptableUnitViolation")
    @DisplayName("Verify that null checkEcritureComptableUnit thrown FunctionalException")
    void checkEcritureComptableUnitViolation()  {
        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    @Tag("checkEcritureComptableUnitViolation")
    @DisplayName("Verify that  checkEcritureComptableUnit thrown FunctionalException if year Comptable is closed")
    void checkEcritureComptableUnitViolation_throwsFunctionalException_ofBadYearJournalComptable()  {
        vEcritureComptable.setDate(Date.valueOf("2020-07-11"));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableUnit(vEcritureComptable));

        vEcritureComptable.setDate(date);
    }


    @Test
    @Tag("checkEcritureComptableRG2")
    @DisplayName("When total Debit is greater than total Credit checkEcritureComptableUnit thrown FunctionalException")
    void checkEcritureComptableRG2_throwFunctionalException_ofDebitGreaterThanCredit() {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
                                                                                 null, new BigDecimal(1234),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
                                                                                 null, null,
                                                                                 new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG2(vEcritureComptable));

    }

    @Test
    @Tag("checkEcritureComptableRG2")
    @DisplayName("When total Debit is less than total Credit checkEcritureComptableUnit thrown FunctionalException")
    void checkEcritureComptableRG2_throwFunctionalException_ofDebitLessThanCredit() {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
              null, null,
              new BigDecimal(1234)));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG2(vEcritureComptable));

    }

    @Test
    @Tag("checkEcritureComptableRG3")
    @DisplayName("When there are only one Debit LigneEcritureComptable checkEcritureComptableUnit thrown FunctionalException")
    void checkEcritureComptableRG3_returnFunctionalException_ofOnlyOneDebitLigneEcritureComptable() {
        vEcritureComptable.getListLigneEcriture().add(
              new LigneEcritureComptable(compteComptable1,
                    null,
                    new BigDecimal(123),
                    null));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG3(vEcritureComptable));
    }

    @Test
    @Tag("checkEcritureComptableRG3")
    @DisplayName("When there are only one Credit LigneEcritureComptable checkEcritureComptableUnit throw FunctionalException")
    void checkEcritureComptableRG3_returnFunctionalException_ofOnlyOneCrediLigneEcritureComptable() {
        vEcritureComptable.getListLigneEcriture().add(
              new LigneEcritureComptable(compteComptable1,
                    null,
                    null,
                    new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG3(vEcritureComptable));
    }

    @Test
    @Tag("checkEcritureComptableRG3")
    @DisplayName("When there are only one LigneEcritureComptable checkEcritureComptableUnit throw FunctionalException")
    void checkEcritureComptableRG3_returnFunctionalException_ofOnlyOneLigneEcritureComptable() {
        vEcritureComptable.getListLigneEcriture().add(
              new LigneEcritureComptable(compteComptable1,
                    null,
                    new BigDecimal(123),
                    new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG3(vEcritureComptable));
    }

    @Test
    @Tag("checkEcritureComptableUnitRG4")
    @DisplayName("Accept negative values")
    void checkEcritureComptableRG4_returnNoException_ofSameCreditAndDebitNegativeValues() {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
              null, new BigDecimal(-123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
              null, null,
              new BigDecimal(-123)));

        Assertions.assertThatCode( () -> manager.checkEcritureComptableUnit(vEcritureComptable))
              .doesNotThrowAnyException();

    }


    @Test
    @Tag("checkEcritureComptableRG5")
    @DisplayName("Bad Reference Journal Code throw FunctionalException")
    void checkEcritureComptableRG5_throwFunctionalException_ofBadJournalCodeInReference() {
        vEcritureComptable.setReference("BQ-2016/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
              null, null,
              new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG5(vEcritureComptable));
    }

    @Test
    @Tag("checkEcritureComptableRG5")
    @DisplayName("Bad Reference Journal Year throw FunctionalException")
    void checkEcritureComptableRG5_throwFunctionalException_ofBadJournalYearInReference() {
        vEcritureComptable.setReference("AC-2018/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
              null, null,
              new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG5(vEcritureComptable));

    }

    @Test
    @Tag("checkEcritureComptableRG6")
    @DisplayName("Reference duplication Year throw FunctionalException")
    void checkEcritureComptableRG6_throwFunctionalException_ofReferenceDuplication() {

        // all EcritureComptable to try to insert them
        List<EcritureComptable> ecritureComptables = manager.getListEcritureComptable();

        for (EcritureComptable e : ecritureComptables) {
            assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG6(e));
        }
    }



    @Test
    @Tag("checkEcritureComptableRG7")
    @DisplayName("The LigneEcritureComptable Debit amout can't exceed 2 decimals")
    void checkEcritureComptableRG7_throwFunctionalException_ofLigneEcritureComptableWith3DecimalDebitAmount() {
        vEcritureComptable.setReference("AC-2018/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
              null, new BigDecimal("123.123"),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
              null, null,
              new BigDecimal("123.12")));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG7(vEcritureComptable));
    }

    @Test
    @Tag("checkEcritureComptableRG7")
    @DisplayName("The LigneEcritureComptable Credit amout can't exceed 2 decimals")
    void checkEcritureComptableRG7_throwFunctionalException_ofLigneEcritureComptableWith3DecimalCrebitAmount() {
        vEcritureComptable.setReference("AC-2018/00001");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
              null, new BigDecimal("123.12"),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
              null, null,
              new BigDecimal("-123.125")));

        assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableRG7(vEcritureComptable));
    }


    @Test
    @Tag("insertEcritureComptable")
    @DisplayName("Verify tha Ecriture comptable is inserted with his LigneEcritureComptable")
    void insertEcritureComptable_insertTheRightEcritureComptable_ofEcritureComptable() {
        EcritureComptable found = null;

        vEcritureComptable.setReference("AC-2016/00055");
        vEcritureComptable.setDate(date);
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
              null, null,
              new BigDecimal(123)));

        try {
            manager.insertEcritureComptable(vEcritureComptable);
            found = manager.getEcritureComptableById(vEcritureComptable.getId());

        } catch (FunctionalException | NotFoundException exception) {
            logger.error("Exception in insertEcritureComptable :", exception);
        }

        assertThat(found).isEqualTo(vEcritureComptable);

        manager.deleteEcritureComptable(vEcritureComptable.getId());
    }

    @Test
    @Tag("updateEcritureComptable")
    @DisplayName("Verify that we can update the libelle of an Ecriture comptable")
    void updateEcritureComptable_returnNewLibelle_ofLibelleUpdating() {
        String newLibelle = "Nouveau libelle";

        EcritureComptable ecritureComptable = getBusinessProxy().getComptabiliteManager().getListEcritureComptable().get(1);
        Integer id = ecritureComptable.getId();
        String oldLibelle = ecritureComptable.getLibelle();

        ecritureComptable.setLibelle(newLibelle);
        try {
            manager.updateEcritureComptable(ecritureComptable);
            ecritureComptable = manager.getEcritureComptableById(id);
            assertThat(ecritureComptable.getLibelle()).isEqualTo(newLibelle);

            ecritureComptable.setLibelle(oldLibelle);
            manager.updateEcritureComptable(ecritureComptable);
            ecritureComptable = manager.getEcritureComptableById(id);
            assertThat(ecritureComptable.getLibelle()).isEqualTo(oldLibelle);
        } catch ( NotFoundException exception) {
            logger.error("Exception in updateEcritureComptable", exception);
        }
    }

    @Test
    @Tag("updateEcritureComptable")
    @DisplayName("Verify that we can update the reference of an Ecriture comptable")
    void updateEcritureComptable_returnNewReference_ofReferenceUpdating() {
        String newLibelle = "Nouveau libelle";
        String newReference = "AC-2016/00055";
        Date newDate =Date.valueOf("2016-7-11");

        EcritureComptable ecritureComptable = getBusinessProxy().getComptabiliteManager().getListEcritureComptable().get(1);
        Integer id = ecritureComptable.getId();
        String oldLibelle = ecritureComptable.getLibelle();
        String oldReference = ecritureComptable.getReference();
        Date oldDate = ecritureComptable.getDate();

        ecritureComptable.setLibelle(newLibelle);
        ecritureComptable.setReference(newReference);
        ecritureComptable.setDate(newDate);
        try {
            manager.updateEcritureComptable(ecritureComptable);
            ecritureComptable = manager.getEcritureComptableById(id);
            assertThat(ecritureComptable.getLibelle()).isEqualTo(newLibelle);
            assertThat(ecritureComptable.getReference()).isEqualTo(newReference);
            assertThat(ecritureComptable.getDate()).isEqualTo(newDate);

            ecritureComptable.setLibelle(oldLibelle);
            ecritureComptable.setReference(oldReference);
            ecritureComptable.setDate(oldDate);
            manager.updateEcritureComptable(ecritureComptable);
            ecritureComptable = manager.getEcritureComptableById(id);
            assertThat(ecritureComptable.getLibelle()).isEqualTo(oldLibelle);
            assertThat(ecritureComptable.getReference()).isEqualTo(oldReference);
            assertThat(ecritureComptable.getDate()).isEqualTo(oldDate);
        } catch ( NotFoundException exception) {
            logger.error("Exception in updateEcritureComptable", exception);
        }
    }

    @Test
    @Tag("updateEcritureComptable")
    @DisplayName("Verify that we can update the Date of an Ecriture comptable")
    void updateEcritureComptable_returnNewDate_ofDateUpdating() {
        Date newDate =Date.valueOf("2016-7-11");

        EcritureComptable ecritureComptable = getBusinessProxy().getComptabiliteManager().getListEcritureComptable().get(1);
        Integer id = ecritureComptable.getId();
        Date oldDate = ecritureComptable.getDate();

        ecritureComptable.setDate(newDate);
        try {
            manager.updateEcritureComptable(ecritureComptable);
            ecritureComptable = manager.getEcritureComptableById(id);
            assertThat(ecritureComptable.getDate()).isEqualTo(newDate);

            ecritureComptable.setDate(oldDate);
            manager.updateEcritureComptable(ecritureComptable);
            ecritureComptable = manager.getEcritureComptableById(id);
            assertThat(ecritureComptable.getDate()).isEqualTo(oldDate);
        } catch ( NotFoundException exception) {
            logger.error("Exception in updateEcritureComptable", exception);
        }
    }

    @Test
    @Tag("deleteEcritureComptable")
    @DisplayName("Verify that we can delete ecriture comptable")
    void deleteEcritureComptable_returnNotFoundException_ofDeletedEcritureComptable() {
        Integer ecritureId;

        vEcritureComptable.setReference("AC-2016/00055");
        vEcritureComptable.setDate(Date.valueOf("2016-7-11"));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
              null, null,
              new BigDecimal(123)));

        try {
            manager.insertEcritureComptable(vEcritureComptable);
            ecritureId = vEcritureComptable.getId();
            manager.deleteEcritureComptable(ecritureId);

            assertThrows(NotFoundException.class, () -> manager.getEcritureComptableById(ecritureId));

        } catch (FunctionalException exception) {
            logger.error("Exception in insertEcritureComptable :", exception);
        }

    }
}