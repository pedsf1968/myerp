package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.impl.dao.ConsumerTestCase;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ComptabiliteDaoImplTest extends ConsumerTestCase {

   private static Date date;
   private static String annee;
   private static EcritureComptable vEcritureComptable;

   @BeforeAll
   public static void initialization(){
      date = new Date();
   }

   @BeforeEach
   public void init(){
      vEcritureComptable = new EcritureComptable();
   }

   @Test
   @Tag("getListCompteComptable")
   public void getListCompteComptable(){
      List<CompteComptable> list = getDaoProxy().getComptabiliteDao().getListCompteComptable();

      assertThat(list.size()).isEqualTo(7);
   }



   @Test
   @Tag("getListJournalComptable")
   public void getListJournalComptable(){
      List<JournalComptable> list = getDaoProxy().getComptabiliteDao().getListJournalComptable();

      assertThat(list.size()).isEqualTo(4);
   }

   // ==================== SequenceEcritureComptable ==================== //
   @Test
   public void getLastSeqOfTheYear(){

      SequenceEcritureComptable vSEC = getDaoProxy().getComptabiliteDao().
            getLastSeqOfTheYear("2016", "OD");

      assertThat(vSEC).isNotNull();
      assertThat(vSEC.getAnnee()).isEqualTo("2016");
      assertThat(vSEC.getDerniereValeur()).isEqualTo(88);
   }


   @Test
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
   public void updateSequenceEcritureComptable(){
      try{
         SequenceEcritureComptable vSeq = getDaoProxy().getComptabiliteDao().getLastSeqOfTheYear("2016", "OD");
         vSeq.setAnnee(vSeq.getAnnee()+2);
         vSeq.setDerniereValeur(vSeq.getDerniereValeur()+2);
         getDaoProxy().getComptabiliteDao().updateSequenceEcritureComptable(vSeq, "OD");
      }catch (Exception e){
         fail("La mise a jour de la sequence comptable a echoué");
      }
  }

}