package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JournalComptableTest {
   private List<JournalComptable> journalComptables = new ArrayList<>();
   private JournalComptable journalComptable1;
   private JournalComptable journalComptable2;
   private JournalComptable journalComptable3;
   private JournalComptable journalComptable4;

   @Before
   public void init() {
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
   public void getByCode_returnsTheRightJournalComptable_ofListAndAxistingCode() {
      Assert.assertEquals(journalComptable1, JournalComptable.getByCode(journalComptables,journalComptable1.getCode()));
      Assert.assertEquals(journalComptable2, JournalComptable.getByCode(journalComptables,journalComptable2.getCode()));
      Assert.assertEquals(journalComptable3, JournalComptable.getByCode(journalComptables,journalComptable3.getCode()));
      Assert.assertEquals(journalComptable4, JournalComptable.getByCode(journalComptables,journalComptable4.getCode()));
   }

   @Test
   public void getByCode_returnsNull_ofListAndNonExistingCode() {
      Assert.assertNull(JournalComptable.getByCode(journalComptables,"4"));
      Assert.assertNull(JournalComptable.getByCode(journalComptables,"-4"));
      Assert.assertNull(JournalComptable.getByCode(journalComptables,"zer"));
   }
}