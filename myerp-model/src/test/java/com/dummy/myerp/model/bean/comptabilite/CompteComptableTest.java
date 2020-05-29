package com.dummy.myerp.model.bean.comptabilite;


import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CompteComptableTest {
   private static final List<CompteComptable> compteComptables = new ArrayList<>();
   private static final Integer numero1 = 2;
   private static final String libelle1 = "compte2";
   private static final CompteComptable compte1 = new CompteComptable(numero1,libelle1);
   private static final CompteComptable compte2 = new CompteComptable(5,"compte5");
   private static final CompteComptable compte3 = new CompteComptable(25,"compte25");
   private static final CompteComptable compte4 = new CompteComptable(48,"compte48");

   @BeforeAll
   public static void beforeAll() {
      compteComptables.add(compte1);
      compteComptables.add(compte2);
      compteComptables.add(compte3);
      compteComptables.add(compte4);
   }

   @AfterEach
   public void afterEach() {
      compte1.setNumero(numero1);
      assertThat(compte1.getNumero()).isEqualTo(numero1);
      compte1.setLibelle(libelle1);
      assertThat(compte1.getLibelle()).isEqualTo(libelle1);
   }

   @Test
   @Tag("getByNumero")
   @DisplayName("In a CompteComptable list we can retreive one by his Numero")
   public void getByNumero_returnsTheRightCompteComptable_ofListAndCompteComptableNumberExisting() {
      assertThat(CompteComptable.getByNumero(compteComptables,numero1)).isEqualTo(compte1);
      assertThat(CompteComptable.getByNumero(compteComptables,compte2.getNumero())).isEqualTo(compte2);
      assertThat(CompteComptable.getByNumero(compteComptables,compte3.getNumero())).isEqualTo(compte3);
      assertThat(CompteComptable.getByNumero(compteComptables,compte4.getNumero())).isEqualTo(compte4);
   }

   @Test
   @Tag("getByNumero")
   @DisplayName("In a CompteComptable list get null if a CompteComptable Numero doesn't exist")
   public void getByNumero_returnsNull_ofListAndCompteComptableNumberDoesntExist() {
      assertThat(CompteComptable.getByNumero(compteComptables,33)).isNull();
   }

   @Test
   @Tag("getByNumero")
   @DisplayName("In a CompteComptable list get null if a CompteComptable Numero is negative")
   public void getByNumero_returnsNull_ofListAndCompteComptableNumberIsNegative() {
      assertThat(CompteComptable.getByNumero(compteComptables,-33)).isNull();
   }

   @Test
   @Tag("getByNumero")
   @DisplayName("In a CompteComptable Null list get null for positive Numero")
   public void getByNumero_returnsNull_ofNullListAndPositiveNumero() {
      assertThat(CompteComptable.getByNumero(null,numero1)).isNull();
   }

   @Test
   @Tag("getByNumero")
   @DisplayName("In a CompteComptable Null list get null for negative Numero")
   public void getByNumero_returnsNull_ofNullListAndNegativeNumero() {
      assertThat(CompteComptable.getByNumero(null,-33)).isNull();
   }

   @Test
   @Tag("getNumero")
   @DisplayName("In a CompteComptable we get the right Numero")
   void getNumero_returnTheNumero_ofCompteComptable() {
      assertThat(compte1.getNumero()).isEqualTo(numero1);
   }

   @Test
   @Tag("setNumero")
   @DisplayName("In a CompteComptable we can change the Numero")
   void setNumero() {
      Integer numero2 = 23;

      compte1.setNumero(numero2);
      assertThat(compte1.getNumero()).isEqualTo(numero2);
   }

   @Test
   @Tag("getLibelle")
   @DisplayName("In a CompteComptable we get the right Libelle")
   void getLibellereturnTheLibelle_ofCompteComptable() {
      assertThat(compte1.getLibelle()).isEqualTo(libelle1);
   }

   @Test
   @Tag("setLibelle")
   @DisplayName("In a CompteComptable we can change the Libelle")
   void setLibelle() {
      String libelle2 = "libelle2";

      compte1.setLibelle(libelle2);
      assertThat(compte1.getLibelle()).isEqualTo(libelle2);

   }

   @Test
   @Tag("testToString")
   @DisplayName("Output the right string for CompteComptable")
   void toString_returnTheString_ofCompteComptable() {
      String compteComptableString = "CompteComptable{numero=2, libelle='compte2'}";

      assertThat(compte1.toString()).isEqualTo(compteComptableString);
   }

   @Test
   @Tag("CompteComptable")
   @DisplayName("The constructor create the right CompteComptable")
   void CompteComptable_returnTheCompteComptable_ofCompteComptableConstructor() {
      assertThat(compte1.getNumero()).isEqualTo(numero1);
      assertThat(compte1.getLibelle()).isEqualTo(libelle1);
   }
}