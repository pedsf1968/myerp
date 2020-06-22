package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class EcritureComptableTest  {
   private static final Integer id = 123;
   private static final String reference = "BQ-2020/00011";
   private static final Date date = Date.valueOf("2020-06-18");
   private static final String libelle = "Equilibrée";
   private static final BigDecimal[] debits = {new BigDecimal("200.50"), new BigDecimal("100.50"), null, new BigDecimal("40")};
   private static final BigDecimal[] credits = {null, new BigDecimal("33"), new BigDecimal("301"), new BigDecimal("7")};
   private static EcritureComptable ecritureComptable;
   private static JournalComptable journalComptable;
   private static BigDecimal debitSum;
   private static BigDecimal creditSum;


   private String getCodeJournalFromReference(String reference) {
      return reference.split("-")[0];
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
      journalComptable = new JournalComptable("BQ", "Journal comptable 11");
   }

   @BeforeEach
   private void beforeEach() {
      ecritureComptable = new EcritureComptable();

      ecritureComptable.setId(id);
      ecritureComptable.setJournal(journalComptable);
      ecritureComptable.setReference(reference);
      ecritureComptable.setDate(date);
      ecritureComptable.setLibelle(libelle);
      String debitValue, creditValue;

      for (int i = 0; i < 4; i++) {
         debitValue = debits[i] != null ? debits[i].toString() : null;
         creditValue = credits[i] != null ? credits[i].toString() : null;
         ecritureComptable.getListLigneEcriture().add(this.createLigne(i < 2 ? 1 : 2, debitValue, creditValue));
      }

      debitSum = UtilityClass.sumBigDecimalList(Arrays.asList(debits));
      creditSum = UtilityClass.sumBigDecimalList(Arrays.asList(credits));

   }

   @Test
   @Tag("isEquilibree")
   @DisplayName("If the EcritureComptable is balanced than return true")
   void isEquilibree_returnsTrue_ofEcritureComptableEquilibree() {

      assertThat(creditSum.byteValue()).isEqualTo(debitSum.byteValue());
      assertThat(ecritureComptable.isEquilibree()).isTrue();
   }

   @Test
   @Tag("isEquilibree")
   @DisplayName("If the EcritureComptable is not balanced than return false")
   void isEquilibree_returnsFalse_ofEcritureComptableNotEquilibree() {
      ecritureComptable.getListLigneEcriture().add(this.createLigne(3, null, "20"));
      ecritureComptable.getListLigneEcriture().add(this.createLigne(3, "21", "2"));

      assertThat(ecritureComptable.isEquilibree()).isFalse();
   }


   @Test
   @Tag("getTotalDebit")
   @DisplayName("Return the right total Debit of the EcritureComptable")
   void getTotalDebit_returnsTotalDebit_ofEcritureComptable() {

      assertThat(ecritureComptable.getTotalDebit()).isEqualTo(debitSum);
   }

   @Test
   @Tag("getTotalCredit")
   @DisplayName("Return the right total Credit of the EcritureComptable")
   void getTotalDebit_returnsTotalCredit_ofEcritureComptable() {

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
      JournalComptable newJournalComptable = new JournalComptable("37", "Journal comptable 37");

      ecritureComptable.setJournal(newJournalComptable);

      assertThat(ecritureComptable.getJournal()).isEqualTo(newJournalComptable);
   }

   @Test
   @Tag("getReference")
   @DisplayName("Return the right Reference of EcritureComptable")
   void getReference() {
      assertThat(ecritureComptable.getReference()).isEqualTo(reference);
   }

   @Test
   @Tag("setReference")
   @DisplayName("Change the Reference of EcritureComptable with good regex")
   void setReference_changeReference_ofEcritureComptableWithGoodRegex() {
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
      Date newDate = Date.valueOf("1988-11-09");

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

   @Test
   @Tag("toString")
   @DisplayName("Verify that the class output is well formated")
   void toStringTest() {

      String output = "EcritureComptable{id=123, journal=JournalComptable{code='BQ', libelle='Journal comptable 11'}, " +
            "reference='BQ-2020/00011', date=" +
            ecritureComptable.getDate() +
            ", libelle='Equilibrée', totalDebit=341.00, totalCredit=341, " +
            "listLigneEcriture=[\n" +
            "LigneEcritureComptable{compteComptable=CompteComptable{numero=1, libelle='null'}, libelle='200.50', debit=200.50, credit=null}\n" +
            "LigneEcritureComptable{compteComptable=CompteComptable{numero=1, libelle='null'}, libelle='67.50', debit=100.50, credit=33}\n" +
            "LigneEcritureComptable{compteComptable=CompteComptable{numero=2, libelle='null'}, libelle='-301', debit=null, credit=301}\n" +
            "LigneEcritureComptable{compteComptable=CompteComptable{numero=2, libelle='null'}, libelle='33', debit=40, credit=7}\n" +
            "]}";
      assertThat(ecritureComptable.toString()).isEqualTo(output);
   }

   @Test
   @Tag("equals")
   @DisplayName("Verify that 2 identicals EcritureComptable are equals")
   void testEquals_returnTrue_of2SameEcritureComptables() {
      assertThat(ecritureComptable.equals(ecritureComptable)).isTrue();
   }

   @Test
   @Tag("equals")
   @DisplayName("Verify that 2 differents EcritureComptable are not equals")
   void testEquals_returnFalse_of2DifferentsEcritureComptables() {
      EcritureComptable reference = new EcritureComptable();
      reference.setId(ecritureComptable.getId());
      reference.setDate(ecritureComptable.getDate());
      reference.setReference(ecritureComptable.getReference());
      reference.setLibelle(ecritureComptable.getLibelle());
      reference.setJournal(ecritureComptable.getJournal());

      assertThat(ecritureComptable.equals(reference)).isFalse();
   }

   @Test
   @Tag("hashCode")
   @DisplayName("Verify that hashCode is always the same")
   void testHashCode() {
      assertThat(ecritureComptable.hashCode()).isEqualTo(ecritureComptable.hashCode());
   }
}