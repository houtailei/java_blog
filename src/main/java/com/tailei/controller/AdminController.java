package com.tailei.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houtailei on 2017/7/25.
 */
@Controller
public class AdminController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/Admin")
    public ModelAndView Admin() {
        ModelAndView mav = new ModelAndView("/Admin/Home/Login");
        return mav;
    }

    @RequestMapping("/Admin/Home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("/Admin/Home/index");
        return mav;
    }

    @RequestMapping("/Admin/check")
    @ResponseBody
    public Object check( String username, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        String sql = "SELECT * from article";
        List list = jdbcTemplate.queryForList(sql);
        result.put("Result", true);
        return result;
    }

}
