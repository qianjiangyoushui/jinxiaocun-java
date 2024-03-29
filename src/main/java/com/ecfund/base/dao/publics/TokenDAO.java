package com.ecfund.base.dao.publics;

import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.publics.Token;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-19 10:44
 * @filename TokenDAO.java
 * 
 */

@Repository
public class TokenDAO extends BaseDAO<Token> {

	public void del(Token token) throws Exception{
		this.sqlSessionTemplate.delete(Token.class.getSimpleName()+".del", token);
	}
}