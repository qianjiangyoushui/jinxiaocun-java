package com.ecfund.base.action.wap.president;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Message;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/president")
public class PresidentAction {

	@Autowired
	private LogsService logsService;
	@Autowired
	private SeedfileService seedfileService;
	@Autowired
	private UpimageService upimageService;
	@Autowired
	private DictionaryService dictionaryService;
	@RequestMapping(value = "/indexList.action", method = RequestMethod.GET)
	public String indexList( HttpServletRequest request,int type,String companyid,Model model) {
		request.getSession().setAttribute("companyid",companyid);
		model.addAttribute("type", type);
		return "president/indexList";
	}


}
