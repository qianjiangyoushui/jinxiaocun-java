package com.ecfund.base.util.dingtalk;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.ecfund.base.config.Constant;
import com.ecfund.base.config.URLConstant;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.utils.SerializationUtils;

import java.nio.charset.Charset;

public class MessageUtil {
    private static final Logger bizLogger = LoggerFactory.getLogger(MessageUtil.class);

    public static void sendMessageToOriginator(String processInstanceId) throws RuntimeException {
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_GET);
            OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
            request.setProcessInstanceId(processInstanceId);
            OapiProcessinstanceGetResponse response = client.execute(request, AccessTokenUtil.getToken());
            String recieverUserId = response.getProcessInstance().getOriginatorUserid();

            client = new DefaultDingTalkClient(URLConstant.MESSAGE_ASYNCSEND);

            OapiMessageCorpconversationAsyncsendV2Request messageRequest = new OapiMessageCorpconversationAsyncsendV2Request();
            messageRequest.setUseridList(recieverUserId);
            messageRequest.setAgentId(Constant.AGENTID);
            messageRequest.setToAllUser(false);

            OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            msg.setMsgtype("text");
            msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
            msg.getText().setContent("出差申请通过了，快去订机票吧");
            messageRequest.setMsg(msg);

            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(messageRequest,AccessTokenUtil.getToken());
        } catch (ApiException e) {
            bizLogger.error("send message failed", e);
            throw new RuntimeException();
        }
    }

    public static String getBodyContentAsString(Message message) {
        if (message.getBody() == null) {
            return null;
        } else {
            try {
                String contentType = message.getMessageProperties() != null ? message.getMessageProperties().getContentType() : null;
                if ("application/x-java-serialized-object".equals(contentType)) {
                    return SerializationUtils.deserialize(message.getBody()).toString();
                }

                if ("text/plain".equals(contentType) || "application/json".equals(contentType) || "text/x-json".equals(contentType) || "application/xml".equals(contentType)) {
                    return new String(message.getBody(), Charset.defaultCharset().name());
                }
            } catch (Exception var2) {
                ;var2.printStackTrace();
            }

            return message.getBody().toString();
        }
    }
}
