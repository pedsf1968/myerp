package com.dummy.myerp.consumer.db.helper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe utilitaire travaillant sur les ResultSet
 */
public abstract class ResultSetHelper {
    private static final String ERROR_MESSAGE = "ResulSet musts be not null";

    // ==================== Constructeurs ====================
    /**
     * Constructeur.
     */
    protected ResultSetHelper() {
        super();
    }


    // ==================== Méthodes ====================
    /**
     * Renvoie la valeur de la colonne pColName dans un <code>Integer</code>.
     * Si la colonne vaut <code>null</code>, la méthode renvoie <code>null</code>
     *
     * @param pRS : Le ResultSet à intéroger
     * @param pColName : Le nom de la colonne dans le retour de la requête SQL
     * @return <code>Integer</code> ou <code>null</code>
     * @throws SQLException sur erreur SQL
     */
    public static Integer getInteger(ResultSet pRS, String pColName) throws SQLException {
        Integer vRetour = null;

        if (pRS==null || pColName==null) {
            throw new SQLException(ERROR_MESSAGE);
        }

        if (!pRS.wasNull()) {
            vRetour = pRS.getInt(pColName);
        }

        return vRetour;
    }

    /**
     * Renvoie la valeur de la colonne pColName dans un <code>Long</code>.
     * Si la colonne vaut <code>null</code>, la méthode renvoie <code>null</code>
     *
     * @param pRS : Le ResultSet à intéroger
     * @param pColName : Le nom de la colonne dans le retour de la requête SQL
     * @return <code>Long</code> ou <code>null</code>
     * @throws SQLException sur erreur SQL
     */
    public static Long getLong(ResultSet pRS, String pColName) throws SQLException {
        Long vRetour = null;

        if (pRS==null || pColName==null) {
            throw new SQLException(ERROR_MESSAGE);
        }

        if (!pRS.wasNull()) {
            vRetour = pRS.getLong(pColName);
        }
        return vRetour;
    }


    /**
     * Renvoie la valeur de la colonne pColName dans un {@link Date} en faisant un truncate de l'heure.
     * Si la colonne vaut <code>null</code>, la méthode renvoie <code>null</code>.
     *
     * @param pRS : Le ResultSet à intéroger
     * @param pColName : Le nom de la colonne dans le retour de la requête SQL
     * @return {@link Date} ou <code>null</code>
     * @throws SQLException sur erreur SQL
     */
    public static Date getDate(ResultSet pRS, String pColName) throws SQLException {
       if (pRS==null || pColName==null) {
            throw new SQLException(ERROR_MESSAGE);
        }

        return pRS.getDate(pColName);
    }
}
