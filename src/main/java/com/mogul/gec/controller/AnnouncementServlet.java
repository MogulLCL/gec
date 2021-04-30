package com.mogul.gec.controller;

import com.mogul.gec.pojo.AnnouncementInfo;
import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.service.AnnouncementService;
import com.mogul.gec.service.impl.AnnouncementServiceImpl;
import com.mogul.gec.utils.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Announcement/list","/Announcement/add","/Announcement/delete","/Announcement/update","/Announcement/get"})
public class AnnouncementServlet extends HttpServlet {
    private AnnouncementService announcementService=new AnnouncementServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        if(url.equals("/Announcement/list")){
            list(req,resp);
        }else if(url.equals("/Announcement/add")){
            add(req,resp);
        }else if(url.equals("/Announcement/update")){
            update(req,resp);
        }else if(url.equals("/Announcement/delete")){
            delete(req,resp);
        }else if(url.equals("/Announcement/get")){
            get(req,resp);
        }
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        AnnouncementInfo announcementInfo = announcementService.getAnnouncement(new Long(id));
        req.setAttribute("updateAnnouncement",announcementInfo);
        req.getRequestDispatcher("/jsp/Announcement_update.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if(id!=null) announcementService.delete(new Long(id));
        resp.sendRedirect("/Announcement/list");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        UserInf userInf= (UserInf) req.getSession().getAttribute("nowUser");
        AnnouncementInfo announcementInfo=new AnnouncementInfo();
        announcementInfo.setId(new Long(id));
        announcementInfo.setTitle(title);
        announcementInfo.setContent(content);
        announcementInfo.setUid(userInf.getId());
        announcementService.updateAnnouncement(announcementInfo);
        resp.sendRedirect("/Announcement/list");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        AnnouncementInfo announcementInfo=new AnnouncementInfo();
        announcementInfo.setTitle(title);
        announcementInfo.setContent(content);
        UserInf userInf= (UserInf) req.getSession().getAttribute("nowUser");
        announcementInfo.setUid(userInf.getId());
        announcementService.addAnnouncement(announcementInfo);
        resp.sendRedirect("/Announcement/list");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limt=req.getParameter("pageNo");
        Page page = announcementService.getAnnouncements(limt==null?1:Integer.parseInt(limt),10);
        req.setAttribute("pageInfo",page);
        req.getRequestDispatcher("/jsp/Announcement_index.jsp").forward(req,resp);
    }
}
