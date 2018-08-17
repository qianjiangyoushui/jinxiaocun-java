package com.ecfund.base.action.down;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/down")
public class DownloadAction {

	@RequestMapping(value="/go.action",method=RequestMethod.GET)
	public String goDownload(){
		return "down/download";
	}
}
