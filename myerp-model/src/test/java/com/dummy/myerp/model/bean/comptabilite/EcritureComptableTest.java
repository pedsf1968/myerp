package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;



public class EcritureComptableTest {
    private EcritureComptable vEcriture;


    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        return new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
    }


    @Test
    public void isEquilibree_returnsTrue_ofEcritureComptableEquilibree() {
        vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());
    }

    @Test
    public void isEquilibree_returnsFalse_ofEcritureComptableNotEquilibree() {
        vEcriture = new EcritureComptable();

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());
    }


    @Test
    public void getTotalDebit_returnsTotalDebit_ofEcritureComptable(){
        vEcriture = new EcritureComptable();
        BigDecimal[] debits = { new BigDecimal("200.50"), new BigDecimal("100.50"), null, new BigDecimal("40")};
        BigDecimal[] credits = { null, new BigDecimal("33"), new BigDecimal("301"), new BigDecimal("7")};


        vEcriture.setLibelle("Equilibrée");

        String debitValue, creditValue;
        for(int i=0; i<4; i++) {
            debitValue = debits[i]!=null?debits[i].toString():null;
            creditValue = credits[i]!=null?credits[i].toString():null;
            vEcriture.getListLigneEcriture().add(this.createLigne(i<2?1:2,debitValue,creditValue));
        }

        BigDecimal debitSum = debits[0].add(debits[1]).add(debits[3]);

        Assert.assertEquals(debitSum,vEcriture.getTotalDebit());
    }

    @Test
    public void getTotalDebit_returnsTotalCredit_ofEcritureComptable(){
        vEcriture = new EcritureComptable();
        BigDecimal[] debits = { new BigDecimal("200.50"), new BigDecimal("100.50"), null, new BigDecimal("40")};
        BigDecimal[] credits = { null, new BigDecimal("33"), new BigDecimal("301"), new BigDecimal("7")};


        vEcriture.setLibelle("Equilibrée");

        String debitValue, creditValue;
        for(int i=0; i<4; i++) {
            debitValue = debits[i]!=null?debits[i].toString():null;
            creditValue = credits[i]!=null?credits[i].toString():null;
            vEcriture.getListLigneEcriture().add(this.createLigne(i<2?1:2,debitValue,creditValue));
        }


        BigDecimal creditSum = credits[1].add(credits[2]).add(credits[3]);


        Assert.assertEquals(creditSum,vEcriture.getTotalCredit());
    }

}
