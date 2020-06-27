package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dummy.myerp.consumer.dao.impl.db.DBN;
import org.springframework.jdbc.core.RowMapper;
import com.dummy.myerp.consumer.dao.impl.cache.CompteComptableDaoCache;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;


/**
 * {@link RowMapper} de {@link LigneEcritureComptable}
 */
public class LigneEcritureComptableRM implements RowMapper<LigneEcritureComptable> {

    /** CompteComptableDaoCache */
    private final CompteComptableDaoCache compteComptableDaoCache = new CompteComptableDaoCache();


    @Override
    public LigneEcritureComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
        LigneEcritureComptable vBean = new LigneEcritureComptable();
        vBean.setCompteComptable(compteComptableDaoCache.getByNumero(pRS.getObject(DBN.COMPTE_COMPTABLE_NUMERO,
                                                                                   Integer.class)));
        vBean.setCredit(pRS.getBigDecimal(DBN.CREDIT));
        vBean.setDebit(pRS.getBigDecimal(DBN.DEBIT));
        vBean.setLibelle(pRS.getString(DBN.LIBELLE));

        return vBean;
    }
}
