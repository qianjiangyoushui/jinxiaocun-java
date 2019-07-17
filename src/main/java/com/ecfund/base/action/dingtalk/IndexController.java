package com.ecfund.base.action.dingtalk;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.dingtalk.oapi.lib.aes.Utils;
import com.ecfund.base.action.purchase.CommonAction;
import com.ecfund.base.config.Constant;
import com.ecfund.base.config.URLConstant;
import com.ecfund.base.model.process.ProcessApprovers;
import com.ecfund.base.rabbitMQ.producer.MessageProducer;
import com.ecfund.base.rabbitMQ.producer.PrePurchaseApplyProducer;
import com.ecfund.base.rabbitMQ.producer.PurchaseApplyProducer;
import com.ecfund.base.service.process.ProcessApproversService;
import com.ecfund.base.service.purchase.PurchaseapplyService;
import com.ecfund.base.util.dingtalk.AccessTokenUtil;
import com.ecfund.base.util.dingtalk.MessageUtil;
import com.ecfund.base.util.dingtalk.ServiceResult;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 企业内部E应用Quick-Start示例代码 实现了最简单的免密登录（免登）功能
 */
@Controller
@RequestMapping("/dingtalk")
public class IndexController {
    private static final Logger bizLogger = LoggerFactory.getLogger(IndexController.class);
    private static final Logger mainLogger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private ProcessApproversService processApproversService;
    @Autowired
    private PurchaseapplyService purchaseapplyService;
    @Autowired
    private PrePurchaseApplyProducer prePurchaseApplyProducer;
    @Autowired
    private PurchaseApplyProducer purchaseApplyProducer;
    @Autowired
    private MessageProducer messageProducer;
    /**
     * 创建套件后，验证回调URL创建有效事件（第一次保存回调URL之前）
     */
    private static final String CHECK_URL = "check_url";

    /**
     * 审批任务回调
     */
    private static final String BPMS_TASK_CHANGE = "bpms_task_change";

    /**
     * 审批实例回调
     */
    private static final String BPMS_INSTANCE_CHANGE = "bpms_instance_change";

    /**
     * 相应钉钉回调时的值
     */
    private static final String CALLBACK_RESPONSE_SUCCESS = "success";


    /**
     * 欢迎页面,通过url访问，判断后端服务是否启动
     */
    @RequestMapping(value = "/welcome.action", method = RequestMethod.GET)
    public @ResponseBody String welcome() {
        return "welcome";
    }

    /**
     * 钉钉用户登录，显示当前登录用户的userId和名称
     *
     * @param requestAuthCode 免登临时code
     */
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult login(@RequestParam(value = "authCode") String requestAuthCode, HttpServletRequest servletRequest) {
        //获取accessToken,注意正是代码要有异常流处理
        String accessToken = AccessTokenUtil.getToken();

        //获取用户信息
        DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_GET_USER_INFO);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(requestAuthCode);
        request.setHttpMethod("GET");

        OapiUserGetuserinfoResponse response;
        try {
            response = client.execute(request, accessToken);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        //3.查询得到当前用户的userId
        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
        String userId = response.getUserid();

        OapiUserGetResponse user  = getUser(accessToken, userId);
        servletRequest.getSession().setAttribute("user",user);
        Map orderMap = JSONObject.parseObject(user.getOrderInDepts(),Map.class);
        //返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("unionid", user.getUnionid());
        resultMap.put("userName", user.getName());
        ServiceResult serviceResult = ServiceResult.success(resultMap);
        return serviceResult;
    }
    @RequestMapping(value = "/test.action", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult test( HttpServletRequest servletRequest ) {
        String userId = servletRequest.getSession().getAttribute("userId").toString();
        ServiceResult serviceResult = ServiceResult.success(userId);
        return serviceResult;
    }
    /**
     * 获取用户姓名
     *
     * @param accessToken
     * @param userId
     * @return
     */
    private OapiUserGetResponse getUser(String accessToken, String userId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_USER_GET);
            OapiUserGetRequest request = new OapiUserGetRequest();
            request.setUserid(userId);
            request.setHttpMethod("GET");
            OapiUserGetResponse response = client.execute(request, accessToken);
            return response;
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/callback.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> callback(@RequestParam(value = "signature", required = false) String signature,
                                        @RequestParam(value = "timestamp", required = false) String timestamp,
                                        @RequestParam(value = "nonce", required = false) String nonce,
                                        @RequestBody(required = false) JSONObject json) {
        String params = " signature:" + signature + " timestamp:" + timestamp + " nonce:" + nonce + " json:" + json;
        try {
            DingTalkEncryptor dingTalkEncryptor = new DingTalkEncryptor(Constant.TOKEN, Constant.ENCODING_AES_KEY,
                    Constant.CORP_ID);

            //从post请求的body中获取回调信息的加密数据进行解密处理
            String encryptMsg = json.getString("encrypt");
            String plainText = dingTalkEncryptor.getDecryptMsg(signature, timestamp, nonce, encryptMsg);
            JSONObject obj = JSON.parseObject(plainText);
            messageProducer.sendMessage(obj);

            // 返回success的加密信息表示回调处理成功
            return dingTalkEncryptor.getEncryptedMap(CALLBACK_RESPONSE_SUCCESS, System.currentTimeMillis(), Utils.getRandomStr(8));
        } catch (Exception e) {
            //失败的情况，应用的开发者应该通过告警感知，并干预修复
            mainLogger.error("process callback failed！" + params, e);
            return null;
        }

    }

}