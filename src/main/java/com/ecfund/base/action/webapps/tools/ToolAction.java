package com.ecfund.base.action.webapps.tools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/webapps/tools")
public class ToolAction {
    @RequestMapping(value = "/tool.action",produces = "application/json;charset=utf-8")
    public String tool(){
        return "tools/tool";
    }
}
