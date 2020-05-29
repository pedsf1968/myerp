package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.util.Date;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class ComptabiliteManagerImplTest {

    private static ComptabiliteManagerImpl manager;
    private  EcritureComptable vEcritureComptable;

    @BeforeAll
    private static void beforeAll() {
        manager = new ComptabiliteManagerImpl();
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
    public void checkEcritureComptableUnit() throws FunctionalException {
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
    public void checkEcritureComptableUnitViolation()  {
        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });
    }


    @Test
    @Tag("checkEcritureComptableUnitRG2")
    @DisplayName("When total Debit is greater than total Credit checkEcritureComptableUnit thrown FunctionalException")
    public void checkEcritureComptableUnitRG2_throwFunctionalException_ofDebitGreaterThanCredit() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(1234),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });

    }

    @Test
    @Tag("checkEcritureComptableUnitRG2")
    @DisplayName("When total Debit is less than total Credit checkEcritureComptableUnit thrown FunctionalException")
    public void checkEcritureComptableUnitRG2_throwFunctionalException_ofDebitLessThanCredit() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
              null, new BigDecimal(123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
              null, null,
              new BigDecimal(1234)));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });

    }

    @Test
    @Tag("checkEcritureComptableUnitRG3")
    @DisplayName("When there are only one Debit LigneEcritureComptable checkEcritureComptableUnit thrown FunctionalException")
    public void checkEcritureComptableUnitRG3_returnFunctionalException_ofOnlyOneDebitLigneEcritureComptable() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(
              new LigneEcritureComptable(new CompteComptable(1),
                    null,
                    new BigDecimal(123),
                    null));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });
    }

    @Test
    @Tag("checkEcritureComptableUnitRG3")
    @DisplayName("When there are only one Credit LigneEcritureComptable checkEcritureComptableUnit thrown FunctionalException")
    public void checkEcritureComptableUnitRG3_returnFunctionalException_ofOnlyOneCrediLigneEcritureComptable() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(
              new LigneEcritureComptable(new CompteComptable(1),
                    null,
                    null,
                    new BigDecimal(123)));

        assertThrows(FunctionalException.class, () -> {
            manager.checkEcritureComptableUnit(vEcritureComptable);
        });
    }

    @Test
    @Tag("RG4")
    @DisplayName("Accept negative values")
    public void RG4_returnNoException_ofSameCreditAndDebitNegativeValues() throws Exception {
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
              null, new BigDecimal(-123),
              null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
              null, null,
              new BigDecimal(-123)));

        Assertions.assertThatCode( () -> manager.checkEcritureComptableUnit(vEcritureComptable))
              .doesNotThrowAnyException();

    }



}
