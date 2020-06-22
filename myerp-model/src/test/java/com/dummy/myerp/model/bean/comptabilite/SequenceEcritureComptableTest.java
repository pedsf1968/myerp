package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SequenceEcritureComptableTest {
   private static final SequenceEcritureComptable sequence1 = new SequenceEcritureComptable(2020,55);
   private static final SequenceEcritureComptable sequence2 = new SequenceEcritureComptable(1914,18);

   @Test
   @Tag("toString")
   @DisplayName("Output the right string for SequenceEcritureComptable")
   void toString_returnTheString_ofSequenceEcritureComptable() {
      assertThat(sequence1.toString()).isEqualTo("SequenceEcritureComptable{annee=2020, derniereValeur=55}");
      assertThat(sequence2.toString()).isEqualTo("SequenceEcritureComptable{annee=1914, derniereValeur=18}");
   }

   @Test
   @Tag("equals")
   @DisplayName("Verify that 2 identicals SequenceEcritureComptable are equals")
   void testEquals_returnTrue_of2SameSequenceEcritureComptables() {
      assertThat(sequence1.equals(sequence1)).isTrue();
   }

   @Test
   @Tag("equals")
   @DisplayName("Verify that 2 differents SequenceEcritureComptable are not equals")
   void testEquals_returnFalse_of2DifferentsSequenceEcritureComptables() {
      assertThat(sequence1.equals(sequence2)).isFalse();
   }

   @Test
   @Tag("hashCode")
   @DisplayName("Verify that hashCode is always the same")
   void testHashCode() {
      assertThat(sequence1.hashCode()).isEqualTo(sequence1.hashCode());
      assertThat(sequence2.hashCode()).isEqualTo(sequence2.hashCode());
   }
}