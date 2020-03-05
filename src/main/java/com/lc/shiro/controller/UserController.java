package com.lc.shiro.controller;

import com.lc.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {
    private UserService service;;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/th")
    public String th(Model model){
        model.addAttribute("tt","c测试");
        model.addAttribute("update","update");
        model.addAttribute("add","add");
        return "th";
    }
    @RequestMapping("/update")
    public String update(Model model){
        return "update";
    }
    @RequestMapping("/add")
    public String add(Model model){
        return "add";
    }

    @RequestMapping("/lo")
    public String lo(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(String name, String password, Model model){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);
        try {
            subject.login(token);
            model.addAttribute("msg","okokok");
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不对！！");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码不对！！");
            return "login";
        }
    }

    @RequestMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }

}
