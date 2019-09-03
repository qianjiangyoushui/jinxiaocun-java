package com.ecfund.base.action.process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.dingtalk.oapi.lib.aes.Utils;
import com.ecfund.base.config.Constant;
import com.ecfund.base.config.URLConstant;
import com.ecfund.base.model.process.ProcessApprovers;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.service.ServiceResult;
import com.ecfund.base.service.ServiceResultCode;
import com.ecfund.base.service.process.ProcessApproversService;
import com.ecfund.base.util.dingtalk.AccessTokenUtil;
import com.ecfund.base.util.dingtalk.LogFormatter;
import com.ecfund.base.util.dingtalk.MessageUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.taobao.api.ApiException;
import com.taobao.api.FileItem;
import com.taobao.api.internal.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/pricessinstance")
public class ProcessAction {
    private static final Logger bizLogger = LoggerFactory.getLogger(ProcessAction.class);

    @Autowired
    private ProcessApproversService processApproversService;
    /**
     * 根据审批实例id获取审批详情
     * @param instanceId
     * @return
     */
    @RequestMapping(value = "/info.action", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResult getProcessinstanceById(@RequestParam String instanceId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_GET);
            OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
            request.setProcessInstanceId(instanceId);
            OapiProcessinstanceGetResponse response = client.execute(request, AccessTokenUtil.getToken());
            if (response.getErrcode().longValue() != 0) {
                return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
            }
            bizLogger.info(response.getProcessInstance().toString());
            return ServiceResult.success(response.getProcessInstance());
        } catch (Exception e) {
            String errLog = LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("instanceId", instanceId));
            bizLogger.info(errLog,e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(),ServiceResultCode.SYS_ERROR.getErrMsg());
        }
    }
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


    @RequestMapping(value = "/callback.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> callback(@RequestParam(value = "signature", required = false) String signature,
                                        @RequestParam(value = "timestamp", required = false) String timestamp,
                                        @RequestParam(value = "nonce", required = false) String nonce,
                                        @RequestBody(required = false) JSONObject json) {
        String params = " signature:"+signature + " timestamp:"+timestamp +" nonce:"+nonce+" json:"+json;
        try {
            DingTalkEncryptor dingTalkEncryptor = new DingTalkEncryptor(Constant.TOKEN, Constant.ENCODING_AES_KEY,
                    Constant.CORP_ID);

            //从post请求的body中获取回调信息的加密数据进行解密处理
            String encryptMsg = json.getString("encrypt");
            String plainText = dingTalkEncryptor.getDecryptMsg(signature, timestamp, nonce, encryptMsg);
            JSONObject obj = JSON.parseObject(plainText);

            //根据回调数据类型做不同的业务处理
            String eventType = obj.getString("EventType");
            if (BPMS_TASK_CHANGE.equals(eventType)) {
                //审批任务状态
                bizLogger.info("收到审批任务进度更新: " + plainText);
                //todo: 实现审批的业务逻辑，如发消息
                /**
                 * 1.获取流程编号，确定是哪个业务流程
                 * 2.记录任务状态
                 */
                ProcessApprovers processApprovers = new ProcessApprovers();

                String processInstanceId = obj.getString("processInstanceId");
                String processCode = obj.getString("processCode");
                processApprovers.setProcesscode(processCode);
                processApprovers.setProcessinstanceid(processInstanceId);
                processApprovers.setTitle(obj.getString("title"));
                processApprovers.setType(obj.getString("type"));
                String userid = obj.getString("staffId");

            } else if (BPMS_INSTANCE_CHANGE.equals(eventType)) {
                //审批实例状态
                /**
                 * 1.获取流程编码
                 * 流程结束时更新业务状态
                 */
                bizLogger.info("收到审批实例状态更新: " + plainText);
                //todo: 实现审批的业务逻辑，如发消息
                String processInstanceId = obj.getString("processInstanceId");
                String processCode = obj.getString("processCode");
                if (obj.containsKey("result") && obj.getString("result").equals("agree")) {
                    MessageUtil.sendMessageToOriginator(processInstanceId);
                }
            } else {
                // 其他类型事件处理
            }

            // 返回success的加密信息表示回调处理成功
            return dingTalkEncryptor.getEncryptedMap(CALLBACK_RESPONSE_SUCCESS, System.currentTimeMillis(), Utils.getRandomStr(8));
        } catch (Exception e) {
            //失败的情况，应用的开发者应该通过告警感知，并干预修复
            bizLogger.error("process callback failed！"+params,e);
            return null;
        }

    }

    public static void main(String[] args) throws Exception{
        // 先删除企业已有的回调
//        DingTalkClient client = new DefaultDingTalkClient(URLConstant.DELETE_CALLBACK);
//        OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
//        request.setHttpMethod("GET");
//        client.execute(request, AccessTokenUtil.getToken());
//
//        // 重新为企业注册回调
//        client = new DefaultDingTalkClient(URLConstant.REGISTER_CALLBACK);
//        OapiCallBackRegisterCallBackRequest registerRequest = new OapiCallBackRegisterCallBackRequest();
//        registerRequest.setUrl(Constant.CALLBACK_URL_HOST + "/jinxc/dingtalk/callback.action");
//        registerRequest.setAesKey(Constant.ENCODING_AES_KEY);
//        registerRequest.setToken(Constant.TOKEN);
//        registerRequest.setCallBackTag(Arrays.asList("bpms_instance_change", "bpms_task_change"));
//        OapiCallBackRegisterCallBackResponse registerResponse = client.execute(registerRequest,AccessTokenUtil.getToken());
//        if (registerResponse.isSuccess()) {
//            System.out.println("回调注册成功了！！！");
//        }else{
//            System.out.println("回调注册失败了！！！");
//        }
        pdfFile();
    }
    public static void   testFile(){
    }

    private static void pdfFile() throws Exception {
        String fileName = "D:\\/usr\\/jiaxd\\/tmpFile\\/"+System.currentTimeMillis()+".pdf";
        String mediaId="";
        try {
            Document document = new Document();
            BaseFont bf = null;
            Font fontChinese = null;

            BaseFont baseFont = BaseFont.createFont("/simfang.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

            fontChinese = new Font(baseFont);// 中文字体
            fontChinese.setSize(16);//字体大小
            Rectangle pageSize = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            pageSize.rotate();
            document.setPageSize(pageSize);
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            File file = new File(fileName);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);
            document.open();
            document.add(new Paragraph("销售开单",fontChinese));
            //表格处理   创建表格时必须指定表格的列数
            PdfPTable table = new PdfPTable(new float[] {150,400});
            table.setWidthPercentage(90);
            table.setLockedWidth(true);
            table.setTotalWidth(550);//550
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.getDefaultCell().setBorder(1);
            // 设置PDF表格标题
            //drawTable(fontChinese,table,guid);
            Saleorder saleorder = new Saleorder();
            try {
                PdfPCell cell1 = new PdfPCell();
                cell1.setColspan(1);
                cell1.setPhrase(new Paragraph("订货单号", fontChinese));
                cell1.setFixedHeight(24f);
                PdfPCell cell2 = new PdfPCell();
//                cell2.setColspan(5);
                cell2.setFixedHeight(24f);
                cell2.setPhrase(new Paragraph("XK201909021816037918", fontChinese));
                PdfPCell cell3 = new PdfPCell();
                cell3.setFixedHeight(24f);
                cell3.setPhrase(new Paragraph("客户名称", fontChinese));
                PdfPCell cell4 = new PdfPCell();
//                cell4.setColspan(5);
                cell4.setFixedHeight(24f);
                cell4.setPhrase(new Paragraph("福建省北繁南种瑞丰农业科技有限公司", fontChinese));
                PdfPCell cell5 = new PdfPCell();
                cell5.setPhrase(new Paragraph("结算方式：", fontChinese));
                PdfPCell cell6 = new PdfPCell();
                cell6.setPhrase(new Paragraph("转账支付", fontChinese));
//                cell6.setColspan(5);

                PdfPCell cell7 = new PdfPCell();
                cell7.setColspan(1);
                cell7.setPhrase(new Paragraph("发票类型", fontChinese));
                cell7.setFixedHeight(24f);
                PdfPCell cell8 = new PdfPCell();
//                cell8.setColspan(5);
                cell8.setFixedHeight(24f);
                cell8.setPhrase(new Paragraph("普通发票", fontChinese));

                PdfPCell cell9 = new PdfPCell();
                cell9.setColspan(1);
                cell9.setPhrase(new Paragraph("结算账号", fontChinese));
                cell9.setFixedHeight(24f);
                PdfPCell cell10 = new PdfPCell();
//                cell10.setColspan(5);
                cell10.setFixedHeight(24f);
                cell10.setPhrase(new Paragraph("6228029909876789", fontChinese));

                PdfPCell cell11 = new PdfPCell();
                cell11.setColspan(1);
                cell11.setPhrase(new Paragraph("送货地点", fontChinese));
                cell11.setFixedHeight(24f);
                PdfPCell cell12 = new PdfPCell();
//                cell12.setColspan(5);
                cell12.setFixedHeight(24f);
                cell12.setPhrase(new Paragraph("三明市建宁镇", fontChinese));

                PdfPCell cell13 = new PdfPCell();
                cell13.setColspan(1);
                cell13.setPhrase(new Paragraph("联系电话", fontChinese));
                cell13.setFixedHeight(24f);
                PdfPCell cell14 = new PdfPCell();
//                cell14.setColspan(5);
                cell14.setFixedHeight(24f);
                cell14.setPhrase(new Paragraph("18504816080", fontChinese));

                PdfPCell cell15 = new PdfPCell();
                cell15.setColspan(1);
                cell15.setPhrase(new Paragraph("总金额", fontChinese));
                cell15.setFixedHeight(24f);
                PdfPCell cell16 = new PdfPCell();
//                cell16.setColspan(5);
                cell16.setFixedHeight(24f);
                cell16.setPhrase(new Paragraph("38000.00", fontChinese));

                PdfPCell cell17 = new PdfPCell();
                cell17.setColspan(1);
                cell17.setPhrase(new Paragraph("关联订货单号", fontChinese));
                cell17.setFixedHeight(24f);
                PdfPCell cell18 = new PdfPCell();
//                cell18.setColspan(5);
                cell18.setFixedHeight(24f);
                cell18.setPhrase(new Paragraph("DH2019786756432", fontChinese));


                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);
                table.addCell(cell8);
                table.addCell(cell9);
                table.addCell(cell10);
                table.addCell(cell11);
                table.addCell(cell12);
                table.addCell(cell13);
                table.addCell(cell14);
                table.addCell(cell15);
                table.addCell(cell16);
                table.addCell(cell17);
                table.addCell(cell18);


            } catch (Exception e) {
                e.printStackTrace();
            }
            //制表单位等信息设置
            // pdf文档中加入table
            document.add(table);
            document.add(new Paragraph("货品明细",fontChinese));
            document.add(createDetailTable(fontChinese));
            document.add(new Paragraph("客户确认签字：",fontChinese));
            document.add(new Paragraph("库管签字：",fontChinese));
            document.close();
            writer.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static PdfPTable createDetailTable( Font fontChinese){
        PdfPTable table = new PdfPTable(new float[] {150,80, 80,80, 80,80});
        table.setWidthPercentage(90);
        table.setLockedWidth(true);
        table.setTotalWidth(550);//550
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.getDefaultCell().setBorder(1);
        // 设置PDF表格标题
        //drawTable(fontChinese,table,guid);
        Saleorder saleorder = new Saleorder();
        try {
            PdfPCell cell1 = new PdfPCell();
            cell1.setColspan(1);
            cell1.setPhrase(new Paragraph("货品名称", fontChinese));
            cell1.setFixedHeight(24f);

            PdfPCell cell2 = new PdfPCell();
            cell2.setColspan(1);
            cell2.setPhrase(new Paragraph("规格", fontChinese));
            cell2.setFixedHeight(24f);

            PdfPCell cell3 = new PdfPCell();
            cell3.setColspan(1);
            cell3.setPhrase(new Paragraph("单位", fontChinese));
            cell3.setFixedHeight(24f);

            PdfPCell cell4 = new PdfPCell();
            cell4.setColspan(1);
            cell4.setPhrase(new Paragraph("单价", fontChinese));
            cell4.setFixedHeight(24f);

            PdfPCell cell5= new PdfPCell();
            cell5.setColspan(1);
            cell5.setPhrase(new Paragraph("数量", fontChinese));
            cell5.setFixedHeight(24f);

            PdfPCell cell6 = new PdfPCell();
            cell6.setColspan(1);
            cell6.setPhrase(new Paragraph("金额", fontChinese));
            cell6.setFixedHeight(24f);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            for(int i=0;i<5;i++){
                table.addCell(new PdfPCell(new Paragraph("六国化工马铃薯专用复合肥专用专用123",fontChinese)));
                table.addCell(new PdfPCell(new Paragraph("50千克/包",fontChinese)));
                table.addCell(new PdfPCell(new Paragraph("吨",fontChinese)));
                table.addCell(new PdfPCell(new Paragraph("3400",fontChinese)));
                table.addCell(new PdfPCell(new Paragraph("100",fontChinese)));
                table.addCell(new PdfPCell(new Paragraph("340000",fontChinese)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    private static void send(String mediaId){
        OapiCspaceAddToSingleChatRequest request = new OapiCspaceAddToSingleChatRequest();
        request.setAgentId("274474833");
        request.setUserid("024333162935579879");
        request.setMediaId(mediaId);
        request.setFileName("test.pdf");
        DingTalkClient client = null;
        try {
            client = new DefaultDingTalkClient("https://oapi.dingtalk.com/cspace/add_to_single_chat?"+WebUtils.buildQuery(request.getTextParams(),"utf-8"));
            OapiCspaceAddToSingleChatResponse response = client.execute(request, AccessTokenUtil.getToken());
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
