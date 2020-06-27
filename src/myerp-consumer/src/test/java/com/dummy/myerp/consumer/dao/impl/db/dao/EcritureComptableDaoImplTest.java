package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EcritureComptableDaoImplTest extends ConsumerTestCase {
   private Logger LOGGER = LoggerFactory.getLogger(EcritureComptableDaoImplTest.class);

   private static Date date;
   private static String annee;
   private static EcritureComptable vEcritureComptable, newEC;
   private static List<EcritureComptable> ecritureComptables;
   private static Map<Integer,List<LigneEcritureComptable>> ligneEcritureComptables;

   @BeforeAll
   public static void beforeAll(){
      newEC = new EcritureComptable();
      date = Date.valueOf("2020-06-11");
      annee = "2020";

      ecritureComptables = getDaoProxy().getComptabiliteDao().getListEcritureComptable();
      ligneEcritureComptables = new HashMap<>();

      for (EcritureComptable e: ecritureComptables) {
         ligneEcritureComptables.put(e.getId(),e.getListLigneEcriture());
      }

      // initialise a new EcritureComptable for tests
      CompteComptable compteComptable1 = getDaoProxy().getComptabiliteDao().getListCompteComptable().get(1);
      CompteComptable compteComptable2 = getDaoProxy().getComptabiliteDao().getListCompteComptable().get(2);
      JournalComptable journalComptable = ecritureComptables.get(1).getJournal();
      newEC.setJournal(ecritureComptables.get(1).getJournal());

      newEC.setDate(date);
      newEC.setReference(journalComptable.getCode() + "-" + annee + "/55555");
      newEC.setLibelle("EcritureComptable 55555");
      newEC.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable1,
            "First line EcritureComptable", new BigDecimal(11),
            null));
      newEC.getListLigneEcriture().add(new LigneEcritureComptable(compteComptable2,
            "Second ligne EcritureComptable", null,
            new BigDecimal(11)));

   }

   @BeforeEach
   public void beforeEach(){
      vEcritureComptable = new EcritureComptable();
   }

   @AfterEach
   public void afterEach() {

   }

   @Test
   @Tag("loadListLignEcriture")
   @DisplayName("Load all LigneEcriture of one EcritureComptable")
   void loadListLigneEcriture_returnListLigneEcriture_ofOneEcritureComptable(){

      ligneEcritureComptables.forEach((ecritureId,list) -> {
         vEcritureComptable.setId(ecritureId);
         getDaoProxy().getComptabiliteDao().loadListLigneEcriture(vEcritureComptable);
         assertThat(vEcritureComptable.getListLigneEcriture().size()).isEqualTo(list.size());
      });

   }

   @Test
   @Tag("getListEcritureComptable")
   @DisplayName("Get all EcritureComptable")
   void getListEcritureComptable_returnAllEcritureComptable_ofComptabilité(){
      List<EcritureComptable> list = getDaoProxy().getComptabiliteDao().getListEcritureComptable();

      assertThat(list.size()).isEqualTo(ecritureComptables.size());
   }


   @Test
   @Tag("getEcritureComptable")
   @DisplayName("Get the right EcritureComptable by Id")
   void getEcritureComptable_returnEcritureComptable_byId(){
      Integer ecritureId = null;
      try{
         for (EcritureComptable e: ecritureComptables) {
            ecritureId = e.getId();
            vEcritureComptable = getDaoProxy().getComptabiliteDao().getEcritureComptable(ecritureId);

            assertThat(vEcritureComptable).isNotNull();
            assertThat(vEcritureComptable.getJournal().getCode()).isEqualTo(e.getJournal().getCode());
            assertThat(vEcritureComptable.getReference()).isEqualTo(e.getReference());
            assertThat(vEcritureComptable.getDate()).isEqualTo(e.getDate());
            assertThat(vEcritureComptable.getLibelle()).isEqualTo(e.getLibelle());
         }
      }catch(Exception ex){
         LOGGER.error("EcritureComptable not fount with this id : {}",ecritureId, ex);
         fail();
      }

   }

   @Test
   @Tag("getEcritureComptableByRef")
   @DisplayName("Get the right EcritureComptable by Reference")
   void getEcritureComptableByRef_returnEcritureComptable_byReference(){
      String reference = null;

      try {
         for (EcritureComptable e: ecritureComptables) {
            reference = e.getReference();
            vEcritureComptable = getDaoProxy().getComptabiliteDao().getEcritureComptableByRef(reference);

            assertThat(vEcritureComptable).isNotNull();
            assertThat(vEcritureComptable.getJournal().getCode()).isEqualTo(e.getJournal().getCode());
            assertThat(vEcritureComptable.getId()).isEqualTo(e.getId());
            assertThat(vEcritureComptable.getDate()).isEqualTo(e.getDate());
            assertThat(vEcritureComptable.getLibelle()).isEqualTo(e.getLibelle());
         }

      }catch (Exception exception){
         LOGGER.error("EcritureComptable not found with this Reference : {}", reference, exception);
         fail();
      }
   }

   @Test
   @Tag("getEcritureComptableByRef")
   @DisplayName("Throws NotFoundException if the Reference of EcritureComptable doesn't exist")
   void getEcritureComptableByRef_throwsNotFoundException_OfUnknownReference(){

      assertThrows(NotFoundException.class, () -> {
         getDaoProxy().getComptabiliteDao().getEcritureComptableByRef("oaizz5");
      });
   }

   @Test
   @Tag("getEcritureComptableByRef")
   @DisplayName("Throws NotFoundException if the Reference of EcritureComptable is null")
   void getEcritureComptableByRef_throwsNotFoundException_OfNullReference(){

      assertThrows(NotFoundException.class, () -> {
         getDaoProxy().getComptabiliteDao().getEcritureComptableByRef(null);
      });
   }


   @Test
   @Tag("insertEcritureComptable")
   @DisplayName("Verify that we can insert EcritureComptable")
   void insertEcritureComptable(){
      EcritureComptable found = null;
      newEC.setId(null);
      getDaoProxy().getComptabiliteDao().insertEcritureComptable(newEC);

      assertThat(newEC.getId()).isNotNull();

      try {
         found = getDaoProxy().getComptabiliteDao().getEcritureComptable(newEC.getId());
      }catch (Exception exception){
         LOGGER.error("EcritureComptable inserted not found with Id : {}", newEC.getId(), exception);
         fail();
      }

      assertThat(found.getReference()).isEqualTo(newEC.getReference());
      assertThat(found.getJournal().getCode()).isEqualTo(newEC.getJournal().getCode());
      assertThat(found.getDate()).isEqualTo(newEC.getDate());
      assertThat(found.getLibelle()).isEqualTo(newEC.getLibelle());

      getDaoProxy().getComptabiliteDao().deleteEcritureComptable(newEC.getId());
   }

   @Test
   @Tag("updateEcritureComptable")
   @DisplayName("Assume that we can update an EcritureComptable")
   void updateEcritureComptable() {
      Integer ecritureId = ecritureComptables.get(1).getId();
      vEcritureComptable = newEC;
      vEcritureComptable.setId(ecritureId);

      // change EcritureContable with newEC values
      getDaoProxy().getComptabiliteDao().updateEcritureComptable(vEcritureComptable);

      try {
         vEcritureComptable = getDaoProxy().getComptabiliteDao().getEcritureComptable(ecritureId);
      } catch (Exception exception) {
         LOGGER.error("EcritureComptable inserted not found with Id : {}", newEC.getId(), exception);
         fail();
      }

      assertThat(vEcritureComptable).isNotNull();
      assertThat(vEcritureComptable.getJournal().getCode()).isEqualTo(newEC.getJournal().getCode());
      assertThat(vEcritureComptable.getReference()).isEqualTo(newEC.getReference());
      assertThat(vEcritureComptable.getDate()).isEqualTo(newEC.getDate());
      assertThat(vEcritureComptable.getLibelle()).isEqualTo(newEC.getLibelle());

      // restore EcritureContable with old values
      vEcritureComptable = ecritureComptables.get(1);
      getDaoProxy().getComptabiliteDao().updateEcritureComptable(vEcritureComptable);

      try {
         vEcritureComptable = getDaoProxy().getComptabiliteDao().getEcritureComptable(ecritureId);
      } catch (Exception exception) {
         LOGGER.error("EcritureComptable inserted not found with Id : {}", newEC.getId(), exception);
         fail();
      }

      assertThat(vEcritureComptable).isNotNull();
      assertThat(vEcritureComptable.getJournal().getCode()).isEqualTo(ecritureComptables.get(1).getJournal().getCode());
      assertThat(vEcritureComptable.getReference()).isEqualTo(ecritureComptables.get(1).getReference());
      assertThat(vEcritureComptable.getDate()).isEqualTo(ecritureComptables.get(1).getDate());
      assertThat(vEcritureComptable.getLibelle()).isEqualTo(ecritureComptables.get(1).getLibelle());
   }

   @Test
   @Tag("deleteEcritureComptable")
   @DisplayName("Verify that we can delete an EcritureComptable")
   void deleteEcritureComptable(){
      newEC.setId(null);
      getDaoProxy().getComptabiliteDao().insertEcritureComptable(newEC);
      Integer ecritureId = newEC.getId();
      getDaoProxy().getComptabiliteDao().deleteEcritureComptable(ecritureId);

      assertThrows(NotFoundException.class,() -> {
         getDaoProxy().getComptabiliteDao().getEcritureComptable(ecritureId);
      });

   }

}