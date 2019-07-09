package com.ecfund.base.model.process;

public class TextForm {
    /**
     * 表单控件名称
     */
    private String name;

    /**
     * 表单值
     */
    private String value;

    public TextForm(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
