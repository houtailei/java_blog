package com.tailei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by houtailei on 2017/7/25.
 */
@Controller
public class AdminController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/Admin")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("/Admin/Home/index");
        return mav;
    }
}
