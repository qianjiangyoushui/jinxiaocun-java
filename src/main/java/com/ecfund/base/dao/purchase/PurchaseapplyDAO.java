package com.ecfund.base.dao.purchase;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.purchase.Purchaseapply;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PurchaseapplyDAO extends BaseDAO<Purchaseapply> {

   public List countMoneyByCategory(){
       return  this.sqlSessionTemplate.selectList(Purchaseapply.class.getSimpleName()+".countMoneyByCategory");
    }
}