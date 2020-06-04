package com.dummy.myerp.model.bean.comptabilite;


import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Bean représentant un Compte Comptable
 */
public class CompteComptable {
    private static final int LIBELLE_MIN = 1;
    private static final int LIBELLE_MAX = 150;

    // ==================== Attributs ====================
    /** The Numero. */
    @NotNull
    private Integer numero;

    /** The Libelle. */
    @NotNull
    @Size(min = LIBELLE_MIN, max = LIBELLE_MAX)
    private String libelle;


    // ==================== Constructeurs ====================
    /**
     * Instantiates a new Compte comptable.
     */
    public CompteComptable() {
    }

    /**
     * Instantiates a new Compte comptable.
     *
     * @param pNumero the numero
     */
    public CompteComptable(Integer pNumero) {
        numero = pNumero;
    }

    /**
     * Instantiates a new Compte comptable.
     *
     * @param pNumero the numero
     * @param pLibelle the libelle
     */
    public CompteComptable(Integer pNumero, String pLibelle) {
        numero = pNumero;
        libelle = pLibelle;
    }


    // ==================== Getters/Setters ====================
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer pNumero) {
        numero = pNumero;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String pLibelle) {
        libelle = pLibelle;
    }


    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
            .append("numero=").append(numero)
            .append(vSEP).append("libelle='").append(libelle).append('\'')
            .append("}");
        return vStB.toString();
    }


    // ==================== Méthodes STATIC ====================
    /**
     * Renvoie le {@link CompteComptable} de numéro {@code pNumero} s'il est présent dans la liste
     *
     * @param pList la liste où chercher le {@link CompteComptable}
     * @param pNumero le numero du {@link CompteComptable} à chercher
     * @return {@link CompteComptable} ou {@code null}
     */
    public static CompteComptable getByNumero(List<? extends CompteComptable> pList, Integer pNumero) {
        CompteComptable vRetour = null;
        if(pList == null || pList.isEmpty() || pNumero == null){
            return vRetour;
        }

        for (CompteComptable vBean : pList) {
            if (vBean != null && Objects.equals(vBean.getNumero(), pNumero)) {
                vRetour = vBean;
                break;
            }
        }
        return vRetour;
    }
}
