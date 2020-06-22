package com.dummy.myerp.model.bean.comptabilite;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;


/**
 * Bean représentant une Écriture Comptable
 */
public class EcritureComptable {
    // erreur regex le code journal n'est pas forcément un chiffre remplacé \d par \w
    private static final String REFERENCE_PATTERN = "\\w{1,5}-\\d{4}/\\d{5}";
    private static final int LIBELLE_MIN = 1;
    private static final int LIBELLE_MAX = 200;
    private static final int LIST_LIGNE_ECRITURE_MIN = 1;

    // ==================== Attributs ====================
    /** The Id. */
    private Integer id;
    /** Journal comptable */
    @NotNull private JournalComptable journal;
    /** The Reference. */
    @Pattern(regexp = REFERENCE_PATTERN)
    private String reference;
    /** The Date. */
    @NotNull private Date date;

    /** The Libelle. */
    @NotNull
    @Size(min = LIBELLE_MIN, max = LIBELLE_MAX)
    private String libelle;

    /** La liste des lignes d'écriture comptable. */
    @Valid
    @Size(min = LIST_LIGNE_ECRITURE_MIN)
    private final List<LigneEcritureComptable> listLigneEcriture = new ArrayList<>();


    // ==================== Getters/Setters ====================
    public Integer getId() {
        return id;
    }
    public void setId(Integer pId) {
        id = pId;
    }
    public JournalComptable getJournal() {
        return journal;
    }
    public void setJournal(JournalComptable pJournal) {
        journal = pJournal;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String pReference) {
        reference = pReference;
    }
    public java.sql.Date getDate() {
        return date;
    }
    public void setDate(java.sql.Date pDate) {
        date = pDate;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String pLibelle) {
        libelle = pLibelle;
    }
    public List<LigneEcritureComptable> getListLigneEcriture() {
        return listLigneEcriture;
    }

    /**
     * Calcul et renvoie le total des montants au débit des lignes d'écriture
     *
     * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au débit
     */
    public BigDecimal getTotalDebit() {
        BigDecimal vRetour = BigDecimal.ZERO;
        for (LigneEcritureComptable vLigneEcritureComptable : listLigneEcriture) {
            if (vLigneEcritureComptable.getDebit() != null) {
                vRetour = vRetour.add(vLigneEcritureComptable.getDebit());
            }
        }
        return vRetour;
    }

    /**
     * Calcul et renvoie le total des montants au crédit des lignes d'écriture
     *
     * @return {@link BigDecimal}, {@link BigDecimal#ZERO} si aucun montant au crédit
     */
    public BigDecimal getTotalCredit() {
        BigDecimal vRetour = BigDecimal.ZERO;
        for (LigneEcritureComptable vLigneEcritureComptable : listLigneEcriture) {
            // replace getDebit() call with getCredit
            if (vLigneEcritureComptable.getCredit() != null) {
                vRetour = vRetour.add(vLigneEcritureComptable.getCredit());
            }
        }
        return vRetour;
    }

    /**
     * Renvoie si l'écriture est équilibrée (TotalDebit = TotalCrédit)
     * @return boolean
     */
    public boolean isEquilibree() {
        // equal compare string and not the value
        // compareTo return 0 if it's the same value
        return this.getTotalDebit().compareTo(getTotalCredit()) == 0;
    }

    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
            .append("id=").append(id)
            .append(vSEP).append("journal=").append(journal)
            .append(vSEP).append("reference='").append(reference).append('\'')
            .append(vSEP).append("date=").append(date)
            .append(vSEP).append("libelle='").append(libelle).append('\'')
            .append(vSEP).append("totalDebit=").append(this.getTotalDebit().toPlainString())
            .append(vSEP).append("totalCredit=").append(this.getTotalCredit().toPlainString())
            .append(vSEP).append("listLigneEcriture=[\n")
            .append(StringUtils.join(listLigneEcriture, "\n")).append("\n]")
            .append("}");
        return vStB.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EcritureComptable)) return false;
        EcritureComptable that = (EcritureComptable) o;
        return getId().equals(that.getId()) &&
              getJournal().equals(that.getJournal()) &&
              getReference().equals(that.getReference()) &&
              getDate().equals(that.getDate()) &&
              Objects.equals(getLibelle(), that.getLibelle()) &&
              Objects.equals(getListLigneEcriture(), that.getListLigneEcriture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJournal(), getReference(), getDate(), getLibelle(), getListLigneEcriture());
    }
}
