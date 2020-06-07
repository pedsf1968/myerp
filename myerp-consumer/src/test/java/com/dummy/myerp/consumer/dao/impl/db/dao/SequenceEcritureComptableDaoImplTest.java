package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class SequenceEcritureComptableDaoImplTest extends ConsumerTestCase {
   private Logger logger = LoggerFactory.getLogger(SequenceEcritureComptableDaoImplTest.class);
   private static Integer annee;
   private static String codeJournal;
   private static Integer derniereValeur;
   private static SequenceEcritureComptable vSEC;

   @BeforeEach
   public void beforeEach(){
      vSEC = new SequenceEcritureComptable();
   }

   @Test
   @Tag("getLastSeqOfTheYear")
   @DisplayName("Verify that we get the last SequenceEcritureComptable")
   public void getLastSeqOfTheYear(){
      annee = 2016;
      codeJournal = "AC";
      derniereValeur = 40;

      vSEC = getDaoProxy().getComptabiliteDao().
            getLastSeqOfTheYear(annee, codeJournal);

      assertThat(vSEC).isNotNull();
      assertThat(vSEC.getAnnee()).isEqualTo(annee);
      assertThat(vSEC.getDerniereValeur()).isEqualTo(derniereValeur);
   }

   @Test
   @Tag("getLastSeqOfTheYear")
   @DisplayName("Verify that we get null if the sequence year doesn't exist")
   public void getLastSeqOfTheYear_returnNull_ofNewSequenceYear(){
      annee = 2025;
      codeJournal = "AC";

      vSEC = getDaoProxy().getComptabiliteDao().getLastSeqOfTheYear(annee, codeJournal);

      assertThat(vSEC).isNull();
   }

   @Test
   @Tag("getLastSeqOfTheYear")
   @DisplayName("Verify that we get null if the journal doesn't exist")
   public void getLastSeqOfTheYear_returnNull_ofNewSequenceJournal(){
      annee = 2016;
      codeJournal = "ZZ";

      vSEC = getDaoProxy().getComptabiliteDao().getLastSeqOfTheYear(annee, codeJournal);

      assertThat(vSEC).isNull();
   }

   @Test
   @Tag("insertSequenceEcritureComptable")
   public void insertSequenceEcritureComptable(){
      try{
         annee = 2020;
         codeJournal = "AC";
         derniereValeur = 111;

         vSEC.setAnnee(annee);
         vSEC.setDerniereValeur(111);
         getDaoProxy().getComptabiliteDao().insertSequenceEcritureComptable(vSEC, codeJournal);
      }catch(Exception exception){
         logger.error("Insertion of  SequenceEcritureComptable failed cause : ",exception);
         fail();
      }

      vSEC = getDaoProxy().getComptabiliteDao().getLastSeqOfTheYear(annee, codeJournal);

      assertThat(vSEC.getAnnee()).isEqualTo(annee);
      assertThat(vSEC.getDerniereValeur()).isEqualTo(derniereValeur);
   }

   @Test
   @Tag("updateSequenceEcritureComptable")
   @DisplayName("Verify that we update the SequenceEcritureComptable")
   public void updateSequenceEcritureComptable(){
      annee = 2016;
      codeJournal = "AC";
      derniereValeur = 555;
      Integer oldValue;

      try{
         vSEC = getDaoProxy().getComptabiliteDao().getLastSeqOfTheYear(annee, codeJournal);
         oldValue = vSEC.getDerniereValeur();
         vSEC.setDerniereValeur(derniereValeur);
         getDaoProxy().getComptabiliteDao().updateSequenceEcritureComptable(vSEC, codeJournal);

         vSEC = getDaoProxy().getComptabiliteDao().getLastSeqOfTheYear(annee, codeJournal);

         assertThat(vSEC.getAnnee()).isEqualTo(annee);
         assertThat(vSEC.getDerniereValeur()).isEqualTo(derniereValeur);

         // restore oldvalue
         vSEC.setDerniereValeur(oldValue);
         getDaoProxy().getComptabiliteDao().updateSequenceEcritureComptable(vSEC, codeJournal);

         vSEC = getDaoProxy().getComptabiliteDao().getLastSeqOfTheYear(annee, codeJournal);

         assertThat(vSEC.getAnnee()).isEqualTo(annee);
         assertThat(vSEC.getDerniereValeur()).isEqualTo(oldValue);


      }catch (Exception exception){
         logger.error("Update of SequenceEcritureComptable failed cause :", exception);
         fail();
      }


  }

}