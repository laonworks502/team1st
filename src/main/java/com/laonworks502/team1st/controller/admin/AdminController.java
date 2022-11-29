package com.laonworks502.team1st.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @RequestMapping(value="adminlogin")
    public String adminlogin() throws Exception{
        return "adminlogin";
    }

    @GetMapping(value = "/adminid/{adminid}")
    public String getAdminId(@PathVariable("adminid") String var){
        return var;
    }
}


