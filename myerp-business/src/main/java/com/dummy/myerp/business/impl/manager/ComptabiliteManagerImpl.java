package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.business.impl.AbstractBusinessManager;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;


/**
 * Comptabilite manager implementation.
 */
public class ComptabiliteManagerImpl extends AbstractBusinessManager implements ComptabiliteManager {
    private Logger logger = LoggerFactory.getLogger(ComptabiliteManagerImpl.class);

    // ==================== Attributs ====================


    // ==================== Constructeurs ====================
    /**
     * Instantiates a new Comptabilite manager.
     */
    public ComptabiliteManagerImpl() {
    }


    // ==================== Getters/Setters ====================
    @Override
    public List<CompteComptable> getListCompteComptable() {
        return getDaoProxy().getComptabiliteDao().getListCompteComptable();
    }


    @Override
    public List<JournalComptable> getListJournalComptable() {
        return getDaoProxy().getComptabiliteDao().getListJournalComptable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EcritureComptable> getListEcritureComptable() {
        return getDaoProxy().getComptabiliteDao().getListEcritureComptable();
    }

    /**
     * {@inheritDoc}
     */
    // TODO à tester
    @Override
    public synchronized void addReference(EcritureComptable pEcritureComptable) {
        // TODO à implémenter
        // Bien se réferer à la JavaDoc de cette méthode !
        /* Le principe :
                1.  Remonter depuis la persitance la dernière valeur de la séquence du journal pour l'année de l'écriture
                    (table sequence_ecriture_comptable)
                2.  * S'il n'y a aucun enregistrement pour le journal pour l'année concernée :
                        1. Utiliser le numéro 1.
                    * Sinon :
                        1. Utiliser la dernière valeur + 1
                3.  Mettre à jour la référence de l'écriture avec la référence calculée (RG_Compta_5)
                4.  Enregistrer (insert/update) la valeur de la séquence en persitance
                    (table sequence_ecriture_comptable)
         */

        List<EcritureComptable> ecritureComptables = getDaoProxy().getComptabiliteDao().getListEcritureComptable();
        int size = ecritureComptables.size();
        EcritureComptable ecritureComptable = ecritureComptables.get(size);
        logger.info(ecritureComptable.toString());

    }

    /**
     * {@inheritDoc}
     */
    // TODO à tester
    @Override
    public void checkEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
        this.checkEcritureComptableUnit(pEcritureComptable);
        this.checkEcritureComptableRG2(pEcritureComptable);
        this.checkEcritureComptableRG3(pEcritureComptable);
        this.checkEcritureComptableRG5(pEcritureComptable);
        this.checkEcritureComptableRG6(pEcritureComptable);
    }


    /**
     * Vérifie que l'Ecriture comptable respecte les règles de gestion unitaires,
     * c'est à dire indépendemment du contexte (unicité de la référence, exercie comptable non cloturé...)
     *
     * @param pEcritureComptable -
     * @throws FunctionalException Si l'Ecriture comptable ne respecte pas les règles de gestion
     */
    // TODO tests à compléter
    protected void checkEcritureComptableUnit(EcritureComptable pEcritureComptable) throws FunctionalException {
        // ===== Vérification des contraintes unitaires sur les attributs de l'écriture
        Set<ConstraintViolation<EcritureComptable>> vViolations = null;

        try {
            logger.info("before getConstraintValidator");
            vViolations = getConstraintValidator().validate(pEcritureComptable);
        } catch (ValidationException ex) {
            logger.error(ex.toString());
            logger.info("vViolations : {} ",  vViolations);
            throw new FunctionalException("Validation exception");
        }

        if (!vViolations.isEmpty()) {
            throw new FunctionalException("L'écriture comptable ne respecte pas les règles de gestion.",
                                          new ConstraintViolationException(
                                              "L'écriture comptable ne respecte pas les contraintes de validation",
                                              vViolations));
        }

    }


    /**
     * checkEcritureComptableRG2 : Pour qu'une écriture comptable soit valide,
     * elle doit être équilibrée : la somme des montants au crédit des lignes d'écriture
     * doit être égale à la somme des montants au débit.
     *
     * @param pEcritureComptable à vérifier
     * @throws FunctionalException
     */
    protected void checkEcritureComptableRG2(EcritureComptable pEcritureComptable) throws FunctionalException {
        // ===== RG_Compta_2 : Pour qu'une écriture comptable soit valide, elle doit être équilibrée
        if (!pEcritureComptable.isEquilibree()) {
            throw new FunctionalException("L'écriture comptable n'est pas équilibrée.");
        }

    }

    /**
     * checkEcritureComptableRG3 : Une écriture comptable doit contenir au moins deux lignes d'écriture
     *  une au débit et une au crédit.
     *
     * @param pEcritureComptable à vérifier
     * @throws FunctionalException
     */
    protected void checkEcritureComptableRG3(EcritureComptable pEcritureComptable) throws FunctionalException {
        // ===== RG_Compta_3 : une écriture comptable doit avoir au moins 2 lignes d'écriture (1 au débit, 1 au crédit)
        int vNbrCredit = 0;
        int vNbrDebit = 0;
        for (LigneEcritureComptable vLigneEcritureComptable : pEcritureComptable.getListLigneEcriture()) {
            if (BigDecimal.ZERO.compareTo(ObjectUtils.defaultIfNull(vLigneEcritureComptable.getCredit(),
                  BigDecimal.ZERO)) != 0) {
                vNbrCredit++;
            }
            if (BigDecimal.ZERO.compareTo(ObjectUtils.defaultIfNull(vLigneEcritureComptable.getDebit(),
                  BigDecimal.ZERO)) != 0) {
                vNbrDebit++;
            }
        }
        // On test le nombre de lignes car si l'écriture à une seule ligne
        //      avec un montant au débit et un montant au crédit ce n'est pas valable
        if (pEcritureComptable.getListLigneEcriture().size() < 2
              || vNbrCredit < 1
              || vNbrDebit < 1) {
            throw new FunctionalException(
                  "L'écriture comptable doit avoir au moins deux lignes : une ligne au débit et une ligne au crédit.");
        }
    }

    /**
     * checkEcritureComptableRG5 : La référence d'une écriture comptable est composée du code du journal dans lequel
     * figure l'écriture suivi de l'année et d'un numéro de séquence (propre à chaque journal) sur 5 chiffres
     * incrémenté automatiquement à chaque écriture. Le formatage de la référence est : XX-AAAA/#####.
     * Ex : Journal de banque (BQ), écriture au 31/12/2016
     * --> BQ-2016/00001
     *
     * @param pEcritureComptable à vérifier
     * @throws FunctionalException
     */
    protected void checkEcritureComptableRG5(EcritureComptable pEcritureComptable) throws FunctionalException {
        // ===== RG_Compta_5 : Format et contenu de la référence
        // vérifier que l'année dans la référence correspond bien à la date de l'écriture, idem pour le code journal...
        if (pEcritureComptable.getJournal()!=null && pEcritureComptable.getReference()!=null) {
            String code = pEcritureComptable.getReference().split("-")[0];

            if (!code.equals(pEcritureComptable.getJournal().getCode())) {
                logger.error("Pas le même Code de Journal Comptable : Code {} et Code dans Reference {}",pEcritureComptable.getJournal().getCode(),code);
                throw new FunctionalException(
                      "La référence de l'écriture comptable doit correspondre au journal.");
            }

            String year = pEcritureComptable.getReference().split("-|/")[1];
            String dateYear = new SimpleDateFormat ("yyyy").format(pEcritureComptable.getDate());

            if (!year.equals(dateYear)) {
                logger.error("Pas le même Date de Journal Comptable : Date {} et Année dans Reference {}",dateYear,year);
                throw new FunctionalException(
                      "La référence de l'écriture comptable doit correspondre au journal.");
            }
        }
    }


    /**
     * Vérifie que l'Ecriture comptable respecte les règles de gestion liées au contexte
     * (unicité de la référence, année comptable non cloturé...)
     *
     * @param pEcritureComptable -
     * @throws FunctionalException Si l'Ecriture comptable ne respecte pas les règles de gestion
     */
    protected void checkEcritureComptableRG6(EcritureComptable pEcritureComptable) throws FunctionalException {
        // ===== RG_Compta_6 : La référence d'une écriture comptable doit être unique
        if (StringUtils.isNoneEmpty(pEcritureComptable.getReference())) {
            try {
                // Recherche d'une écriture ayant la même référence
                EcritureComptable vECRef = getDaoProxy().getComptabiliteDao().getEcritureComptableByRef(
                    pEcritureComptable.getReference());

                // Si l'écriture à vérifier est une nouvelle écriture (id == null),
                // ou si elle ne correspond pas à l'écriture trouvée (id != idECRef),
                // c'est qu'il y a déjà une autre écriture avec la même référence
                if (pEcritureComptable.getId() == null
                    || !pEcritureComptable.getId().equals(vECRef.getId())) {
                    throw new FunctionalException("Une autre écriture comptable existe déjà avec la même référence.");
                }
            } catch (NotFoundException vEx) {
                // Dans ce cas, c'est bon, ça veut dire qu'on n'a aucune autre écriture avec la même référence.
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
        this.checkEcritureComptable(pEcritureComptable);
        TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
        try {
            getDaoProxy().getComptabiliteDao().insertEcritureComptable(pEcritureComptable);
            getTransactionManager().commitMyERP(vTS);
            vTS = null;
        } finally {
            getTransactionManager().rollbackMyERP(vTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEcritureComptable(EcritureComptable pEcritureComptable) throws FunctionalException {
        TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
        try {
            getDaoProxy().getComptabiliteDao().updateEcritureComptable(pEcritureComptable);
            getTransactionManager().commitMyERP(vTS);
            vTS = null;
        } finally {
            getTransactionManager().rollbackMyERP(vTS);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEcritureComptable(Integer pId) {
        TransactionStatus vTS = getTransactionManager().beginTransactionMyERP();
        try {
            getDaoProxy().getComptabiliteDao().deleteEcritureComptable(pId);
            getTransactionManager().commitMyERP(vTS);
            vTS = null;
        } finally {
            getTransactionManager().rollbackMyERP(vTS);
        }
    }
}
