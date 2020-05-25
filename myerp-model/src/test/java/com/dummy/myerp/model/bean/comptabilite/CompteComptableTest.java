package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompteComptableTest {
   private List<CompteComptable> compteComptables = new ArrayList<>();
   private CompteComptable compte1 = new CompteComptable(2,"compte2");
   private CompteComptable compte2 = new CompteComptable(5,"compte5");
   private CompteComptable compte3 = new CompteComptable(25,"compte25");
   private CompteComptable compte4 = new CompteComptable(48,"compte48");


   @Before
   public void init() {
      compteComptables.add(compte1);
      compteComptables.add(compte2);
      compteComptables.add(compte3);
      compteComptables.add(compte4);
   }

   @Test
   public void givenCompteComptableList_whenAskAnExistingNumber_ThenReturnTheRightCompteCompable() {
      Assert.assertEquals(compte1,CompteComptable.getByNumero(compteComptables,2));
      Assert.assertEquals(compte2,CompteComptable.getByNumero(compteComptables,5));
      Assert.assertEquals(compte3,CompteComptable.getByNumero(compteComptables,25));
      Assert.assertEquals(compte4,CompteComptable.getByNumero(compteComptables,48));
   }

   @Test
   public void givenCompteComptableList_whenAskAnNoExistingNumber_ThenReturnNull() {
      Assert.assertNull(CompteComptable.getByNumero(compteComptables,3));
   }

}