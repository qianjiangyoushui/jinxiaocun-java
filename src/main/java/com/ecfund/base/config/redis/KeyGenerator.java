package com.ecfund.base.config.redis;




import com.ecfund.base.model.publics.Dingrm;

import java.lang.reflect.Method;

/**
 * 文件描述:
 * 创建用户:emotion
 * 创建时间:2018/6/22
 */
public class KeyGenerator implements org.springframework.cache.interceptor.KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... params) {
        //规定  本类名+方法名+参数名 为key
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getSimpleName());
        sb.append("-");
        sb.append(method.getName());
        sb.append("-");
        for (Object param : params) {
            Dingrm dingrm = (Dingrm) param;
            sb.append(dingrm.getTabindex());
        }
        return sb.toString();
    }
}