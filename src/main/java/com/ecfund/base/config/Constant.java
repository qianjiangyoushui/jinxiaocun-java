package com.ecfund.base.config;

/**
 * 项目中的常量定义类
 */
public class Constant {
    /**
     * 开发者后台->企业自建应用->选择您创建的E应用->查看->AppKey
     */
    public static final String APP_KEY = "dingdddulcoipbehonon";
    /**
     * 开发者后台->企业自建应用->选择您创建的E应用->查看->AppSecret
     */
    public static final String APP_SECRET="mUbbl4AFDakKUvUz5jo_NWCUYw8dTW9KWgT9JwL-kHpIE3cXCLhZBT1BLlWB9HTq";

    /**
     * 企业corpid, 需要修改成开发者所在企业
     */
    public static final String CORP_ID = "ding32211ec91474443635c2f4657eb6378f";
    /**
     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成
     */
    public static final String ENCODING_AES_KEY = "qwertyuioplkjhgfdsazxcvbnm1234567890poiuytr";

    /**
     * 加解密需要用到的token，企业可以随机填写。如 "12345"
     */
    public static final String TOKEN = "zaq1xsw2";

    /**
     * 应用的agentdId，登录开发者后台可查看
     */
    public static final Long AGENTID = 274474833L;

    /**
     * 审批模板唯一标识，可以在审批管理后台找到
     */
    public static final String PROCESS_CODE = "PROC-C6515836-40F8-45B6-ACA2-70FBA2F34AEC";
    /**
     * 采购开单流程
     */
    public static final String PROCESS_CODE_PURCHASE = "PROC-A7CCBE8F-1F2E-4F47-AE53-6FFB8164F8EA";
    /**
     * 订货单流程
     */
    public static final String PROCESS_CODE_PRESALE = "PROC-B90BF9E6-9D6E-42F1-9D65-EA44A6FCC71E";
    /**
     * 销售开单流程
     */
    public static final String PROCESS_CODE_SALE= "PROC-E7658982-73C3-4591-AD6D-A9807F56B13A";

    /**
     * 回调host
     */
    public static final String CALLBACK_URL_HOST = "https://erp.nongyitianxia.com";
}
