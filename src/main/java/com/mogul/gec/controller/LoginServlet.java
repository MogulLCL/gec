package com.mogul.gec.controller;

import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.service.AnnouncementService;
import com.mogul.gec.service.UserService;
import com.mogul.gec.service.impl.AnnouncementServiceImpl;
import com.mogul.gec.service.impl.UserServiceImpl;
import com.mogul.gec.utils.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/ManagerLogin","/logout"})
public class LoginServlet extends HttpServlet {

    private AnnouncementService announcementService=new AnnouncementServiceImpl();
    private UserService userService=new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        if(url.equals("/ManagerLogin")){
            login(req,resp);
        }else if(url.equals("/logout")){
            logout(req,resp);
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("nowUser");
        resp.sendRedirect("Manager_login.jsp");
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        String code= (String) session.getAttribute("Code");
        String codename=req.getParameter("Checkcode");
        if(!code.equals(codename)){
            req.setAttribute("message","验证码错误");
            req.getRequestDispatcher("Manager_login.jsp").forward(req, resp);
            return;
        }
        String username = req.getParameter("loginname");
        String password = req.getParameter("password");
        UserInf user = userService.loginUser(username);
        if(user!=null&&user.getPassword().equals(password)){
            Page announcements = announcementService.getAnnouncements(1, 10);
            session.setAttribute("nowUser",user);
            resp.sendRedirect("/Announcement/list");
        }else {
            req.setAttribute("message","用户或密码错误");
            req.getRequestDispatcher("Manager_login.jsp").forward(req, resp);
        }
    }
}
