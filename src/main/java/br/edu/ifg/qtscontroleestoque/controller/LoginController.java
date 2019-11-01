package br.edu.ifg.qtscontroleestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView init() {
        ModelAndView mav = new ModelAndView("login");

        return mav;
    }
}
