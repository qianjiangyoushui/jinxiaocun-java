package com.ecfund.base.rabbitMQ.consumer;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCspaceAddToSingleChatRequest;
import com.dingtalk.api.response.OapiCspaceAddToSingleChatResponse;
import com.ecfund.base.service.PrintPDF.PrintPDF;
import com.ecfund.base.service.PrintPDF.impl.SaleOrderPrintPDF;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.service.sale.SaleorderService;
import com.ecfund.base.util.dingtalk.AccessTokenUtil;
import com.ecfund.base.util.dingtalk.MessageUtil;
import com.taobao.api.internal.util.WebUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * jiaxd-create
 */

public class PrintOrderConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(PrintOrderConsumer.class);

    @Autowired
    private SaleorderService saleorderService;
    @Autowired
    private PrintPDF saleOrderPrintPDF;
    @Autowired
    private PrintPDF outStockPrintPDF;
    @Override
    public void onMessage(Message message) {
        /**
         * 1.数据结构 单据类型   单据guid 用户的id  agentId
         * 2.销售开单类型为saleOrder
         */
        logger.info("shijj receive message------->:{}", message);
        String resultStr = MessageUtil.getBodyContentAsString(message);
        JSONObject result = JSONObject.parseObject(resultStr);
        String orderType = result.getString("orderType");
        String guid = result.getString("guid");
        String userId = result.getString("userId");
        String agentId = result.getString("agentId");
        /**
         * 1.取出订单数据
         * 2.建立文件
         * 3.上传到盯盘
         * 4.发送给用户
         */
        try {

//        String fileName = "D:\\/usr\\/jiaxd\\/tmpFile\\/"+System.currentTimeMillis()+".pdf";
            Configuration config = new PropertiesConfiguration("systemConfig.properties");
            String fileName = config.getString("filePath")+System.currentTimeMillis()+".pdf";
        if("saleOrder".equals(orderType)){
            String mediaId = saleOrderPrintPDF.createPdf(fileName,agentId,guid);
            String result1 = send(mediaId,agentId,userId);
            JSONObject jsonResult = JSONObject.parseObject(result1);
            if("ok".equals(jsonResult.getString("errmsg"))){
                File file = new File(fileName);
                file.delete();
            }
        }else if("outStock".equals(orderType)){
            String mediaId =outStockPrintPDF.createPdf(fileName,agentId,guid);
            String result1 = send(mediaId,agentId,userId);
            JSONObject jsonResult = JSONObject.parseObject(result1);
            if("ok".equals(jsonResult.getString("errmsg"))){
                File file = new File(fileName);
                file.delete();
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 将钉盘的文件发送给用户
     * @param mediaId
     * @param agentId
     * @param userId
     * @return
     */
    private  String send(String mediaId,String agentId,String userId){
        OapiCspaceAddToSingleChatRequest request = new OapiCspaceAddToSingleChatRequest();
        request.setAgentId(agentId);
        request.setUserid(userId);
        request.setMediaId(mediaId);
        request.setFileName("销售开单打印.pdf");
        DingTalkClient client = null;
        try {
            client = new DefaultDingTalkClient("https://oapi.dingtalk.com/cspace/add_to_single_chat?"+WebUtils.buildQuery(request.getTextParams(),"utf-8"));
            OapiCspaceAddToSingleChatResponse response = client.execute(request, AccessTokenUtil.getToken());
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
