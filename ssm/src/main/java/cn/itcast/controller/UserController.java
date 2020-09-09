package cn.itcast.controller;

import cn.itcast.domain.User;

import cn.itcast.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/action")
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping("/login")
    public void   login(Model model, User user , HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        boolean flag = userService.findByOne(user);

        if (flag == true) {
            response.getWriter().write("true");
        }else {
            response.getWriter().write("false");
        }


    }
    @RequestMapping("/enroll")
    public void enroll(String username,String password,String rePassword,HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
        boolean flag = userService.findByname(username);
        if (flag == true){
            response.getWriter().write("false");

        }
        if (flag == false){
             if (password.equals(rePassword)){
                  User user = new User();
                  user.setPassword(password);
                  user.setUsername(username);
                  userService.saveUser(user);
                 response.getWriter().write("true");
            }else {
                 response.getWriter().write("password not rePassword");

            }
        }
    }

}


