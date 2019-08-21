package com.ecfund.base.service.publics;

import com.ecfund.base.dao.publics.DingrmDAO;
import com.ecfund.base.dao.report.ReportDAO;
import com.ecfund.base.model.publics.Dingrm;
import com.ecfund.base.model.report.ReportDO;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * jiaxd-create
 */

@Service
public class ReportService extends BaseService<ReportDO> {

    @Autowired
    private ReportDAO reportDAO;

    @Autowired
    public void setBaseDAO(ReportDAO reportDAO) {
        super.setBaseDAO(reportDAO);
    }
    /**
     * 按类别查询申请金额
     * @return
     */
    public List purchaseApplyCount(){
        return reportDAO.countMoneyByCategory();
    }
    public List purchaseBilingCount(){
        return reportDAO.purchaseBilingMoney();
    }
    public List stockInMoney(){
        return reportDAO.stockInMoney();
    }
    public List stockOutMoney(){
        return reportDAO.stockOutMoney();
    }
     public List stockInAmount(){
        return reportDAO.stockInAmount();
    }
    public List stockOutAmount(){
        return reportDAO.stockOutAmount();
    }
    public List stockAmount(){
        return reportDAO.stockAmount();
    }
    public List stockMoney(){
        return reportDAO.stockMoney();
    }
}