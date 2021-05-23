package com.wms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @RequestMapping(value = { "/login" })
    public ModelAndView loginPage(ModelAndView model) {
        model.setViewName("login");

        return model;
    }

    @RequestMapping("/login-error")
    public ModelAndView handleLoginError(ModelAndView model) {
        model.setViewName("login");
        model.addObject("loginError", true);
        return model;
    }

}
