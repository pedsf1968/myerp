package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LigneEcritureComptableTest {
   private static final CompteComptable compteComptable = new CompteComptable(123,"nouveau compte");

   @Test
   @Tag("equals")
   @DisplayName("Verify that 2 identicals LigneEcritureComptable are equals")
   void testEquals_returnTrue_of2SameLigneEcritureComptables() {
      LigneEcritureComptable ligne1 = new LigneEcritureComptable();
      LigneEcritureComptable ligne2 = new LigneEcritureComptable();

      ligne1.setCompteComptable(compteComptable);
      ligne1.setLibelle("vente");
      ligne1.setCredit(new BigDecimal("200.00"));
      ligne1.setDebit(new BigDecimal("123.50"));
      ligne2.setCompteComptable(compteComptable);
      ligne2.setLibelle("vente");
      ligne2.setCredit(new BigDecimal("200"));
      ligne2.setDebit(new BigDecimal("123.5"));


      assertThat(ligne1).isEqualTo(ligne2);
   }

   @Test
   @Tag("equals")
   @DisplayName("Verify that 2 differents LigneEcritureComptable are not equals")
   void testEquals_returnFalse_of2DifferentLigneEcritureComptables() {
      LigneEcritureComptable ligne1 = new LigneEcritureComptable();
      LigneEcritureComptable ligne2 = new LigneEcritureComptable();

      ligne1.setCompteComptable(compteComptable);
      ligne1.setLibelle("vente");
      ligne1.setCredit(new BigDecimal("123.00"));
      ligne1.setDebit(new BigDecimal("123.50"));
      ligne2.setCompteComptable(compteComptable);
      ligne2.setLibelle("vente");
      ligne2.setCredit(new BigDecimal("200"));
      ligne2.setDebit(new BigDecimal("123.5"));


      assertThat(ligne1).isNotEqualTo(ligne2);
   }

}