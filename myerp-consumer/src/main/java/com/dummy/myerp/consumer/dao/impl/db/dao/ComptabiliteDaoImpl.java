package com.dummy.myerp.consumer.dao.impl.db.dao;

import java.sql.Types;
import java.util.List;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.*;
import com.dummy.myerp.model.bean.comptabilite.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.technical.exception.NotFoundException;


/**
 * Implémentation de l'interface {@link ComptabiliteDao}
 */
public class ComptabiliteDaoImpl extends AbstractDbConsumer implements ComptabiliteDao {
    private static final String PARAM_ANNEE = "annee";
    private static final String PARAM_COMPTE_COMPTABLE_NUMERO = "compte_comptable_numero";
    private static final String PARAM_CREDIT = "credit";
    private static final String PARAM_DATE = "date";
    private static final String PARAM_DEBIT = "debit";
    private static final String PARAM_ECRITURE_ID = "ecriture_id";
    private static final String PARAM_ID = "id";
    private static final String PARAM_JOURNAL_CODE = "journal_code";
    private static final String PARAM_LIBELLE = "libelle";
    private static final String PARAM_LIGNE_ID = "ligne_id";
    private static final String PARAM_REFERENCE = "reference";

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
        vSqlParams.addValue(PARAM_ID, pId);
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
        vSqlParams.addValue(PARAM_REFERENCE, pReference);
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
        vSqlParams.addValue(PARAM_ECRITURE_ID, pEcritureComptable.getId());
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
        vSqlParams.addValue(PARAM_JOURNAL_CODE, pEcritureComptable.getJournal().getCode());
        vSqlParams.addValue(PARAM_REFERENCE, pEcritureComptable.getReference());
        vSqlParams.addValue(PARAM_DATE, pEcritureComptable.getDate(), Types.DATE);
        vSqlParams.addValue(PARAM_LIBELLE, pEcritureComptable.getLibelle());

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
        vSqlParams.addValue(PARAM_ECRITURE_ID, pEcritureComptable.getId());

        int vLigneId = 0;
        for (LigneEcritureComptable vLigne : pEcritureComptable.getListLigneEcriture()) {
            vLigneId++;
            vSqlParams.addValue(PARAM_LIGNE_ID, vLigneId);
            vSqlParams.addValue(PARAM_COMPTE_COMPTABLE_NUMERO, vLigne.getCompteComptable().getNumero());
            vSqlParams.addValue(PARAM_LIBELLE, vLigne.getLibelle());
            vSqlParams.addValue(PARAM_DEBIT, vLigne.getDebit());

            vSqlParams.addValue(PARAM_CREDIT, vLigne.getCredit());

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
        vSqlParams.addValue(PARAM_ID, pEcritureComptable.getId());
        vSqlParams.addValue(PARAM_JOURNAL_CODE, pEcritureComptable.getJournal().getCode());
        vSqlParams.addValue(PARAM_REFERENCE, pEcritureComptable.getReference());
        vSqlParams.addValue(PARAM_DATE, pEcritureComptable.getDate(), Types.DATE);
        vSqlParams.addValue(PARAM_LIBELLE, pEcritureComptable.getLibelle());

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
        vSqlParams.addValue(PARAM_ID, pId);
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
        vSqlParams.addValue(PARAM_ECRITURE_ID, pEcritureId);
        vJdbcTemplate.update(SQLdeleteListLigneEcritureComptable, vSqlParams);
    }

    /** SQLgetLastSeqOfTheYear */
    private static String SQLgetLastSeqOfTheYear;
    public static void setSQLgetLastSeqOfTheYear(String pSQLgetLastSeqOfTheYear) {
        SQLgetLastSeqOfTheYear = pSQLgetLastSeqOfTheYear;
    }

    /**
     * Get the last sequence in the Journal Comptable
     *
     * @param pAnneeJournal of the sequence
     * @param pCodeJournal of the Journal
     * @return the last sequence found
     */
    @Override
    public SequenceEcritureComptable getLastSeqOfTheYear(String pAnneeJournal, String pCodeJournal) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(DataSourcesEnum.MYERP));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(PARAM_ANNEE, pAnneeJournal);
        vSqlParams.addValue(PARAM_JOURNAL_CODE, pCodeJournal);

        SequenceEcritureComptableRM vRM = new SequenceEcritureComptableRM();

        try {
            return vJdbcTemplate.queryForObject(SQLgetLastSeqOfTheYear, vSqlParams,vRM);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }
}
