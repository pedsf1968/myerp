package com.dummy.myerp.testconsumer.consumer;

import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class SequenceEcritureComptableDaoImplTest extends ConsumerTestCase {

   private static Date date;
   private static Integer annee;
   private static SequenceEcritureComptable vSequenceEcritureComptable;

   @BeforeAll
   public static void beforeAll(){
      date = new Date();
   }

   @BeforeEach
   public void beforeEach(){
      vSequenceEcritureComptable = new SequenceEcritureComptable();
   }

   @Test
   @Tag("getLastSeqOfTheYear")
   @DisplayName("Verify that we get the last SequenceEcritureComptable")
   public void getLastSeqOfTheYear(){


      SequenceEcritureComptable vSEC = getDaoProxy().getComptabiliteDao().
            getLastSeqOfTheYear(2016, "OD");

      assertThat(vSEC).isNotNull();
      assertThat(vSEC.getAnnee()).isEqualTo("2016");
      assertThat(vSEC.getDerniereValeur()).isEqualTo(88);
   }


   @Test
   @Tag("insertSequenceEcritureComptable")
   @Disabled
   public void insertSequenceEcritureComptable(){
      try{
         SequenceEcritureComptable vSEC = new SequenceEcritureComptable();
         vSEC.setAnnee(2018);
         vSEC.setDerniereValeur(104);
         getDaoProxy().getComptabiliteDao().insertSequenceEcritureComptable(vSEC, "BQ");
      }catch(Exception e){
         fail("L'insertion de la sequence comptable a echoué");
      }


   }

   @Test
   @Tag("updateSequenceEcritureComptable")
   @Disabled
   public void updateSequenceEcritureComptable(){
      try{
         SequenceEcritureComptable vSeq = getDaoProxy().getComptabiliteDao().getLastSeqOfTheYear(2016, "OD");
         vSeq.setAnnee(vSeq.getAnnee()+2);
         vSeq.setDerniereValeur(vSeq.getDerniereValeur()+2);
         getDaoProxy().getComptabiliteDao().updateSequenceEcritureComptable(vSeq, "OD");
      }catch (Exception e){
         fail("La mise a jour de la sequence comptable a echoué");
      }
  }

}