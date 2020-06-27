package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UtilityClassTest {
   private static final BigDecimal bigDecimal1 = new BigDecimal("123");
   private static final BigDecimal bigDecimal2 = new BigDecimal("123.00");
   private static final BigDecimal bigDecimal3 = new BigDecimal("43.45");
   private static final List<BigDecimal> bigDecimals1 = new ArrayList<>();
   private static final List<BigDecimal> bigDecimals2 = new ArrayList<>();
   private static final List<BigDecimal> bigDecimals3 = new ArrayList<>();

   @BeforeAll
   private static void beforeAll() {
      bigDecimals1.add(bigDecimal1);
      bigDecimals1.add(bigDecimal2);
      bigDecimals1.add(bigDecimal3);
      bigDecimals2.add(bigDecimal2);
      bigDecimals2.add(bigDecimal1);
      bigDecimals2.add(bigDecimal3);
      bigDecimals3.add(bigDecimal3);
      bigDecimals3.add(bigDecimal2);
      bigDecimals3.add(bigDecimal3);

   }

   @Test
   @Tag("bigDecimalEquals")
   @DisplayName("Verify that 2 BigDecimal same values return TRUE")
   void bigDecimalEquals_returnTrue_of2SameValues() {
      assertThat(UtilityClass.bigDecimalEquals(bigDecimal1,bigDecimal1)).isTrue();
      assertThat(UtilityClass.bigDecimalEquals(bigDecimal1,bigDecimal2)).isTrue();
   }

   @Test
   @Tag("bigDecimalEquals")
   @DisplayName("Verify that 2 BigDecimal null values return TRUE")
   void bigDecimalEquals_returnTrue_of2NullValues() {
      assertThat(UtilityClass.bigDecimalEquals((BigDecimal)null,(BigDecimal)null)).isTrue();
   }

   @Test
   @Tag("bigDecimalEquals")
   @DisplayName("Verify that 2 BigDecimal differents values return FALSE")
   void bigDecimalEquals_returnFalse_of2DifferentValues() {
      assertThat(UtilityClass.bigDecimalEquals(bigDecimal1,bigDecimal3)).isFalse();
      assertThat(UtilityClass.bigDecimalEquals(bigDecimal3,bigDecimal2)).isFalse();
   }

   @Test
   @Tag("bigDecimalEquals")
   @DisplayName("Verify that BigDecimal and Null values comparison return FALSE")
   void bigDecimalEquals_returnFalse_ofBigDecimalAndNull() {
      assertThat(UtilityClass.bigDecimalEquals(bigDecimal1,(BigDecimal)null)).isFalse();
      assertThat(UtilityClass.bigDecimalEquals(bigDecimal3,(BigDecimal)null)).isFalse();
      assertThat(UtilityClass.bigDecimalEquals(bigDecimal3,(BigDecimal)null)).isFalse();
   }


   @Test
   @Tag("bigDecimalEquals")
   @DisplayName("Verify that 2 BigDecimal same List values return TRUE")
   void bigDecimalListEquals_returnTrue_of2SameListOfValues() {
      assertThat(UtilityClass.bigDecimalEquals(bigDecimals1,bigDecimals1)).isTrue();
      assertThat(UtilityClass.bigDecimalEquals(bigDecimals1,bigDecimals2)).isTrue();
   }

   @Test
   @Tag("bigDecimalEquals")
   @DisplayName("Verify that 2 BigDecimal different List values return FALSE")
   void bigDecimalListEquals_returnFalse_of2DifferentListOfValues() {
      assertThat(UtilityClass.bigDecimalEquals(bigDecimals1,bigDecimals3)).isFalse();
      assertThat(UtilityClass.bigDecimalEquals(bigDecimals3,bigDecimals2)).isFalse();
   }

   @Test
   @Tag("removeNullFromBigDecimalList")
   @DisplayName("Verify that 2 BigDecimal different List values return FALSE")
   void removeNullFromBigDecimalList_returnBigDecimalListWithoutNull_ofBigDecimalListWithNulValue() {
      List<BigDecimal> original = new ArrayList<>();
      List<BigDecimal> reference = new ArrayList<>();
      List<BigDecimal> modified = null;

      original.add(bigDecimal1);
      reference.add(bigDecimal1);
      original.add(bigDecimal2);
      original.add(null);
      reference.add(bigDecimal2);
      original.add(bigDecimal3);
      reference.add(bigDecimal3);

      modified = UtilityClass.removeNullFromBigDecimalList(original);

      assertThat(UtilityClass.bigDecimalEquals(original,reference)).isFalse();
      assertThat(UtilityClass.bigDecimalEquals(modified,reference)).isTrue();
   }

   @Test
   @Tag("sumBigDecimalList")
   @DisplayName("Verify that the sum of BigDecimal is what expected")
   void sumBigDecimalList_returnSum_ofBigDecimalList() {
      BigDecimal sum1 = UtilityClass.sumBigDecimalList(bigDecimals1);
      BigDecimal sum2 = UtilityClass.sumBigDecimalList(bigDecimals2);
      BigDecimal sum3 = UtilityClass.sumBigDecimalList(bigDecimals3);

      assertThat(UtilityClass.bigDecimalEquals(sum1,new BigDecimal("289.45"))).isTrue();
      assertThat(UtilityClass.bigDecimalEquals(sum2,new BigDecimal("289.45"))).isTrue();
      assertThat(UtilityClass.bigDecimalEquals(sum3,new BigDecimal("209.90"))).isTrue();

   }
}