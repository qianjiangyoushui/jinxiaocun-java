package com.ecfund.base.model.process;

import java.util.List;

public class PictureForm {
    public PictureForm(String name, List<String> value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 表单控件名称
     */

    private String name;

    /**
     * 表单值，数组格式
     */
    private List<String> value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
