package com.dummy.myerp.model.bean.comptabilite;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CompteComptableTest {
   private static List<CompteComptable> compteComptables = new ArrayList<>();
   private static CompteComptable compte1 = new CompteComptable(2,"compte2");
   private static CompteComptable compte2 = new CompteComptable(5,"compte5");
   private static CompteComptable compte3 = new CompteComptable(25,"compte25");
   private static CompteComptable compte4 = new CompteComptable(48,"compte48");

   @BeforeAll
   public static void beforeAll() {
      compteComptables.add(compte1);
      compteComptables.add(compte2);
      compteComptables.add(compte3);
      compteComptables.add(compte4);
   }

   @Test
   @Tag("getByNumero")
   @DisplayName("In a CompteComptable list we can retreive one by his Numero")
   public void getByNumero_returnsTheRightCompteComptable_ofListAndCompteComptableNumberExisting() {
      assertThat(CompteComptable.getByNumero(compteComptables,compte1.getNumero())).isEqualTo(compte1);
      assertThat(CompteComptable.getByNumero(compteComptables,compte2.getNumero())).isEqualTo(compte2);
      assertThat(CompteComptable.getByNumero(compteComptables,compte3.getNumero())).isEqualTo(compte3);
      assertThat(CompteComptable.getByNumero(compteComptables,compte4.getNumero())).isEqualTo(compte4);
   }

   @Test
   @Tag("getByNumero")
   @DisplayName("In a CompteComptable list get null if a CompteComptable Numero doesn't exist")
   public void getByNumero_returnsNull_ofListAndCompteComptableNumberDoesntExisting() {
      assertThat(CompteComptable.getByNumero(compteComptables,33)).isNull();
      assertThat(CompteComptable.getByNumero(compteComptables,-33)).isNull();
   }

}