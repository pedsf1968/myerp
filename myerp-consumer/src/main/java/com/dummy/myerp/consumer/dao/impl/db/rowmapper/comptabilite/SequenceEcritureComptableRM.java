package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceEcritureComptableRM implements RowMapper<SequenceEcritureComptable> {
    private final static String PARAM_ANNEE = "annee";
    private final static String PARAM_DERNIERE_VALEUR = "derniere_valeur";

    @Override
    public SequenceEcritureComptable mapRow(ResultSet resultSet, int i) throws SQLException {
        SequenceEcritureComptable vSequenceEcritureComptable = new SequenceEcritureComptable();
        vSequenceEcritureComptable.setAnnee(resultSet.getInt(PARAM_ANNEE));
        vSequenceEcritureComptable.setDerniereValeur(resultSet.getInt(PARAM_DERNIERE_VALEUR));
        return vSequenceEcritureComptable;
    }
}
