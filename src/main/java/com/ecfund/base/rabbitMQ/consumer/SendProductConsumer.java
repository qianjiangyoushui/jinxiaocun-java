package com.ecfund.base.rabbitMQ.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.model.sale.Saleorderdetail;
import com.ecfund.base.model.storage.Sendproduct;
import com.ecfund.base.model.storage.Sendproductdetail;
import com.ecfund.base.service.sale.SaleorderService;
import com.ecfund.base.service.storage.SendproductService;
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

public class SendProductConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(SendProductConsumer.class);

    @Autowired
    private SaleorderService saleorderService;
    @Autowired
    private SendproductService sendproductService;
    @Override
    public void onMessage(Message message) {
        logger.info("shijj receive message------->:{}", message);
        String resultStr = MessageUtil.getBodyContentAsString(message);
        JSONObject result = JSONObject.parseObject(resultStr);
        String processId = result.getString("content");
        Saleorder saleorder = saleorderService.viewByProcessId(processId);
        Sendproduct sendproduct = buildSendProduct(saleorder);
        try{
            sendproductService.insertAll(sendproduct);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private Sendproduct buildSendProduct(Saleorder saleorder) {
        Sendproduct sendproduct = new Sendproduct();
        sendproduct.setAddress(saleorder.getAddress());
        sendproduct.setAmount(saleorder.getSummount());
        sendproduct.setCode(OrderCodeFactory.getSendProductCode(0L));
        sendproduct.setCompanyid(saleorder.getCompnayid());
        sendproduct.setSuitid(saleorder.getSuitid());
        sendproduct.setCreatedate(Calendar.getInstance().getTime());
        sendproduct.setSaleordercode(saleorder.getApplycode());
        sendproduct.setSaleorderid(saleorder.getGuid());
        sendproduct.setPhone(saleorder.getContactphone());
        sendproduct.setSummoney(saleorder.getSummoney());
        sendproduct.setSenddate(saleorder.getSenddate());
        sendproduct.setStatus(1);
        sendproduct.setCustomerid(saleorder.getCustomerid());
        sendproduct.setCustomername(saleorder.getCustomername());
        sendproduct.setBusiuserid(saleorder.getUserid());
        sendproduct.setBusiusername(saleorder.getUsername());
        List<Sendproductdetail> list = new ArrayList<Sendproductdetail>();
        for (Saleorderdetail bilingdetail:saleorder.getDetailList()) {
            Sendproductdetail sendproductdetail = new Sendproductdetail();
            sendproductdetail.setAmount(bilingdetail.getAmount());
            sendproductdetail.setImgurl(bilingdetail.getImgurl());
            sendproductdetail.setNormal(bilingdetail.getNormal());
            sendproductdetail.setPrice(bilingdetail.getPrice());
            sendproductdetail.setProductid(bilingdetail.getProductid());
            sendproductdetail.setProductname(bilingdetail.getProductname());
            sendproductdetail.setSummoney(bilingdetail.getSummoney());
            sendproductdetail.setUnit(bilingdetail.getUnit());
            sendproductdetail.setCreatedate(Calendar.getInstance().getTime());
            list.add(sendproductdetail);
        }
        sendproduct.setDetailList(list);
        return sendproduct;
    }

}
