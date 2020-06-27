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
    private static String sqlGetListCompteComptable;
    private static String sqlGetListJournalComptable;
    private static String sqlGetListEcritureComptable;
    private static String sqlGetEcritureComptable;
    private static String sqlGetEcritureComptableByRef;
    private static String sqlLoadListLigneEcriture;
    private static String sqlInsertEcritureComptable;
    private static String sqlInsertListLigneEcritureComptable;
    private static String sqlUpdateEcritureComptable;
    private static String sqlDeleteEcritureComptable;
    private static String sqlDeleteListLigneEcritureComptable;
    private static String sqlGetLastSequenceEcritureComptable;
    private static String sqlInsertSequenceEcritureComptable;
    private static String sqlUpdateSequenceEcritureComptable;
    private static String sqlDeleteSequenceEcritureComptable;

    private DataSourcesEnum databaseType;

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
        DataSourcesEnum database = DataSourcesEnum.valueOf(System.getProperty("databaseType"));

        if(database.equals(DataSourcesEnum.MYERP)){
            databaseType = DataSourcesEnum.MYERP;
        } else {
            databaseType = DataSourcesEnum.TEST;
        }
    }

    // ==================== SETTERS ====================
    public static void setSqlGetListCompteComptable(String sqlGetListCompteComptable) {
        ComptabiliteDaoImpl.sqlGetListCompteComptable = sqlGetListCompteComptable;
    }

    public static void setSqlGetListJournalComptable(String sqlGetListJournalComptable) {
        ComptabiliteDaoImpl.sqlGetListJournalComptable = sqlGetListJournalComptable;
    }

    public static void setSqlGetListEcritureComptable(String sqlGetListEcritureComptable) {
        ComptabiliteDaoImpl.sqlGetListEcritureComptable = sqlGetListEcritureComptable;
    }

    public static void setSqlGetEcritureComptable(String sqlGetEcritureComptable) {
        ComptabiliteDaoImpl.sqlGetEcritureComptable = sqlGetEcritureComptable;
    }

    public static void setSqlGetEcritureComptableByRef(String sqlGetEcritureComptableByRef) {
        ComptabiliteDaoImpl.sqlGetEcritureComptableByRef = sqlGetEcritureComptableByRef;
    }

    public static void setSqlLoadListLigneEcriture(String sqlLoadListLigneEcriture) {
        ComptabiliteDaoImpl.sqlLoadListLigneEcriture = sqlLoadListLigneEcriture;
    }

    public static void setSqlInsertEcritureComptable(String sqlInsertEcritureComptable) {
        ComptabiliteDaoImpl.sqlInsertEcritureComptable = sqlInsertEcritureComptable;
    }

    public static void setSqlInsertListLigneEcritureComptable(String sqlInsertListLigneEcritureComptable) {
        ComptabiliteDaoImpl.sqlInsertListLigneEcritureComptable = sqlInsertListLigneEcritureComptable;
    }

    public static void setSqlUpdateEcritureComptable(String sqlUpdateEcritureComptable) {
        ComptabiliteDaoImpl.sqlUpdateEcritureComptable = sqlUpdateEcritureComptable;
    }

    public static void setSqlDeleteEcritureComptable(String sqlDeleteEcritureComptable) {
        ComptabiliteDaoImpl.sqlDeleteEcritureComptable = sqlDeleteEcritureComptable;
    }

    public static void setSqlDeleteListLigneEcritureComptable(String sqlDeleteListLigneEcritureComptable) {
        ComptabiliteDaoImpl.sqlDeleteListLigneEcritureComptable = sqlDeleteListLigneEcritureComptable;
    }

    public static void setSqlGetLastSequenceEcritureComptable(String sqlGetLastSequenceEcritureComptable) {
        ComptabiliteDaoImpl.sqlGetLastSequenceEcritureComptable = sqlGetLastSequenceEcritureComptable;
    }

    public static void setSqlInsertSequenceEcritureComptable(String sqlInsertSequenceEcritureComptable) {
        ComptabiliteDaoImpl.sqlInsertSequenceEcritureComptable = sqlInsertSequenceEcritureComptable;
    }

    public static void setSqlUpdateSequenceEcritureComptable(String sqlUpdateSequenceEcritureComptable) {
        ComptabiliteDaoImpl.sqlUpdateSequenceEcritureComptable = sqlUpdateSequenceEcritureComptable;
    }

    public static void setSqlDeleteSequenceEcritureComptable(String sqlDeleteSequenceEcritureComptable) {
        ComptabiliteDaoImpl.sqlDeleteSequenceEcritureComptable = sqlDeleteSequenceEcritureComptable;
    }

// ==================== Méthodes ====================
    /** SQLgetListCompteComptable */
    @Override
    public List<CompteComptable> getListCompteComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(databaseType));
        CompteComptableRM vRM = new CompteComptableRM();
        return vJdbcTemplate.query(sqlGetListCompteComptable, vRM);
    }


    /** SQLgetListJournalComptable */
    @Override
    public List<JournalComptable> getListJournalComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(databaseType));
        JournalComptableRM vRM = new JournalComptableRM();
        return vJdbcTemplate.query(sqlGetListJournalComptable, vRM);
    }

    // ==================== EcritureComptable - GET ====================

    /** SQLgetListEcritureComptable */
    @Override
    public List<EcritureComptable> getListEcritureComptable() {
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(this.getDataSource(databaseType));
        EcritureComptableRM vRM = new EcritureComptableRM();
        return vJdbcTemplate.query(sqlGetListEcritureComptable, vRM);
    }


    /** SQLgetEcritureComptable */
    @Override
    public EcritureComptable getEcritureComptable(Integer pId) throws NotFoundException {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ID, pId);
        EcritureComptableRM vRM = new EcritureComptableRM();
        EcritureComptable vBean;
        try {
            vBean = vJdbcTemplate.queryForObject(sqlGetEcritureComptable, vSqlParams, vRM);
        } catch (EmptyResultDataAccessException vEx) {
            throw new NotFoundException("EcritureComptable non trouvée : id=" + pId);
        }
        return vBean;
    }


    /** SQLgetEcritureComptableByRef */
    @Override
    public EcritureComptable getEcritureComptableByRef(String pReference) throws NotFoundException {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.REFERENCE, pReference);
        EcritureComptableRM vRM = new EcritureComptableRM();
        EcritureComptable vBean;

        try {
            vBean = vJdbcTemplate.queryForObject(sqlGetEcritureComptableByRef, vSqlParams, vRM);
        } catch (EmptyResultDataAccessException vEx) {
            throw new NotFoundException("EcritureComptable non trouvée : reference=" + pReference);
        }
        return vBean;
    }


    /** SQLloadListLigneEcriture */
    @Override
    public void loadListLigneEcriture(EcritureComptable pEcritureComptable) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ECRITURE_ID, pEcritureComptable.getId());
        LigneEcritureComptableRM vRM = new LigneEcritureComptableRM();
        List<LigneEcritureComptable> vList = vJdbcTemplate.query(sqlLoadListLigneEcriture, vSqlParams, vRM);
        pEcritureComptable.getListLigneEcriture().clear();
        pEcritureComptable.getListLigneEcriture().addAll(vList);
    }


    // ==================== EcritureComptable - INSERT ====================

    /** SQLinsertEcritureComptable */
    @Override
    public void insertEcritureComptable(EcritureComptable pEcritureComptable) {
        // ===== Ecriture Comptable
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.JOURNAL_CODE, pEcritureComptable.getJournal().getCode());
        vSqlParams.addValue(DBN.REFERENCE, pEcritureComptable.getReference());
        vSqlParams.addValue(DBN.DATE, pEcritureComptable.getDate());
        vSqlParams.addValue(DBN.LIBELLE, pEcritureComptable.getLibelle());

        vJdbcTemplate.update(sqlInsertEcritureComptable, vSqlParams);

        Integer vId = this.queryGetSequenceValue(databaseType, "myerp.ecriture_comptable_id_seq", Integer.class);

        pEcritureComptable.setId(vId);

        // ===== Liste des lignes d'écriture
        this.insertListLigneEcritureComptable(pEcritureComptable);
    }

    /**
     * Insert les lignes d'écriture de l'écriture comptable
     *
     * @param pEcritureComptable l'écriture comptable
     */
    protected void insertListLigneEcritureComptable(EcritureComptable pEcritureComptable) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
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

            vJdbcTemplate.update(sqlInsertListLigneEcritureComptable, vSqlParams);
        }
    }


    // ==================== EcritureComptable - UPDATE ====================

    /** SQLupdateEcritureComptable */
    @Override
    public void updateEcritureComptable(EcritureComptable pEcritureComptable) {
        // ===== Ecriture Comptable
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ID, pEcritureComptable.getId());
        vSqlParams.addValue(DBN.JOURNAL_CODE, pEcritureComptable.getJournal().getCode());
        vSqlParams.addValue(DBN.REFERENCE, pEcritureComptable.getReference());
        vSqlParams.addValue(DBN.DATE, pEcritureComptable.getDate(), Types.DATE);
        vSqlParams.addValue(DBN.LIBELLE, pEcritureComptable.getLibelle());

        vJdbcTemplate.update(sqlUpdateEcritureComptable, vSqlParams);

        // ===== Liste des lignes d'écriture
        this.deleteListLigneEcritureComptable(pEcritureComptable.getId());
        this.insertListLigneEcritureComptable(pEcritureComptable);
    }


    // ==================== EcritureComptable - DELETE ====================

    /**
     * Supprime l'écriture de comptable d'id {@code pId}
     *
     * @param pId l'id de l'écriture
     */
    @Override
    public void deleteEcritureComptable(Integer pId) {
        // ===== Suppression des lignes d'écriture
        this.deleteListLigneEcritureComptable(pId);

        // ===== Suppression de l'écriture
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ID, pId);
        vJdbcTemplate.update(sqlDeleteEcritureComptable, vSqlParams);
    }

    /**
     * Supprime les lignes d'écriture de l'écriture comptable d'id {@code pEcritureId}
     *
     * @param pEcritureId id de l'écriture comptable
     */
    @Override
    public void deleteListLigneEcritureComptable(Integer pEcritureId) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();
        vSqlParams.addValue(DBN.ECRITURE_ID, pEcritureId);
        vJdbcTemplate.update(sqlDeleteListLigneEcritureComptable, vSqlParams);
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
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();

        vSqlParams.addValue(DBN.ANNEE, pAnneeJournal);
        vSqlParams.addValue(DBN.JOURNAL_CODE, pCodeJournal);

        try {
            return vJdbcTemplate.queryForObject(sqlGetLastSequenceEcritureComptable, vSqlParams,vRM);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    /**
     * Insert new sequence
     *
     * @param pSequenceEcritureComptable the sequence to insert
     * @param codeJournal of the Journal
     */
    @Override
    public void insertSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable, String codeJournal) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();

        vSqlParams.addValue(DBN.JOURNAL_CODE, codeJournal);
        vSqlParams.addValue(DBN.ANNEE, pSequenceEcritureComptable.getAnnee());
        vSqlParams.addValue(DBN.DERNIERE_VALEUR, pSequenceEcritureComptable.getDerniereValeur());

        vJdbcTemplate.update(sqlInsertSequenceEcritureComptable, vSqlParams);
    }

    /**
     * Update a sequence
     *
     * @param pSequenceEcritureComptable the sequence to update
     * @param codeJournal of the Journal
     */
    @Override
    public void updateSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable, String codeJournal) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();

        vSqlParams.addValue(DBN.JOURNAL_CODE, codeJournal);
        vSqlParams.addValue(DBN.ANNEE, pSequenceEcritureComptable.getAnnee());
        vSqlParams.addValue(DBN.DERNIERE_VALEUR, pSequenceEcritureComptable.getDerniereValeur());

        vJdbcTemplate.update(sqlUpdateSequenceEcritureComptable, vSqlParams);
    }

     /**
     * Delete a sequence
     *
     * @param pSequenceEcritureComptable the sequence to deleted
     * @param codeJournal of the Journal
     */
    @Override
    public void deleteSequenceEcritureComptable(SequenceEcritureComptable pSequenceEcritureComptable, String codeJournal) {
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource(databaseType));
        MapSqlParameterSource vSqlParams = new MapSqlParameterSource();

        vSqlParams.addValue(DBN.JOURNAL_CODE, codeJournal);
        vSqlParams.addValue(DBN.ANNEE, pSequenceEcritureComptable.getAnnee());

        vJdbcTemplate.update(sqlDeleteSequenceEcritureComptable, vSqlParams);
    }

}
