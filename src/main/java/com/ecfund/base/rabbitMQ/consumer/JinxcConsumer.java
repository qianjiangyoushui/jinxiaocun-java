package com.ecfund.base.rabbitMQ.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.config.Constant;
import com.ecfund.base.rabbitMQ.producer.PrePurchaseApplyProducer;
import com.ecfund.base.rabbitMQ.producer.PreSaleProducer;
import com.ecfund.base.rabbitMQ.producer.PurchaseApplyProducer;
import com.ecfund.base.rabbitMQ.producer.SaleProducer;
import com.ecfund.base.service.process.ProcessApproversService;
import com.ecfund.base.service.purchase.PurchaseapplyService;
import com.ecfund.base.service.purchase.PurchasebilingService;
import com.ecfund.base.service.sale.PreorderService;
import com.ecfund.base.service.sale.SaleorderService;
import com.ecfund.base.util.dingtalk.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.charset.Charset;

import static com.ecfund.base.util.dingtalk.MessageUtil.getBodyContentAsString;

public class JinxcConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(JinxcConsumer.class);
    /**
     * 审批任务回调
     */
    private static final String BPMS_TASK_CHANGE = "bpms_task_change";

    /**
     * 审批实例回调
     */
    private static final String BPMS_INSTANCE_CHANGE = "bpms_instance_change";

    @Autowired
    private ProcessApproversService processApproversService;
    @Autowired
    private PurchaseapplyService purchaseapplyService;
    @Autowired
    private PurchasebilingService purchasebilingService;
    @Autowired
    private PreorderService preorderService;
    @Autowired
    private SaleorderService saleorderService;
    @Autowired
    private PrePurchaseApplyProducer prePurchaseApplyProducer;
    @Autowired
    private PurchaseApplyProducer purchaseApplyProducer;
    @Autowired
    private PreSaleProducer preSaleProducer;
    @Autowired
    private SaleProducer saleProducer;
    @Override
    public void onMessage(Message message) {
        logger.info("shijj receive message------->:{}", MessageUtil.getBodyContentAsString(message));
        //根据回调数据类型做不同的业务处理
        String messageStr = getBodyContentAsString(message);
        JSONObject obj = JSONObject.parseObject(messageStr);
        String eventType = obj.getString("EventType");
        boolean flags = Constant.PROCESS_CODE_PURCHASE.contains(obj.getString("processCode"));
        if (BPMS_TASK_CHANGE.equals(eventType)) {
            //todo: 实现审批的业务逻辑，如发消息
            processApproversService.saveByJSON(obj);
        } else if (BPMS_INSTANCE_CHANGE.equals(eventType)) {
            //todo: 实现审批的业务逻辑，如发消息
            processApproversService.saveByJSON(obj);
            if(Constant.PROCESS_CODE.contains(obj.getString("processCode"))&&"finish".equals(obj.getString("type"))){
                /**
                 * 采购申请审批通过后的逻辑。
                 */
                if("refuse".equals(obj.getString("result"))){
                    purchaseapplyService.updateStatus(obj.getString("processInstanceId"),3);
                }else if("agree".equals(obj.getString("result"))){
                    purchaseapplyService.updateStatus(obj.getString("processInstanceId"),2);
                    JSONObject result = new JSONObject();
                    result.put("content",obj.getString("processInstanceId"));
                    result.put("success",true);
                    try{
                        prePurchaseApplyProducer.sendMessage(result);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }else if(Constant.PROCESS_CODE_PURCHASE.contains(obj.getString("processCode"))&&"finish".equals(obj.getString("type"))){
                /**
                 * 采购开单申请通过的逻辑
                 */
                if("refuse".equals(obj.getString("result"))){
                    purchasebilingService.updateStatus(obj.getString("processInstanceId"),3);
                }else if("agree".equals(obj.getString("result"))){
                    purchasebilingService.updateStatus(obj.getString("processInstanceId"),2);
                    JSONObject result = new JSONObject();
                    result.put("content",obj.getString("processInstanceId"));
                    result.put("success",true);
                    try{
                        purchaseApplyProducer.sendMessage(result);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }else if(Constant.PROCESS_CODE_PRESALE.contains(obj.getString("processCode"))&&"finish".equals(obj.getString("type"))){
                /**
                 * 销售订货单申请通过的逻辑
                 */
                if("refuse".equals(obj.getString("result"))){
                    preorderService.updateStatus(obj.getString("processInstanceId"),3);
                }else if("agree".equals(obj.getString("result"))){
                    preorderService.updateStatus(obj.getString("processInstanceId"),2);
                    JSONObject result = new JSONObject();
                    result.put("content",obj.getString("processInstanceId"));
                    result.put("success",true);
                    try{
                        preSaleProducer.sendMessage(result);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }else if(Constant.PROCESS_CODE_SALE.contains(obj.getString("processCode"))&&"finish".equals(obj.getString("type"))){
                /**
                 * 销售开单申请通过的逻辑
                 */
                if("refuse".equals(obj.getString("result"))){
                    saleorderService.updateStatus(obj.getString("processInstanceId"),3);
                }else if("agree".equals(obj.getString("result"))){
                    saleorderService.updateStatus(obj.getString("processInstanceId"),2);
                    JSONObject result = new JSONObject();
                    result.put("content",obj.getString("processInstanceId"));
                    result.put("success",true);
                    try{
                        saleProducer.sendMessage(result);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        } else {
            // 其他类型事件处理
        }

    }


}
