package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SequenceEcritureComptableTest {
   private static final SequenceEcritureComptable sequence1 = new SequenceEcritureComptable(2020,55);
   private static final SequenceEcritureComptable sequence2 = new SequenceEcritureComptable(1914,18);

   @Test
   @Tag("toString")
   @DisplayName("Output the right string for SequenceEcritureComptable")
   void toString_returnTheString_ofSequenceEcritureComptable() {
      assertThat(sequence1).hasToString("SequenceEcritureComptable{annee=2020, derniereValeur=55}");
      assertThat(sequence2).hasToString("SequenceEcritureComptable{annee=1914, derniereValeur=18}");
   }

   @Test
   @Tag("equals")
   @DisplayName("Verify that 2 identicals SequenceEcritureComptable are equals")
   void testEquals_returnTrue_of2SameSequenceEcritureComptables() {
      assertThat(sequence1).isEqualTo(sequence1);
   }

   @Test
   @Tag("equals")
   @DisplayName("Verify that 2 differents SequenceEcritureComptable are not equals")
   void testEquals_returnFalse_of2DifferentsSequenceEcritureComptables() {
      assertThat(sequence1).isNotEqualTo(sequence2);
   }

   @Test
   @Tag("hashCode")
   @DisplayName("Verify that hashCode is always the same")
   void testHashCode_returnSameHashCode_of2SameSequence() {
      assertThat(sequence1).hasSameHashCodeAs(sequence1);
      assertThat(sequence2).hasSameHashCodeAs(sequence2);
   }

   @Test
   @Tag("hashCode")
   @DisplayName("Verify that hashCode is different for two different sequence")
   void testHashCode_returnDifferentHashCode_of2DifferentSequence() {
      assertThat(sequence1.hashCode()).isNotEqualTo(sequence2.hashCode());
      assertThat(sequence2.hashCode()).isNotEqualTo(sequence1.hashCode());
   }
}