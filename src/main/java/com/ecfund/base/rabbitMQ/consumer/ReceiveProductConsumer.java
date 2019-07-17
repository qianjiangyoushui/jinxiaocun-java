package com.ecfund.base.rabbitMQ.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.purchase.Bilingdetail;
import com.ecfund.base.model.purchase.Purchasebiling;
import com.ecfund.base.model.storage.Receiveproduct;
import com.ecfund.base.model.storage.Receiveproductdetail;
import com.ecfund.base.service.purchase.PurchasebilingService;
import com.ecfund.base.service.storage.ReceiveproductService;
import com.ecfund.base.util.common.OrderCodeFactory;
import com.ecfund.base.util.dingtalk.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReceiveProductConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(ReceiveProductConsumer.class);

    @Autowired
    private PurchasebilingService purchasebilingService;
    @Autowired
    private ReceiveproductService receiveproductService;
    @Override
    public void onMessage(Message message) {
        logger.info("shijj receive message------->:{}", message);
        String resultStr = MessageUtil.getBodyContentAsString(message);
        JSONObject result = JSONObject.parseObject(resultStr);
        String processId = result.getString("content");
        /**
         * 根据采购开单构建一个收货单
         */
        Purchasebiling purchasebiling = purchasebilingService.viewByProcessId(processId);
        Receiveproduct receiveproduct = buildReceiveProduct(purchasebiling);
        try {
            receiveproductService.insertAll(receiveproduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Receiveproduct buildReceiveProduct(Purchasebiling purchasebiling) {
        Receiveproduct receiveproduct = new Receiveproduct();
        //receiveproduct.setAddress(purchasebiling.get);
        receiveproduct.setAmount(purchasebiling.getSummount());
        receiveproduct.setCode(OrderCodeFactory.getReceiveProductCode(0L));
        receiveproduct.setCompanyid(purchasebiling.getCompnayid());
        receiveproduct.setSuitid(purchasebiling.getSuitid());
        receiveproduct.setCreatedate(Calendar.getInstance().getTime());
        receiveproduct.setPurchasecode(purchasebiling.getApplycode());
        receiveproduct.setPurchaseid(purchasebiling.getGuid());
        receiveproduct.setBusiuserid(purchasebiling.getUserid());
        receiveproduct.setBusiusername(purchasebiling.getUsername());
        //receiveproduct.setPhone(purchasebiling.get);
        receiveproduct.setSummoney(purchasebiling.getSummoney());
        receiveproduct.setSenddate(purchasebiling.getApplydate());
        receiveproduct.setStatus(1);
        receiveproduct.setSupplyid(purchasebiling.getSupplyid());
        receiveproduct.setSupplyname(purchasebiling.getSupplyname());
        List<Receiveproductdetail> list = new ArrayList<Receiveproductdetail>();
        for (Bilingdetail bilingdetail:purchasebiling.getDetailList()) {
            Receiveproductdetail receiveproductdetail = new Receiveproductdetail();
            receiveproductdetail.setAmount(bilingdetail.getAmount());
            receiveproductdetail.setImgurl(bilingdetail.getImgurl());
            receiveproductdetail.setNormal(bilingdetail.getNormal());
            receiveproductdetail.setPrice(bilingdetail.getPrice());
            receiveproductdetail.setProductid(bilingdetail.getProductid());
            receiveproductdetail.setProductname(bilingdetail.getProductname());
            receiveproductdetail.setSummoney(bilingdetail.getSummoney());
            receiveproductdetail.setUnit(bilingdetail.getUnit());
            receiveproductdetail.setCreatedate(Calendar.getInstance().getTime());
            list.add(receiveproductdetail);
        }
        receiveproduct.setDetailList(list);
        return receiveproduct;
    }

}
