package com.mogul.gec.controller;

import com.mogul.gec.pojo.AnnouncementInfo;
import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.service.UserService;
import com.mogul.gec.service.impl.UserServiceImpl;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = {"/user/list","/user/add","/user/update","/user/delete","/user/get"})
@MultipartConfig
public class UserServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        if(url.equals("/user/list")){
            list(req,resp);
        }else if(url.equals("/user/add")){
            add(req,resp);
        }else if(url.equals("/user/update")){
            update(req,resp);
        }else if(url.equals("/user/delete")){
            delete(req,resp);
        }else if(url.equals("/user/get")){
            get(req,resp);
        }
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UserInf userInfo = userService.getUserInfo(new Long(id));
        req.setAttribute("updateUser",userInfo);
        req.getRequestDispatcher("/jsp/User_update.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String loginname=req.getParameter("loginname");
        String password=req.getParameter("password");
        String username=req.getParameter("username");
        String status=req.getParameter("status");
        Part part = req.getPart("file");
        String url=req.getServletContext().getRealPath("/photo");
        String headerInfo = part.getHeader("content-disposition");
        String time=new Date().getTime()+"_";
        String fileName =  headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);
        UserInf userInf = new UserInf();
        userInf.setLoginname(loginname);
        userInf.setPassword(password);
        userInf.setUsername(username);
        userInf.setStatus(status!=null?1:new Long(status));
        userInf.setImgname("".equals(fileName)?"":time+fileName);
        if(!"".equals(fileName)) {
            File file = new File(url);
            if (!file.exists()) file.mkdirs();
            part.write(url + "/" + time + fileName);
        }
        userService.addUserInfo(userInf);
        resp.sendRedirect("/user/list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if(id!=null) {
            long userId=new Long(id);
            String image =userService.getUserInfo(userId).getImgname();
            userService.delete(userId);
            if(image!=null&&!image.equals("")) {
                String url = req.getServletContext().getRealPath("/photo");
                new File(url + "/" + image).delete();
            }
        }
        resp.sendRedirect("/user/list");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String loginname=req.getParameter("loginname");
        String username=req.getParameter("username");
        String status=req.getParameter("status");
        UserInf userInf = new UserInf();
        userInf.setId(new Long(id));
        userInf.setLoginname(loginname);
        userInf.setUsername(username);
        userInf.setStatus(status!=null?1:new Long(status));
        userService.updateUserInfo(userInf);
        resp.sendRedirect("/user/list");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limti=req.getParameter("pageNo");
        String username=req.getParameter("username");
        String status=req.getParameter("status");
        UserVo userVo=new UserVo();
        userVo.setSize(10);
        userVo.setUsername(username==null?"":username);
        userVo.setStatus(status==null?"":status);
        userVo.setLimit(limti!=null?Integer.parseInt(limti):1);
        Page page = userService.getUserList(userVo);
        req.setAttribute("pageInfo",page);
        req.getRequestDispatcher("/jsp/User_index.jsp").forward(req,resp);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String limti=req.getParameter("pageNo");
//        if(limti!=null){
//            Page page=userService.getUserList(Integer.parseInt(limti),10);
//            req.setAttribute("pageInfo",page);
//            req.getRequestDispatcher("/jsp/User_index.jsp").forward(req,resp);
//        }else {
//            Page page = userService.getUserList(1,10);
//            req.setAttribute("pageInfo",page);
//            req.getRequestDispatcher("/jsp/User_index.jsp").forward(req,resp);
//        }
//    }
}
