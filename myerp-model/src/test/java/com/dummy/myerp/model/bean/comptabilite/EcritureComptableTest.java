package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class EcritureComptableTest {
    private static EcritureComptable ecritureComptable;
    private static Integer id = 123;
    private static JournalComptable journalComptable;
    private static String reference = "BQ-2020/00011";
    private static Date date = new Date();
    private static String libelle = "Equilibrée";
    private static BigDecimal[] debits = { new BigDecimal("200.50"), new BigDecimal("100.50"), null, new BigDecimal("40")};
    private static BigDecimal[] credits = { null, new BigDecimal("33"), new BigDecimal("301"), new BigDecimal("7")};
    private static BigDecimal debitSum;
    private static BigDecimal creditSum;


    private List<BigDecimal> removeNull(List<BigDecimal> bigDecimals){
        List<BigDecimal> result = new ArrayList<>();

        for(BigDecimal b: bigDecimals){
            if (b!=null){
                result.add(b);
            }
        }

        return result;
    }

    private BigDecimal sum(List<BigDecimal> bigDecimals) {

        bigDecimals = removeNull(bigDecimals);
        return bigDecimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        return new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
    }

    @BeforeAll
    private static void beforeAll() {
      journalComptable = new JournalComptable("11","Journal comptable 11");
    }

    @BeforeEach
    private void beforeEach() {
       ecritureComptable = new EcritureComptable();

       ecritureComptable.setId(id);
       ecritureComptable.setJournal(journalComptable);
       ecritureComptable.setDate(date);
       ecritureComptable.setLibelle(libelle);
       String debitValue, creditValue;

        for(int i=0; i<4; i++) {
            debitValue = debits[i]!=null?debits[i].toString():null;
            creditValue = credits[i]!=null?credits[i].toString():null;
           ecritureComptable.getListLigneEcriture().add(this.createLigne(i<2?1:2,debitValue,creditValue));
        }

        debitSum = sum(Arrays.asList(debits));
        creditSum = sum(Arrays.asList(credits));

    }

    @Test
    @Tag("isEquilibree")
    @DisplayName("If the EcritureComptable is balanced than return true")
    public void isEquilibree_returnsTrue_ofEcritureComptableEquilibree() {

        assertThat(creditSum.byteValue()).isEqualTo(debitSum.byteValue());
        assertThat(ecritureComptable.isEquilibree()).isTrue();
    }

    @Test
    @Tag("isEquilibree")
    @DisplayName("If the EcritureComptable is not balanced than return false")
    public void isEquilibree_returnsFalse_ofEcritureComptableNotEquilibree() {
       ecritureComptable.getListLigneEcriture().add(this.createLigne(3, null, "20"));
       ecritureComptable.getListLigneEcriture().add(this.createLigne(3, "21", "2"));

        assertThat(ecritureComptable.isEquilibree()).isFalse();
    }


    @Test
    @Tag("getTotalDebit")
    @DisplayName("Return the right total Debit of the EcritureComptable")
    public void getTotalDebit_returnsTotalDebit_ofEcritureComptable(){

        assertThat(ecritureComptable.getTotalDebit()).isEqualTo(debitSum);
    }

    @Test
    @Tag("getTotalCredit")
    @DisplayName("Return the right total Credit of the EcritureComptable")
    public void getTotalDebit_returnsTotalCredit_ofEcritureComptable(){

        assertThat(ecritureComptable.getTotalCredit()).isEqualTo(creditSum);
    }

   @Test
   @Tag("getId")
   @DisplayName("Return the right id of EcritureComptable")
   void getId() {
      assertThat(ecritureComptable.getId()).isEqualTo(id);

   }

   @Test
   @Tag("setId")
   @DisplayName("Change the id of EcritureComptable")
   void setId() {
       Integer newId = 465;
       ecritureComptable.setId(newId);

      assertThat(ecritureComptable.getId()).isEqualTo(newId);
   }

   @Test
   @Tag("getJournal")
   @DisplayName("Return the right JournalComptabler of EcritureComptable")
   void getJournal() {
      assertThat(ecritureComptable.getJournal()).isEqualTo(journalComptable);
   }

   @Test
   @Tag("setJournal")
   @DisplayName("Change the JournalComptabler of EcritureComptable")
   void setJournal() {
      JournalComptable newJournalComptable = new JournalComptable("37","Journal comptable 37");

      ecritureComptable.setJournal(newJournalComptable);

      assertThat(ecritureComptable.getJournal()).isEqualTo(newJournalComptable);
   }

   @Test
   @Tag("getReference")
   @DisplayName("Return the right Reference of EcritureComptable")
   @Disabled
   void getReference() {
      assertThat(ecritureComptable.getReference()).isEqualTo(reference);
   }

   @Test
   @Tag("setReference")
   @DisplayName("Change the Reference of EcritureComptable")
   void setReference() {
       String newReference = "BQ-2016/00001";

       ecritureComptable.setReference(newReference);

      assertThat(ecritureComptable.getReference()).isEqualTo(newReference);
   }

   @Test
   @Tag("getDate")
   @DisplayName("Return the right Date of EcritureComptable")
   void getDate() {
       assertThat(ecritureComptable.getDate()).isEqualTo(date);
   }

   @Test
   @Tag("setDate")
   @DisplayName("Change the Date of EcritureComptable")
   void setDate() throws ParseException {
       DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
       Date newDate = df.parse("09/11/1988");

       ecritureComptable.setDate(newDate);

       assertThat(ecritureComptable.getDate()).isEqualTo(newDate);
   }

   @Test
   @Tag("getLibelle")
   @DisplayName("Return the right Libelle of EcritureComptable")
   void getLibelle() {
       assertThat(ecritureComptable.getLibelle()).isEqualTo(libelle);
   }

   @Test
   @Tag("setLibelle")
   @DisplayName("Change the Libelle of EcritureComptable")
   void setLibelle() {
       String newLibelle = "Nouvelle ecriture comptable";

       ecritureComptable.setLibelle(newLibelle);

       assertThat(ecritureComptable.getLibelle()).isEqualTo(newLibelle);
   }
}
