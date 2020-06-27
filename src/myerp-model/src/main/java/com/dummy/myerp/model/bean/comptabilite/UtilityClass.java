package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface UtilityClass {

   /**
    * Compare 2 BigDecimal numbers
    *
    * @param a BigDecimal to be compare with
    * @param b BigDecimal to compare to
    *
    * @return true if they are equals
    */
   static boolean bigDecimalEquals(BigDecimal a, BigDecimal b) {
      if(a==null && b==null) {
         return true;
      }

      if(a!=null && b!=null) {
         return a.compareTo(b) == 0;
      } else {
         return false;
      }
   }

   /**
    *Compare 2 BigDecimal list
    *
    * @param aList list of BigDecimals to be compare with
    * @param bList list of BigDecimals to compare to
    *
    * @return true if they are equals
    */
   static boolean bigDecimalEquals(List<BigDecimal> aList, List<BigDecimal> bList) {
      if(aList.size() != bList.size()) return false;
      Integer index = 0;

      for(BigDecimal bigDecimal: aList) {
         if(!bigDecimalEquals(bigDecimal,bList.get(index++))){
            return false;
         }
      }
      return true;
   }

   /**
    * removeNullFromBigDecimalList : remove null item in BigDecimal list
    *
    * @param bigDecimals list of BigDecimal numbers
    * @return list of BigDecimal numbers without null
    */
   static List<BigDecimal> removeNullFromBigDecimalList(List<BigDecimal> bigDecimals) {
      List<BigDecimal> result = new ArrayList<>();

      for (BigDecimal b : bigDecimals) {
         if (b != null) {
            result.add(b);
         }
      }

      return result;
   }

   /**
    * sumBigDecimalList : sum all BigDecimal numbers
    *
    * @param bigDecimals list of BigDecimal numbers
    * @return BigDecimal sum
    */
   static BigDecimal sumBigDecimalList(List<BigDecimal> bigDecimals) {

      bigDecimals = removeNullFromBigDecimalList(bigDecimals);
      return bigDecimals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
   }

}
