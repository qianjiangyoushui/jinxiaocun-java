package com.ecfund.base.dao.publics;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.publics.Accountsuit;
import org.springframework.stereotype.Repository;

@Repository
public class AccountsuitDAO extends BaseDAO<Accountsuit> {

    public String getCurrentSuit() {
        Accountsuit as = new Accountsuit();
        as.setCurrent(2);
        as = sqlSessionTemplate.selectOne(Accountsuit.class.getSimpleName()
                + ".find", as);
        return as.getGuid();
    }
}