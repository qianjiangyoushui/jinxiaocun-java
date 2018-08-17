package com.ecfund.base.service.publics;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.MessageUitls;
import com.ecfund.base.util.common.UUIDCreate;
import com.ecfund.base.model.publics.Token;
import com.ecfund.base.dao.publics.TokenDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-19 10:44
 * @filename TokenService.java
 * 
 */

@Service
public class TokenService extends BaseService<Token> {

	@Autowired
	private TokenDAO tokenDAO;

	@Autowired
	public void setBaseDAO(TokenDAO tokenDAO) {
		super.setBaseDAO(tokenDAO);
	}
	
	public void del(Token token) throws Exception {
		tokenDAO.del(token);
	}
	
	/**
	 * 获取验证码
	 * 
	 * @param phone
	 * @param type
	 * @throws Exception
	 */
	public void getToken(String phone, String type) throws Exception {

		// 删除同类型，同手机号的记录
		Token tok = new Token();
		tok.setPhone(phone);
		tok.setType(type);
		this.del(tok);

		// 插入数据准备
		Token token = new Token();
		String code = Math.round(Math.random() * 899999 + 100000) + "";
		token.setId(UUIDCreate.get());
		token.setCode(code);
		token.setPhone(phone);
		token.setSenddate(new Date());
		token.setStatus(1);
		token.setType(type);
		
		String sndmsg = "亲,您的注册验证码是:" + code + ",有效时间是一分钟,记得打死也不能给别人哦！";
		
		MessageUitls.sendMessage(phone, sndmsg, "");
		tokenDAO.insert(token);
	}

}