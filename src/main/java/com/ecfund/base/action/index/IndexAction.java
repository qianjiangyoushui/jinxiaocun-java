package com.ecfund.base.action.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexAction {

	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public String index(){
		return "index/index";
	}
	
	@RequestMapping(value="/test.action",method=RequestMethod.POST)
	public String test(String file){
		System.out.println( file);
		return "";
	}
}
