package com.ecfund.base.action.wap.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/area")
public class AreaAction {

    @RequestMapping(value = "/list.action", method = RequestMethod.GET)
    public String list() {
        return "employee/arealist";
    }
}
