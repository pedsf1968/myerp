package com.dummy.myerp.model.bean.comptabilite;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class JournalComptableTest {
   private static List<JournalComptable> journalComptables = new ArrayList<>();
   private static JournalComptable journalComptable1;
   private static JournalComptable journalComptable2;
   private static JournalComptable journalComptable3;
   private static JournalComptable journalComptable4;

   @BeforeAll
   public static void beforeAll() {
      journalComptable1 = new JournalComptable("1","first Journal Comptable");
      journalComptable2 = new JournalComptable("45","Journal Comptable 45");
      journalComptable3 = new JournalComptable("13","Journal Comptable 13");
      journalComptable4 = new JournalComptable("987","Journal Comptable 987");

      journalComptables.add(journalComptable1);
      journalComptables.add(journalComptable2);
      journalComptables.add(journalComptable3);
      journalComptables.add(journalComptable4);
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we can retreive one by his Code")
   public void getByCode_returnsTheRightJournalComptable_ofListAndAxistingCode() {
      assertThat(JournalComptable.getByCode(journalComptables,journalComptable1.getCode())).isEqualTo(journalComptable1);
      assertThat(JournalComptable.getByCode(journalComptables,journalComptable2.getCode())).isEqualTo(journalComptable2);
      assertThat(JournalComptable.getByCode(journalComptables,journalComptable3.getCode())).isEqualTo(journalComptable3);
      assertThat(JournalComptable.getByCode(journalComptables,journalComptable4.getCode())).isEqualTo(journalComptable4);
   }

   @Test
   @Tag("getByCode")
   @DisplayName("In a JournalComptable list we get null if Code doesn't exist")
   public void getByCode_returnsNull_ofListAndNonExistingCode() {
      assertThat(JournalComptable.getByCode(journalComptables,"4")).isNull();
      assertThat(JournalComptable.getByCode(journalComptables,"-4")).isNull();
      assertThat(JournalComptable.getByCode(journalComptables,"sdfsd")).isNull();
   }
}