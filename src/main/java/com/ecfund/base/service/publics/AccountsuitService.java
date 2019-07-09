package com.ecfund.base.service.publics;


import com.ecfund.base.dao.publics.AccountsuitDAO;
import com.ecfund.base.model.publics.Accountsuit;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsuitService extends BaseService<Accountsuit> {

    @Autowired
    private AccountsuitDAO accountsuitDAO;

    @Autowired
    public void setBaseDAO(AccountsuitDAO accountsuitDAO) {
        super.setBaseDAO(accountsuitDAO);
    }

}