package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.model.bean.comptabilite.*;
import org.junit.jupiter.api.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ComptabiliteDaoImplTest extends ConsumerTestCase {

   private final static Map<Integer,String> mapCompteComptables = new HashMap<>();
   private final static Map<String,String> mapJournalComptable = new HashMap<>();

   @BeforeAll
   public static void beforeAll(){
      for(CompteComptable c: getDaoProxy().getComptabiliteDao().getListCompteComptable()) {
         mapCompteComptables.put(c.getNumero(),c.getLibelle());
      }

      for(JournalComptable j: getDaoProxy().getComptabiliteDao().getListJournalComptable()){
         mapJournalComptable.put(j.getCode(),j.getLibelle());
      }
   }


   @Test
   @Tag("getListCompteComptable")
   @DisplayName("Verify that we get all CompteComptable")
   public void getListCompteComptable(){
      List<CompteComptable> compteComptables = getDaoProxy().getComptabiliteDao().getListCompteComptable();

      assertThat(compteComptables.size()).isEqualTo(mapCompteComptables.size());
      for(CompteComptable c: compteComptables) {
         assertThat(c.getLibelle()).isEqualTo(mapCompteComptables.get(c.getNumero()));
      }
   }

   @Test
   @Tag("getListJournalComptable")
   @DisplayName("Verify that we get all JournalComptable")
   public void getListJournalComptable(){
      List<JournalComptable> journalComptables = getDaoProxy().getComptabiliteDao().getListJournalComptable();

      assertThat(journalComptables.size()).isEqualTo(mapJournalComptable.size());
      for(JournalComptable j: journalComptables) {
         assertThat(j.getLibelle()).isEqualTo(mapJournalComptable.get(j.getCode()));
      }
   }

}