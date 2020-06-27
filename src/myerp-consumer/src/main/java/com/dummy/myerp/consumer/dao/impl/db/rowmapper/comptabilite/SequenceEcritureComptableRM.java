package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.dao.impl.db.DBN;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceEcritureComptableRM implements RowMapper<SequenceEcritureComptable> {

    @Override
    public SequenceEcritureComptable mapRow(ResultSet resultSet, int i) throws SQLException {
        SequenceEcritureComptable vBean = new SequenceEcritureComptable();

        vBean.setAnnee(resultSet.getInt(DBN.ANNEE));
        vBean.setDerniereValeur(resultSet.getInt(DBN.DERNIERE_VALEUR));

        return vBean;
    }
}
