package com.dummy.myerp.model.bean.comptabilite;


import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


class JournalComptableTest {
   private static final List<JournalComptable> journalComptables = new ArrayList<>();
   private static JournalComptable journalComptable;
   private static final String[] code = {"1", "45", "13", "987"};
   private static final String[] libelle = {"first Journal Comptable", "Journal Comptable 45", "Journal Comptable 13", "Journal Comptable 987"};

   @BeforeAll
   private static void beforeAll() {
      for (int i=0; i<4; i++) {
         journalComptables.add(new JournalComptable(code[i], libelle[i]));
      }

   }

   @BeforeEach
   private void beforeEach() {
      journalComptable = new JournalComptable(code[1], libelle[1]);
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we can retreive one by his Code")
   void getByCode_returnsTheRightJournalComptable_ofListAndAxistingCode() {
      for (int i=0; i<4; i++) {
         assertThat(JournalComptable.getByCode(journalComptables, code[i])).isEqualTo(journalComptables.get(i));
      }
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we get null if Code doesn't exist")
   void getByCode_returnsNull_ofListAndNonExistingCode() {
      assertThat(JournalComptable.getByCode(journalComptables,"4")).isNull();
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we get null if Code is negative value")
   void getByCode_returnsNull_ofListAndNegativeCode() {
      assertThat(JournalComptable.getByCode(journalComptables,"-4")).isNull();
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we get null if Code is null")
   void getByCode_returnsNull_ofListAndNullCode() {
      assertThat(JournalComptable.getByCode(journalComptables,null)).isNull();
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we get null if Code format doesn't match")
   void getByCode_returnsNull_ofListAndBadFormatCode() {
      assertThat(JournalComptable.getByCode(journalComptables,"sdfsd")).isNull();
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we get null if list is null")
   void getByCode_returnsNull_ofNullList() {
      assertThat(JournalComptable.getByCode(null,"4")).isNull();
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we get null if list is empty")
   void getByCode_returnsNull_ofEmptyList() {
      List<JournalComptable> emptyList = new ArrayList<>();

      assertThat(JournalComptable.getByCode(emptyList,"2")).isNull();
   }


   @Test
   @Tag("getCode")
   @DisplayName("Return the Code of a JournalComptable")
   void getCode_returnCode_ofJournalComptable() {
      assertThat(journalComptable.getCode()).isEqualTo(code[1]);
   }

   @Test
   @Tag("setCode")
   @DisplayName("We can change the Code of a JournalComptable")
   void setCode_returnNewCode_ofJournalComptableCodeChanged() {
      journalComptable.setCode(code[2]);
      assertThat(journalComptable.getCode()).isEqualTo(code[2]);
   }

   @Test
   @Tag("getLibelle")
   @DisplayName("Return the Libelle of a JournalComptable")
   void getLibelle_returnLibelle_ofJournalComptable() {
      assertThat(journalComptable.getLibelle()).isEqualTo(libelle[1]);
   }

   @Test
   @Tag("setLibelle")
   @DisplayName("We can change the Libelle of a JournalComptable")
   void setLibelle_returnNewLibelle_ofJournalComptableLibelleChanged() {
      journalComptable.setLibelle(libelle[2]);
      assertThat(journalComptable.getLibelle()).isEqualTo(libelle[2]);
   }

   @Test
   @Tag("toString")
   @DisplayName("testTostring")
   void testToStringreturnTheString_ofJournalComptable() {
      String journalComptableString = "JournalComptable{code='45', libelle='Journal Comptable 45'}";

      assertThat(journalComptables.get(1).toString()).isEqualTo(journalComptableString);
   }

   @Test
   @Tag("hashCode")
   @DisplayName("Verify that hashCode is always the same")
   void testHashCode() {
      assertThat(journalComptable.hashCode()).isEqualTo(journalComptable.hashCode());
   }
}