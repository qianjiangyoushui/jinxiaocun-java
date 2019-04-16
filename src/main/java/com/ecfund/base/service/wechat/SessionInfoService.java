package com.ecfund.base.service.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.ReturnCodeType;
import com.ecfund.base.dao.users.UsersDAO;
import com.ecfund.base.dao.users.WxUserDAO;
import com.ecfund.base.dao.wechat.AppinfoDAO;
import com.ecfund.base.dao.wechat.SessioninfoDAO;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.model.users.WxUser;
import com.ecfund.base.model.wechat.Appinfo;
import com.ecfund.base.util.common.HttpsRequestUtil;
import com.ecfund.base.util.common.MD5Utils;
import com.ecfund.base.util.common.StringUtils;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/10/13.
 */
@Service
public class SessionInfoService {
    @Autowired
    private SessioninfoDAO sessioninfoDAO;
    @Autowired
    private AppinfoDAO appinfoDAO;
    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private WxUserDAO wxUserDAO;


    /**
     * 登录，且换取session_key，用户信息。
     *
     * @param code
     * @param id
     * @return
     */
    @Transactional
    public String login(String code, String id, String userName, String passwd) throws Exception {
        Appinfo appInfo = appinfoDAO.findByGuid(id);
        Users user = new Users();
        user.setTelphone(userName);
        user = usersDAO.view(user);
        if(user!=null){
            user.setPassword(MD5Utils.encryString(passwd));
            if(!(!"-1".equals(user.getDel())&&MD5Utils.encryString(passwd).equals(user.getPassword()))){
                user = null;
            }
        }
        if (user == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", ReturnCodeType.MA_AUTH_ERR);
            jsonObject.put("returnMessage", "user not exist");
            jsonObject.put("data", "user null");
            return jsonObject.toString();
        }
        if (appInfo == null) {
            //是否配置appid
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", ReturnCodeType.MA_NO_APPID);
            jsonObject.put("returnMessage", "NO_APPID");
            jsonObject.put("data", "");
            return jsonObject.toString();
        } else {
            //配置appid,那么换取sessin_key及openid.
            StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");
            url.append("appid=").append(appInfo.getAppid());
            url.append("&secret=").append(appInfo.getSecret());
            url.append("&js_code=").append(code);
            url.append("&grant_type=").append("authorization_code");
            String result = HttpsRequestUtil.httpGet(url.toString(), HttpMethod.GET, null, null);
            JSONObject json = JSONObject.parseObject(result);
            if ("".equals(result)) {
                //换取失败
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("returnCode", ReturnCodeType.MA_WEIXIN_NET_ERR);
                jsonObject.put("returnMessage", "WEIXIN_NET_ERR");
                jsonObject.put("returnData", "");
                return jsonObject.toString();
            } else if (!StringUtils.isEmpty(json.get("openid"))
                    && !StringUtils.isEmpty(json.get("session_key"))) {
                //换取成功
                String openid = json.get("openid").toString();
                resetOpenid(openid);//将绑定重置
                JSONObject arrJson = new JSONObject();
                user.setOpenid(openid);
                usersDAO.update(user);
                arrJson.put("duration", json.get("expires_in"));
                JSONObject resultJson = new JSONObject();
                resultJson.put("code", ReturnCodeType.MA_OK);
                resultJson.put("returnMessage", "NEW_SESSION_SUCCESS");
                resultJson.put("data", arrJson);
                resultJson.put("user", user);
                return resultJson.toString();
            } else {
                //返回错误的数据
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("returnCode", ReturnCodeType.MA_DECRYPT_ERR);
                jsonObject.put("returnMessage", "DECRYPT_FAIL");
                jsonObject.put("returnData", "");
                return jsonObject.toString();
            }
        }
    }

    /**
     * 根据code换取sessionKey
     *
     * @param code
     * @param id
     * @return
     */
    public JSONObject code2Session(String code, String id) {
        JSONObject rt = new JSONObject();
        Appinfo appInfo = appinfoDAO.findByGuid(id);
        if (appInfo != null) {
            StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");
            url.append("appid=").append(appInfo.getAppid());
            url.append("&secret=").append(appInfo.getSecret());
            url.append("&js_code=").append(code);
            url.append("&grant_type=").append("authorization_code");
            String result = HttpsRequestUtil.httpGet(url.toString(), HttpMethod.GET, null, null);
            JSONObject jsonResult = JSONObject.parseObject(result);
            if (!StringUtils.isEmpty(jsonResult.get("openid")) && !StringUtils.isEmpty(jsonResult.get("session_key"))) {
                //换取成功。
                rt.put("success", true);
                JSONObject content = new JSONObject();
                content.put("openid", jsonResult.get("openid"));
                content.put("appid", appInfo.getAppid());
                content.put("sessionKey", jsonResult.get("session_key"));
                rt.put("content", content);
            } else {
                //换取失败
                rt.put("success", false);
                rt.put("erro", "session_key换取失败");
            }
        } else {
            //查不到appid
            rt.put("success", false);
            rt.put("erro", "appid不存在");
        }
        return rt;
    }

    /**
     * 根据code换取用户id
     * @param code
     * @param id
     * @return
     */
    public JSONObject updateSession(String code, String id) {
        JSONObject result = new JSONObject();
        JSONObject rt = code2Session(code, id);
        if (rt.getBooleanValue("success")) {
            //换取成功了
            JSONObject content = rt.getJSONObject("content");
            String openid = content.getString("openid");
            String sessionKey = content.getString("sessionKey");
            WxUser wxUser = new WxUser();
            wxUser.setOpenid(openid);
            try {
                wxUser = wxUserDAO.view(wxUser);
                if (wxUser == null) {
                    wxUser = new WxUser();
                    wxUser.setOpenid(openid);
                    wxUser.setAppid(content.getString("appid"));
                    wxUser.setSessionKey(sessionKey);
                    wxUser.setCreatedate(Calendar.getInstance().getTime());
                    String uuid = wxUserDAO.insert(wxUser);
                    result.put("success", true);
                    result.put("content", uuid);
                } else {
                    WxUser updateUser = new WxUser();
                    updateUser.setGuid(wxUser.getGuid());
                    updateUser.setOpenid(openid);
                    wxUser.setAppid(content.getString("appid"));
                    updateUser.setSessionKey(sessionKey);
                    wxUserDAO.update(wxUser);
                    result.put("success", true);
                    result.put("content", wxUser.getGuid());
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("erro", e.getMessage());
            }

        } else {
            //换取失败
            result.put("success", false);
            result.put("erro", rt.getString("erro"));
        }
        return result;
    }

    /**
     * 将绑定重置
     *
     * @param openid
     */
    private void resetOpenid(String openid) {
        Users user = new Users();
        user.setOpenid(openid);
        try {
            List<Users> list = usersDAO.find(user);
            for (Users u : list) {
                Users u1 = new Users();
                u1.setGuid(u.getGuid());
                u1.setOpenid("no-bind-openid");
                usersDAO.update(u1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完善用户信息
     * @param skey
     * @param userInfo
     * @return
     */
    public JSONObject updateUserInfo(String skey, String userInfo) {
        JSONObject result = new JSONObject();
        try {
            WxUser wxUser = wxUserDAO.viewByGuid(skey);
            JSONObject userJson = JSONObject.parseObject(userInfo);
            wxUser.setAvatarurl(userJson.getString("avatarUrl"));
            wxUser.setNickname(userJson.getString("nickName"));
            wxUser.setGender(userJson.getString("gender"));
            wxUser.setCountry(userJson.getString("country"));
            wxUser.setProvince(userJson.getString("province"));
            wxUser.setCity(userJson.getString("city"));
            wxUserDAO.update(wxUser);
            result.put("success", true);
            result.put("content",wxUser);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success", false);
            result.put("erro",e.getMessage());
        }
        return result;
    }
}
