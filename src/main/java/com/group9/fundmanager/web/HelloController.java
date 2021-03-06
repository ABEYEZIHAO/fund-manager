package com.group9.fundmanager.web;
import java.text.DateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class HelloController {
 
    @RequestMapping("/index")
    public String hello(Model m) throws Exception {
    	m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));

        return "index";
    }
}
