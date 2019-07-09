package com.ecfund.base.service.process;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.ecfund.base.config.Constant;
import com.ecfund.base.config.URLConstant;
import com.ecfund.base.model.process.ProcessInstanceInputVO;
import com.ecfund.base.service.ServiceResultCode;
import com.ecfund.base.util.dingtalk.AccessTokenUtil;
import com.ecfund.base.util.dingtalk.LogFormatter;
import com.ecfund.base.util.dingtalk.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessinstanceService {
    private static final Logger bizLogger = LoggerFactory.getLogger(ProcessinstanceService.class);

    public ServiceResult<String> startProcessInstance(ProcessInstanceInputVO processInstance){
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_START);
            OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
            request.setProcessCode(Constant.PROCESS_CODE);

            request.setFormComponentValues(processInstance.generateForms());

            /**
             * 如果想复用审批固定流程，使用或签会签的话，可以不传审批人，具体请参考文档： https://open-doc.dingtalk.com/microapp/serverapi2/ebkwx8
             * 本次quickstart，演示不传审批人的场景
             */
            //request.setApprovers(processInstance.getOriginatorUserId());
            request.setOriginatorUserId(processInstance.getOriginatorUserId());
            request.setDeptId(processInstance.getDeptId());
            request.setCcList(processInstance.getOriginatorUserId());
            request.setCcPosition("FINISH");
            System.out.println(request.getFormComponentValues());
           OapiProcessinstanceCreateResponse response = client.execute(request, AccessTokenUtil.getToken());
           //OapiProcessinstanceCreateResponse response = null;

            if (response.getErrcode().longValue() != 0) {
                return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
            }
            return ServiceResult.success(response.getProcessInstanceId());

        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("processInstance", JSON.toJSONString(processInstance)));
            bizLogger.info(errLog,e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(),ServiceResultCode.SYS_ERROR.getErrMsg());
        }
    }
    public ServiceResult<String> startMyProcessInstance(ProcessInstanceInputVO processInstance,String processCode){
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_START);
            OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
            request.setProcessCode(processCode);

            request.setFormComponentValues(processInstance.generateForms());

            /**
             * 如果想复用审批固定流程，使用或签会签的话，可以不传审批人，具体请参考文档： https://open-doc.dingtalk.com/microapp/serverapi2/ebkwx8
             * 本次quickstart，演示不传审批人的场景
             */
            //request.setApprovers(processInstance.getOriginatorUserId());
            request.setOriginatorUserId(processInstance.getOriginatorUserId());
            request.setDeptId(processInstance.getDeptId());
            request.setCcList(processInstance.getOriginatorUserId());
            request.setCcPosition("FINISH");
            //System.out.println(request.getFormComponentValues());
           OapiProcessinstanceCreateResponse response = client.execute(request, AccessTokenUtil.getToken());
           //OapiProcessinstanceCreateResponse response = null;

            if (response.getErrcode().longValue() != 0) {
                return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
            }
            return ServiceResult.success(response.getProcessInstanceId());

        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("processInstance", JSON.toJSONString(processInstance)));
            bizLogger.info(errLog,e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(),ServiceResultCode.SYS_ERROR.getErrMsg());
        }
    }
}
