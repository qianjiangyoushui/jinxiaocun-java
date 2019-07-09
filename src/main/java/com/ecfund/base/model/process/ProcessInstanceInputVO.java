package com.ecfund.base.model.process;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest.FormComponentValueVo;
import com.ecfund.base.util.common.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @author 令久
 * @date 2018/08/22
 * 审批实例
 */
public class ProcessInstanceInputVO {
    /**
     * 审批人列表
     */
    private String approvers;

    /**
     * 抄送人列表
     */
    private String ccList;

    /**
     * 抄送时机
     */
    private String ccPosition;

    /**
     * 审批发起人部门id
     */
    private Long deptId;

    /**
     * 单行输入框、多行输入框的表单数据
     */
    private List<TextForm> textForms;

    /**
     * 图片表单数据
     */
    private List<PictureForm> pictureForms;

    /**
     * 明细表单数据
     */
    private List<DetailForm> detailForms;

    /**
     * 审批发起人
     */
    private String originatorUserId;




    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers;
    }

    public String getCcList() {
        return ccList;
    }

    public void setCcList(String ccList) {
        this.ccList = ccList;
    }

    public String getCcPosition() {
        return ccPosition;
    }

    public void setCcPosition(String ccPosition) {
        this.ccPosition = ccPosition;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<TextForm> getTextForms() {
        return textForms;
    }

    public void setTextForms(List<TextForm> textForms) {
        this.textForms = textForms;
    }

    public List<PictureForm> getPictureForms() {
        return pictureForms;
    }

    public void setPictureForms(List<PictureForm> pictureForms) {
        this.pictureForms = pictureForms;
    }

    public List<DetailForm> getDetailForms() {
        return detailForms;
    }

    public void setDetailForms(List<DetailForm> detailForms) {
        this.detailForms = detailForms;
    }

    public String getOriginatorUserId() {
        return originatorUserId;
    }

    public void setOriginatorUserId(String originatorUserId) {
        this.originatorUserId = originatorUserId;
    }

    /**
     * 生成FormComponentValueVo，用于调用发起审批实例的接口
     * @return
     */
    public List<FormComponentValueVo> generateForms() {
        List<FormComponentValueVo> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(textForms)) {
            for (TextForm textForm : textForms) {
                result.add(generateFormWithTextForm(textForm));
            }
        }

        if (!CollectionUtils.isEmpty(pictureForms)) {
            for (PictureForm pictureForm : pictureForms) {
                result.add(generateFormWithPictureForm(pictureForm));
            }
        }

        if (!CollectionUtils.isEmpty(detailForms)) {
            for (DetailForm detailForm : detailForms) {
                result.add(generateFormWithDetailForm(detailForm));
            }
        }
        return result;
    }

    private FormComponentValueVo generateFormWithTextForm(TextForm textForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(textForm.getName());
        form.setValue(textForm.getValue());
        return form;
    }

    private FormComponentValueVo generateFormWithPictureForm(PictureForm pictureForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(pictureForm.getName());
        form.setValue(JSON.toJSONString(pictureForm.getValue()));
        return form;
    }

    private FormComponentValueVo generateFormWithDetailForm(DetailForm detailForm1) {
        List<FormComponentValueVo> result = new ArrayList<FormComponentValueVo>();
        List<String> valueString = new ArrayList<String>();
        FormComponentValueVo form1 = new FormComponentValueVo();
        form1.setName(detailForm1.getName());

        if (!CollectionUtils.isEmpty(detailForm1.getDetailForms())) {
            for (DetailForm detailForm : detailForm1.getDetailForms()) {
                result.add(generateFormWithDetailForm1(detailForm));
                valueString.add(generateFormWithDetailForm1(detailForm).getValue());
            }
        }
        StringBuffer sb = new StringBuffer("[");
        for(int i=0;i<valueString.size();i++){
            if(i==valueString.size()-1){
                sb.append(valueString.get(i));
            }else{
                sb.append(valueString.get(i)).append(",");
            }
        }
        sb.append("]");
        form1.setValue(sb.toString());
        //form1.setValue(JSON.toJSONString(valueString));
        return form1;
    }

    private FormComponentValueVo generateFormWithDetailForm1(DetailForm detailForm) {
        FormComponentValueVo form = new FormComponentValueVo();
        form.setName(detailForm.getName());

        List<FormComponentValueVo> forms = new ArrayList<>();
        if (!CollectionUtils.isEmpty(detailForm.getTextForms())) {
            for (TextForm textForm : detailForm.getTextForms()) {
                forms.add(generateFormWithTextForm(textForm));
            }
        }

        if (!CollectionUtils.isEmpty(detailForm.getPictureForms())) {
            for (PictureForm pictureForm : detailForm.getPictureForms()) {
                forms.add(generateFormWithPictureForm(pictureForm));
            }
        }
        form.setValue(JSON.toJSONString(forms));

        return form;
    }
}
