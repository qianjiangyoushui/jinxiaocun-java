package com.ecfund.base.service.process;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.config.URLConstant;
import com.ecfund.base.dao.process.ProcessApproversDAO;
import com.ecfund.base.model.process.ProcessApprovers;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.dingtalk.AccessTokenUtil;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ProcessApproversService extends BaseService<ProcessApprovers> {

    @Autowired
    private ProcessApproversDAO processApproversDAO;

    @Autowired
    public void setBaseDAO(ProcessApproversDAO processApproversDAO) {
        super.setBaseDAO(processApproversDAO);
    }
    /**
     * 审批任务回调
     */
    private static final String BPMS_TASK_CHANGE = "bpms_task_change";

    /**
     * 审批实例回调
     */
    private static final String BPMS_INSTANCE_CHANGE = "bpms_instance_change";

    public void saveByJSON(JSONObject obj) {
        String eventType = obj.getString("EventType");
        if (BPMS_TASK_CHANGE.equals(eventType)) {
            //logger.info("收到审批任务进度更新: " + plainText);
            //todo: 实现审批的业务逻辑，如发消息
            ProcessApprovers processApprovers = new ProcessApprovers();
            String processInstanceId = obj.getString("processInstanceId");
            String processCode = obj.getString("processCode");
            processApprovers.setProcesscode(processCode);
            processApprovers.setProcessinstanceid(processInstanceId);
            processApprovers.setTitle(obj.getString("title"));
            processApprovers.setType(obj.getString("type"));
            String userid = obj.getString("staffId");
            OapiUserGetResponse user = getUser(AccessTokenUtil.getToken(), userid);
            processApprovers.setStaffid(userid);
            processApprovers.setStaffname(user.getName());
            processApprovers.setResult(obj.getString("result"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(obj.getLong("createTime"));
            processApprovers.setCreatetime(calendar.getTime());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(obj.getLong("finishTime")==null?System.currentTimeMillis():obj.getLong("finishTime"));
            processApprovers.setFinishtime(calendar2.getTime());
            processApprovers.setUpdatedate(Calendar.getInstance().getTime());
            processApprovers.setRemark(obj.getString("remark"));
            processApprovers.setEventtype(eventType);
            try {
                this.insert(processApprovers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (BPMS_INSTANCE_CHANGE.equals(eventType)) {
            //bizLogger.info("收到审批实例状态更新: " + plainText);
            //todo: 实现审批的业务逻辑，如发消息
            ProcessApprovers processApprovers = new ProcessApprovers();
            String processInstanceId = obj.getString("processInstanceId");
            String processCode = obj.getString("processCode");
            processApprovers.setProcesscode(processCode);
            processApprovers.setProcessinstanceid(processInstanceId);
            processApprovers.setTitle(obj.getString("title"));
            processApprovers.setType(obj.getString("type"));
            String userid = obj.getString("staffId");
            OapiUserGetResponse user = getUser(AccessTokenUtil.getToken(), userid);
            processApprovers.setStaffid(userid);
            processApprovers.setStaffname(user.getName());
            processApprovers.setResult(obj.getString("result"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(obj.getLong("createTime"));
            processApprovers.setCreatetime(calendar.getTime());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(obj.getLong("finishTime")==null?System.currentTimeMillis():obj.getLong("finishTime"));
            processApprovers.setFinishtime(calendar2.getTime());
            processApprovers.setUpdatedate(Calendar.getInstance().getTime());
            processApprovers.setUrl(obj.getString("url"));
            processApprovers.setEventtype(eventType);
            processApprovers.setEventtype(eventType);
            try {
                this.insert(processApprovers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
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
}