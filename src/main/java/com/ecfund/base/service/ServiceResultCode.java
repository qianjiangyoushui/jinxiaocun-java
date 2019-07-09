package com.ecfund.base.service;

public enum ServiceResultCode {
    SYS_ERROR("-1","系统繁忙")
    ;

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private ServiceResultCode(String errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
