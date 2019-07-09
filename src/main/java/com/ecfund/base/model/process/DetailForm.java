package com.ecfund.base.model.process;

import java.util.List;

public class DetailForm {
    public DetailForm(String name, List<TextForm> textForms, List<PictureForm> pictureForms) {
        this.name = name;
        this.textForms = textForms;
        this.pictureForms = pictureForms;
    }

    public DetailForm() {
    }

    /**
     * 表单控件名称
     */

    private String name;

    /**
     * 明细里的文本控件列表
     */
    private List<TextForm> textForms;

    private List<DetailForm> detailForms;
    /**
     * 明细里的图片控件列表
     */
    private List<PictureForm> pictureForms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
