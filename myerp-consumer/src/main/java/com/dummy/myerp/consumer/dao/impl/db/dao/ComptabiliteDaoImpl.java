package com.dummy.myerp.consumer.dao.impl.db.dao;


import com.dummy.myerp.consumer.dao.impl.db.DBN;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.*;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.sql.Types;
import java.util.List;


/**
 * Implémentation de l'interface {@link ComptabiliteDao}
 */
public class ComptabiliteDaoImpl extends AbstractDbConsumer implements ComptabiliteDao {

    // ==================== Constructeurs ====================
    /** Instance unique de la classe (design pattern Singleton) */
    private static final ComptabiliteDaoImpl INSTANCE = new ComptabiliteDaoImpl();

    /**
     * Renvoie l'instance unique de la classe (design pattern Singleton).
     *
     * @return {@link ComptabiliteDaoImpl}
     */
    public static ComptabiliteDaoImpl getInstance() {
        return ComptabiliteDaoImpl.INSTANCE;
    }

    /**
     * Constructeur.
     */
    protected ComptabiliteDaoImpl() {
        super();
    }


    // ==================== Méthodes ====================
    /** SQLgetListCompteComptable */
    private static String SQLgetListCompteComptable;
    public static void setSQLgetListCompteComptable(String pSQLgetListCompteComptable) {
        SQLgetListCompteComptable = pSQLgetListCompteComptable;
    }
    @Override
    public List<CompteComptable> getListCompteComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
        CompteComptableRM vRM = new CompteComptableRM();
        return vJdbcTemplate.query(SQLgetListCompteComptable, vRM);
    }


    /** SQLgetListJournalComptable */
    private static String SQLgetListJournalComptable;
    public static void setSQLgetListJournalComptable(String pSQLgetListJournalComptable) {
        SQLgetListJournalComptable = pSQLgetListJournalComptable;
    }
    @Override
    public List<JournalComptable> getListJournalComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
        JournalComptableRM vRM = new JournalComptableRM();
        return vJdbcTemplate.query(SQLgetListJournalComptable, vRM);
    }

    // ==================== EcritureComptable - GET ====================

    /** SQLgetListEcritureComptable */
    private static String SQLgetListEcritureComptable;
    public static void setSQLgetListEcritureComptable(String pSQLgetListEcritureComptable) {
        SQLgetListEcritureComptable = pSQLgetListEcritureComptable;
    }
    @Override
    public List<EcritureComptable> getListEcritureComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(DataSourcesEnum.MYERP));
        EcritureComptableRM vRM = new EcritureComptableRM();
        return vJdbcTemplate.query(SQLgetListEcritureComptable, vRM);
    }


    /** SQLgetEcritureComptable */
    private static String SQLgetEcritureComptable;
    public static void setSQLgetEcritureComptable(String pSQLgetEcritureComptable) {
        SQLgetEcritureComptable = pSQLgetEcritureComptable;
    }
    @Override
    public EcritureComptable getEcritureComptable(Integer pId) throws NotFoundException {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ID, pId);
        EcritureComptableRM vRM = new EcritureComptableRM();
        EcritureComptable vBean;
        try {
            vBean = vJdbcTemplate.queryForObject(SQLgetEcritureComptable, vSqlParams, vRM);
        } catch (EmptyResultDataAccessException vEx) {
            throw new NotFoundException("EcritureComptable non trouvée : id=" + pId);
        }
        return vBean;
    }


    /** SQLgetEcritureComptableByRef */
    private static String SQLgetEcritureComptableByRef;
    public static void setSQLgetEcritureComptableByRef(String pSQLgetEcritureComptableByRef) {
        SQLgetEcritureComptableByRef = pSQLgetEcritureComptableByRef;
    }
    @Override
    public EcritureComptable getEcritureComptableByRef(String pReference) throws NotFoundException {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.REFERENCE, pReference);
        EcritureComptableRM vRM = new EcritureComptableRM();
        EcritureComptable vBean;
        try {
            vBean = vJdbcTemplate.queryForObject(SQLgetEcritureComptableByRef, vSqlParams, vRM);
        } catch (EmptyResultDataAccessException vEx) {
            throw new NotFoundException("EcritureComptable non trouvée : reference=" + pReference);
        }
        return vBean;
    }


    /** SQLloadListLigneEcriture */
    private static String SQLloadListLigneEcriture;
    public static void setSQLloadListLigneEcriture(String pSQLloadListLigneEcriture) {
        SQLloadListLigneEcriture = pSQLloadListLigneEcriture;
    }
    @Override
    public void loadListLigneEcriture(EcritureComptable pEcritureComptable) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ECRITURE_ID, pEcritureComptable.getId());
        LigneEcritureComptableRM vRM = new LigneEcritureComptableRM();
        List<LigneEcritureComptable> vList = vJdbcTemplate.query(SQLloadListLigneEcriture, vSqlParams, vRM);
        pEcritureComptable.getListLigneEcriture().clear();
        pEcritureComptable.getListLigneEcriture().addAll(vList);
    }


    // ==================== EcritureComptable - INSERT ====================

    /** SQLinsertEcritureComptable */
    private static String SQLinsertEcritureComptable;
    public static void setSQLinsertEcritureComptable(String pSQLinsertEcritureComptable) {
        SQLinsertEcritureComptable = pSQLinsertEcritureComptable;
    }
    @Override
    public void insertEcritureComptable(EcritureComptable pEcritureComptable) {
        // ===== Ecriture Comptable
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.JOURNAL_CODE, pEcritureComptable.getJournal().getCode());
        vSqlParams.addValue(DBN.REFERENCE, pEcritureComptable.getReference());
        vSqlParams.addValue(DBN.DATE, pEcritureComptable.getDate(), Types.DATE);
        vSqlParams.addValue(DBN.LIBELLE, pEcritureComptable.getLibelle());

        vJdbcTemplate.update(SQLinsertEcritureComptable, vSqlParams);

        // ----- Récupération de l'id
        Integer vId = this.queryGetSequenceValuePostgreSQL(DataSourcesEnum.MYERP, "myerp.ecriture_comptable_id_seq",
                                                           Integer.class);
        pEcritureComptable.setId(vId);

        // ===== Liste des lignes d'écriture
        this.insertListLigneEcritureComptable(pEcritureComptable);
    }

    /** SQLinsertListLigneEcritureComptable */
    private static String SQLinsertListLigneEcritureComptable;
    public static void setSQLinsertListLigneEcritureComptable(String pSQLinsertListLigneEcritureComptable) {
        SQLinsertListLigneEcritureComptable = pSQLinsertListLigneEcritureComptable;
    }
    /**
     * Insert les lignes d'écriture de l'écriture comptable
     * @param pEcritureComptable l'écriture comptable
     */
    protected void insertListLigneEcritureComptable(EcritureComptable pEcritureComptable) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ECRITURE_ID, pEcritureComptable.getId());

        int vLigneId = 0;
        for (LigneEcritureComptable vLigne : pEcritureComptable.getListLigneEcriture()) {
            vLigneId++;
            vSqlParams.addValue(DBN.LIGNE_ID, vLigneId);
            vSqlParams.addValue(DBN.COMPTE_COMPTABLE_NUMERO, vLigne.getCompteComptable().getNumero());
            vSqlParams.addValue(DBN.LIBELLE, vLigne.getLibelle());
            vSqlParams.addValue(DBN.DEBIT, vLigne.getDebit());

            vSqlParams.addValue(DBN.CREDIT, vLigne.getCredit());

            vJdbcTemplate.update(SQLinsertListLigneEcritureComptable, vSqlParams);
        }
    }


    // ==================== EcritureComptable - UPDATE ====================

    /** SQLupdateEcritureComptable */
    private static String SQLupdateEcritureComptable;
    public static void setSQLupdateEcritureComptable(String pSQLupdateEcritureComptable) {
        SQLupdateEcritureComptable = pSQLupdateEcritureComptable;
    }
    @Override
    public void updateEcritureComptable(EcritureComptable pEcritureComptable) {
        // ===== Ecriture Comptable
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ID, pEcritureComptable.getId());
        vSqlParams.addValue(DBN.JOURNAL_CODE, pEcritureComptable.getJournal().getCode());
        vSqlParams.addValue(DBN.REFERENCE, pEcritureComptable.getReference());
        vSqlParams.addValue(DBN.DATE, pEcritureComptable.getDate(), Types.DATE);
        vSqlParams.addValue(DBN.LIBELLE, pEcritureComptable.getLibelle());

        vJdbcTemplate.update(SQLupdateEcritureComptable, vSqlParams);

        // ===== Liste des lignes d'écriture
        this.deleteListLigneEcritureComptable(pEcritureComptable.getId());
        this.insertListLigneEcritureComptable(pEcritureComptable);
    }


    // ==================== EcritureComptable - DELETE ====================

    /** SQLdeleteEcritureComptable */
    private static String SQLdeleteEcritureComptable;
    public static void setSQLdeleteEcritureComptable(String pSQLdeleteEcritureComptable) {
        SQLdeleteEcritureComptable = pSQLdeleteEcritureComptable;
    }
    @Override
    public void deleteEcritureComptable(Integer pId) {
        // ===== Suppression des lignes d'écriture
        this.deleteListLigneEcritureComptable(pId);

        // ===== Suppression de l'écriture
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ID, pId);
        vJdbcTemplate.update(SQLdeleteEcritureComptable, vSqlParams);
    }

    /** SQLdeleteListLigneEcritureComptable */
    private static String SQLdeleteListLigneEcritureComptable;
    public static void setSQLdeleteListLigneEcritureComptable(String pSQLdeleteListLigneEcritureComptable) {
        SQLdeleteListLigneEcritureComptable = pSQLdeleteListLigneEcritureComptable;
    }

    /**
     * Supprime les lignes d'écriture de l'écriture comptable d'id {@code pEcritureId}
     * @param pEcritureId id de l'écriture comptable
     */
    protected void deleteListLigneEcritureComptable(Integer pEcritureId) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ECRITURE_ID, pEcritureId);
        vJdbcTemplate.update(SQLdeleteListLigneEcritureComptable, vSqlParams);
    }

    /** SQLgetLastSeqOfTheYear */
    private static String SQLgetLastSequenceEcritureComptable;
    public void setSQLgetLastSequenceEcritureComptable(String pSQLgetLastSequenceEcritureComptable)
    {
        SQLgetLastSequenceEcritureComptable = pSQLgetLastSequenceEcritureComptable;
    }

    /**
     * Get the last sequence in the Journal Comptable
     *
     * @param pAnneeJournal of the sequence
     * @param pCodeJournal of the Journal
     * @return the last sequence found
     */
    @Override
    public SequenceEcritureComptable getLastSeqOfTheYear(Integer pAnneeJournal, String pCodeJournal) {
        SequenceEcritureComptableRM vRM = new SequenceEcritureComptableRM();
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();

        vSqlParams.addValue(DBN.ANNEE, pAnneeJournal);
        vSqlParams.addValue(DBN.JOURNAL_CODE, pCodeJournal);

        try {
            return vJdbcTemplate.queryForObject(SQLgetLastSequenceEcritureComptable, vSqlParams,vRM);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    private static String SQLinsertSequenceEcritureComptable;
    public void setSQLinsertSequenceEcritureComptable(String pSQLinsertSequenceEcritureComptable)
    {
        SQLinsertSequenceEcritureComptable = pSQLinsertSequenceEcritureComptable;
    }

    /**
     * Insert new sequence
     *
     * @param pSequenceEcritureComptable the sequence to insert
     * @param codeJournal of the Journal
     */
    @Override
    public void insertSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable, String codeJournal) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();

        vSqlParams.addValue(DBN.JOURNAL_CODE, codeJournal);
        vSqlParams.addValue(DBN.ANNEE, pSequenceEcritureComptable.getAnnee());
        vSqlParams.addValue(DBN.DERNIERE_VALEUR, pSequenceEcritureComptable.getDerniereValeur());

        vJdbcTemplate.update(SQLinsertSequenceEcritureComptable, vSqlParams);
    }

    private static String SQLupdateSequenceEcritureComptable;
    public void setSQLupdateSequenceEcritureComptable(String pSQLupdateSequenceEcritureCOmptable)
    {
        SQLupdateSequenceEcritureComptable = pSQLupdateSequenceEcritureCOmptable;
    }

    /**
     * Update a sequence
     *
     * @param pSequenceEcritureComptable the sequence to update
     * @param codeJournal of the Journal
     */
    @Override
    public void updateSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable, String codeJournal) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();

        vSqlParams.addValue(DBN.JOURNAL_CODE, codeJournal);
        vSqlParams.addValue(DBN.ANNEE, pSequenceEcritureComptable.getAnnee());
        vSqlParams.addValue(DBN.DERNIERE_VALEUR, pSequenceEcritureComptable.getDerniereValeur());

        vJdbcTemplate.update(SQLupdateSequenceEcritureComptable, vSqlParams);
    }

    private static String SQLdeleteSequenceEcritureComptable;
    public void setSQLdeleteSequenceEcritureComptable(String pSQLdeleteSequenceEcritureCOmptable)
    {
        SQLdeleteSequenceEcritureComptable = pSQLdeleteSequenceEcritureCOmptable;
    }

    /**
     * Delete a sequence
     *
     * @param pSequenceEcritureComptable the sequence to deleted
     * @param codeJournal of the Journal
     */
    @Override
    public void deleteSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable, String codeJournal) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();

        vSqlParams.addValue(DBN.JOURNAL_CODE, codeJournal);
        vSqlParams.addValue(DBN.ANNEE, pSequenceEcritureComptable.getAnnee());
        //vSqlParams.addValue(DBN.DERNIERE_VALEUR, pSequenceEcritureComptable.getDerniereValeur());

        vJdbcTemplate.update(SQLdeleteSequenceEcritureComptable, vSqlParams);
    }

}
