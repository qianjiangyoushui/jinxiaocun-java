package com.ecfund.base.convert;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/9.
 */
public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String stringDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(stringDate);
		} catch (ParseException e) {
			try {
				return simpleDateFormat1.parse(stringDate);
			} catch (ParseException e1) {
				try {
					return simpleDateFormat2.parse(stringDate);
				} catch (Exception e2) {
					e2.printStackTrace();
					return null;
				}
			}
		}
	}
	
}
