package com.ecfund.base.dao.report;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.purchase.Purchaseapply;
import com.ecfund.base.model.report.ReportDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDAO extends BaseDAO<ReportDO> {

   public List countMoneyByCategory(){
       return  this.sqlSessionTemplate.selectList("Report.purchase-apply-money");
    }
   public List purchaseBilingMoney(){
       return  this.sqlSessionTemplate.selectList("Report.purchase-biling-money");
    }
   public List stockInMoney(){
       return  this.sqlSessionTemplate.selectList("Report.stock-in-money");
    }
   public List stockOutMoney(){
       return  this.sqlSessionTemplate.selectList("Report.stock-out-money");
    }
   public List stockInAmount(){
       return  this.sqlSessionTemplate.selectList("Report.stock-in-amount");
    }
   public List stockOutAmount(){
       return  this.sqlSessionTemplate.selectList("Report.stock-out-amount");
    }
    public List stockAmount(){
       return  this.sqlSessionTemplate.selectList("Report.stock-amount");
    }
    public List stockMoney(){
       return  this.sqlSessionTemplate.selectList("Report.stock-money");
    }
}