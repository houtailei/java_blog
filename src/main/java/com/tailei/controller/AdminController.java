package com.tailei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
//    博客管理
    @RequestMapping("/Admin/Blog")
    public ModelAndView Blog() {
        ModelAndView mav = new ModelAndView("/Admin/page/Blog");
        String sql = "SELECT * from article";
        List list = jdbcTemplate.queryForList(sql);
        mav.addObject("articlelist",list);
        return mav;
    }
//     公告管理
    @RequestMapping("/Admin/Notice")
    public ModelAndView Notice() {
        ModelAndView mav = new ModelAndView("/Admin/page/Notice");
        String sql = "SELECT * from notice";
        Map list = jdbcTemplate.queryForMap(sql);
        mav.addObject("Notice",list);
        return mav;
    }

    @RequestMapping("/Admin/check")
    @ResponseBody
    public Object check(String username, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        String  sql = "SELECT * from member t WHERE t.name = ?";
        List<Map<String, Object>> member = jdbcTemplate.queryForList(sql,new Object[]{username});
        if(member.size()==0){
            result.put("Result", false);
            result.put("msg", "您输入的账号不存哦");
        }else{
            if(member.get(0).get("password").equals(password)){
                result.put("Result", true);
            }else{
                result.put("Result", false);
                result.put("msg", "您输入的密码不正确");
            }

        }

        return result;
    }

    @RequestMapping("/Admin/reviseNotice")
    @ResponseBody
    public Object check(String text) {
        String sql = "update notice set text = ?";
        int temp = jdbcTemplate.update(sql,new Object[]{text});
        if (temp > 0) {
            return true;
        }else {
            return false;
        }

    }

}
