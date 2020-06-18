package com.dummy.myerp.consumer.dao.impl.cache;

import com.dummy.myerp.consumer.dao.impl.db.dao.ConsumerTestCase;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JournalComptableDaoCacheTest extends ConsumerTestCase {
   private final static JournalComptableDaoCache journalComptableDaoCache = new JournalComptableDaoCache();
   private static List<JournalComptable> journalComptables = new ArrayList<>();

   @BeforeAll
   protected static void beforeAll(){
      journalComptables = getDaoProxy().getComptabiliteDao().getListJournalComptable();
   }

   @Test
   @Tag("getByCode")
   @DisplayName("Verify that get the right journalComptable by his code")
   void getByCode_returnjournalComptable_byHisCode() {
      JournalComptable found = null;

      for(JournalComptable j: journalComptables) {
         found = journalComptableDaoCache.getByCode(j.getCode());
         assertThat(found.getCode()).isEqualTo(j.getCode());
         assertThat(found.getLibelle()).isEqualTo(j.getLibelle());
      }

   }
}