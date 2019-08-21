package com.ecfund.base.rabbitMQ.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.funds.Receivebill;
import com.ecfund.base.model.sale.Preorder;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.service.funds.ReceivebillService;
import com.ecfund.base.service.sale.PreorderService;
import com.ecfund.base.service.sale.SaleorderService;
import com.ecfund.base.util.common.OrderCodeFactory;
import com.ecfund.base.util.dingtalk.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Calendar;

public class ReceiveConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(ReceiveConsumer.class);

    @Autowired
    private SaleorderService saleorderService;
    @Autowired
    private ReceivebillService receivebillService;
    @Override
    public void onMessage(Message message) {
        logger.info("shijj receive message------->:{}", message);
        String resultStr = MessageUtil.getBodyContentAsString(message);
        JSONObject result = JSONObject.parseObject(resultStr);
        String processId = result.getString("content");
        Saleorder saleorder = saleorderService.viewByProcessId(processId);
        if(saleorder.getPaymoney().compareTo(BigDecimal.ZERO)!=1){
            return;
        }
        Receivebill receivebill = buildReceiveBill(saleorder);
        try {
            receivebillService.insert(receivebill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Receivebill buildReceiveBill(Saleorder preorder) {
        Receivebill receivebill = new Receivebill();
        receivebill.setAccount(preorder.getPayacount());
        receivebill.setBusinesscode(preorder.getApplycode());
        receivebill.setBusinessid(preorder.getGuid());
        receivebill.setCode(OrderCodeFactory.getPreReceiveCode(0L));
        receivebill.setCompanyid(preorder.getCompnayid());
        receivebill.setCustomerid(preorder.getCustomerid());
        receivebill.setCustomername(preorder.getCustomername());
        receivebill.setPaymoney(preorder.getPaymoney());
        receivebill.setSuitid(preorder.getSuitid());
        receivebill.setBusiuserid(preorder.getUserid());
        receivebill.setBusiusername(preorder.getUsername());
        receivebill.setType("2");
        receivebill.setStatus(1);
        receivebill.setMoney(preorder.getSummoney());
        receivebill.setPaytype(preorder.getPaytype());
        receivebill.setPaytypename(preorder.getPaytypename());
        receivebill.setCreatedate(Calendar.getInstance().getTime());
        return receivebill;
    }
}
