package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dummy.myerp.consumer.dao.impl.db.DBN;
import org.springframework.jdbc.core.RowMapper;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;


/**
 * {@link RowMapper} de {@link JournalComptable}
 */
public class JournalComptableRM implements RowMapper<JournalComptable> {

    @Override
    public JournalComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
        JournalComptable vBean = new JournalComptable();
        vBean.setCode(pRS.getString(DBN.CODE));
        vBean.setLibelle(pRS.getString(DBN.LIBELLE));

        return vBean;
    }
}
