package com.online.controller;

import com.online.service.LoginService;
import com.online.service.StudentService;
import com.online.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.sql.SQLException;

@Controller
public class LoginController {


    @Autowired
    LoginService loginService;

    @Autowired
    StudentService studentService;

    /**
     * admin login:学生登录
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    String login(@RequestParam int id, @RequestParam String password, HttpServletResponse response, HttpSession session) throws IOException {

        session.setMaxInactiveInterval(3 * 60);  //设置过期时间以秒为单位，会自动刷新
        session.setAttribute("user", "student");
        session.setAttribute("id", id);

        Cookie mycookie = new Cookie("id", Integer.toString(id));
        response.addCookie(mycookie);
        Cookie lscookie = new Cookie("user", "student");
        response.addCookie(lscookie);
        password = MD5Util.getMD5(password); //md5加密

        int respons = loginService.studentlogin(id, password);
        if (respons == 1) return "table-list";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('用户名或者密码错误，请重新登录');history.go(-1);</script>");
        return null;
    }


    /**
     * admin login:管理人员登录
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    String adminlogin(@RequestParam String id, @RequestParam String password, HttpServletResponse response, HttpSession session) throws IOException {


        session.setMaxInactiveInterval(3 * 60);  //设置过期时间以秒为单位，会自动刷新
        session.setAttribute("user", "admin");
        session.setAttribute("id", id);


        Cookie mycookie = new Cookie("id", id);
        Cookie lscookie = new Cookie("user", "admin");

        mycookie.setMaxAge(30 * 24 * 60 * 60);   //存活期为一个月 30*24*60*60
        mycookie.setPath("/");
        response.addCookie(mycookie);
        response.addCookie(lscookie);


        password = MD5Util.getMD5(password); //md5加密

        int respons = loginService.adminLogin(id, password);
        if (respons == 1) return "table-list";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('用户名或者密码错误，请重新登录');history.go(-1);</script>");
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String login() {

        return "login";
    }

    @RequestMapping(value = "/adminlogin", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String adminlogin() {

        return "adminlogin";
    }


    /**
     * 注册
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    String register(@RequestParam String email, @RequestParam int id, @RequestParam String password, @RequestParam String password_2, HttpServletResponse response) throws IOException, SQLException {
        String res_url = null;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        if (!email.contains("@")) {
            out.print("<script>alert('邮箱格式不正确，请重新输入');history.go(-1);</script>");
            return res_url;
        }
        if (!password.equals(password_2)) {
            out.print("<script>alert('两次密码不一致，请重新输入');history.go(-1);</script>");
            return res_url;
        }

        password = MD5Util.getMD5(password); //md5加密
        int respons = studentService.studentRegister(id, email, password);


        if (respons == 1) return "login";

        else if (respons == 2) {
            out.print("<script>alert('此账号id已被注册，请更换id');history.go(-1);</script>");
            return res_url;

        }
        out.print("<script>alert('注册失败，请重试');history.go(-1);</script>");

        return res_url;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String register() {

        return "sign-up.html";
    }


    /**
     * 注册
     *
     * @param
     * @param password
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    String infoChange(@RequestParam int id, @RequestParam String email, @RequestParam String name, @RequestParam String password, @RequestParam String password_2, HttpServletResponse response) throws IOException, SQLException {
        String res_url = null;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        if (email != "" && !email.contains("@")) {
            out.print("<script>alert('邮箱格式不正确，请重新输入');history.go(-1);</script>");
            return res_url;
        }
        if (password != "" && password_2 != "" && password.equals(password_2)) {
            out.print("<script>alert('新旧密码一致，请重新输入');history.go(-1);</script>");
            return res_url;
        }
        if (password_2 == "") password_2 = password;
        password = MD5Util.getMD5(password_2); //md5加密
        int respons = studentService.infoChange(id, email, name, password);


        if (respons == 1) out.print("<script>alert('修改信息成功');history.go(-2);</script>");


        return res_url;
    }

}
