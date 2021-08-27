package com.group9.fundmanager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 * @author abe
 */
@Controller
public class HelloController {
 
    @RequestMapping
    public String hello()  {
        return "redirect:funds";
    }
}
