package com.tailei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by houtailei on 2017/7/16.
 */
@Controller
public class HomeController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("/Home/index");
        String sql = "SELECT * from article";
        String sql2 ="SELECT * from notice";
        List list = jdbcTemplate.queryForList(sql);
        List list2=jdbcTemplate.queryForList(sql2);
        mav.addObject("notice",list2.get(0));
        mav.addObject("list",list);

        return mav;
    }
    /**
     * @Author:CCP
     * @Date:2017/7/18 14:41
     * @Description:文章详情
     * @User: M站
     */
    @RequestMapping(value = "/Detail/{id}")
    public ModelAndView detail(@PathVariable Integer id)  {
        ModelAndView mav = new ModelAndView("/Home/Detail");
        String sql= "SELECT * from article where id="+id;
        String sql2 ="SELECT * from notice";
        Map article = jdbcTemplate.queryForMap(sql);
        mav.addObject("article",article);
        List list2=jdbcTemplate.queryForList(sql2);
        mav.addObject("notice",list2.get(0));
        return mav;
    }
}

