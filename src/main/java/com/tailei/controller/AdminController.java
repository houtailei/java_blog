package com.tailei.controller;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private Map<String, Object> todo(Map<String, Object> o){
        try {
            Date date = (Date)o.get("create_time");
            o.put("create_time",DateFormatUtils.format(date,"yyyy-MM-dd"));
        }catch (
                Exception e
                ){}
        return o;
    }
//    博客管理
    @RequestMapping("/Admin/Blog")
    public ModelAndView Blog() {
        ModelAndView mav = new ModelAndView("/Admin/page/Blog");
        String sql = "SELECT * from article ";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        list = list.stream().map(this::todo).collect(Collectors.toList());

        mav.addObject("articlelist",list);

        return mav;
    }
//    新增博客 视图
    @RequestMapping("/Admin/AddBlog")
    public ModelAndView AddBlog() {
        ModelAndView mav = new ModelAndView("/Admin/page/AddBlog");
        return mav;
    }
//    修改博客 视图
@RequestMapping("/Admin/UpdateBlog/{id}")
public ModelAndView UpdateBlog(@PathVariable Integer id) {
    ModelAndView mav = new ModelAndView("/Admin/page/UpdateBlog");
    String sql= "SELECT * from article where id="+id;
    Map article = jdbcTemplate.queryForMap(sql);
    mav.addObject("article",article);
    return mav;
}
//    新增博客 接口
    @RequestMapping("/api/Admin/addBlog")
    @ResponseBody
    public Object apiaddBlog(String title,String description,String image,String content,Integer isdel) {
        String sql = "insert INTO article(title,description,image,content,isdel) values(?,?,?,?,?)";
        int temp = jdbcTemplate.update(sql,new Object[]{title,description,image,content,isdel});
        if (temp > 0) {
            return true;
        }else {
            return false;
        }
}
    //    修改博客 接口
    @RequestMapping("/api/Admin/upadateBlog")
    @ResponseBody
    public Object upadateBlog(String title,String description,String image,String content,Integer isdel,String id) {
        String sql = "UPDATE article set title=?,description=?,image=?,content=?,isdel=? where id = ?";
        int temp = jdbcTemplate.update(sql,new Object[]{title,description,image,content,isdel,id});
        if (temp > 0) {
            return true;
        }else {
            return false;
        }
    }

 //博客 上架/下架
 @RequestMapping("/Admin/shelves")
 @ResponseBody
 public Object shelves(Integer isdel,String id) {
     String sql = "update article set isdel = ? WHERE id=?";
     int temp = jdbcTemplate.update(sql,new Object[]{isdel,id});
     if (temp > 0) {
         return true;
     }else {
         return false;
     }
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
