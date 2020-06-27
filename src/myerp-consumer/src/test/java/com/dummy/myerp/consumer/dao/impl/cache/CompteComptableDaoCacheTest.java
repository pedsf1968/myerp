package com.dummy.myerp.consumer.dao.impl.cache;

import com.dummy.myerp.consumer.dao.impl.db.dao.ConsumerTestCase;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CompteComptableDaoCacheTest extends ConsumerTestCase {
   private final static CompteComptableDaoCache comptableDaoCache = new CompteComptableDaoCache();
   private static List<CompteComptable> compteComptables;

   @BeforeAll
   protected static void beforeAll(){
      compteComptables = getDaoProxy().getComptabiliteDao().getListCompteComptable();
   }

   @Test
   @Tag("getByNumero")
   @DisplayName("Verify that get the right CompteComptable by his number")
   void getByNumero_returnCompteComptable_byHisNumber() {
      CompteComptable found = null;

      for(CompteComptable c: compteComptables) {
         found = comptableDaoCache.getByNumero(c.getNumero());
         assertThat(found.getNumero()).isEqualTo(c.getNumero());
         assertThat(found.getLibelle()).isEqualTo(c.getLibelle());
      }
   }
}