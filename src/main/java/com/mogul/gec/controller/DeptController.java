package com.mogul.gec.controller;

import com.mogul.gec.pojo.AnnouncementInfo;
import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.service.DeptService;
import com.mogul.gec.service.impl.DeptServiceImpl;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;
import com.mogul.gec.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/dept/list","/dept/add","/dept/delete","/dept/update","/dept/get"})
public class DeptController extends HttpServlet {

    private DeptService deptService=new DeptServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        if(url.equals("/dept/list")){
            list(req,resp);
        }else if(url.equals("/dept/add")){
            add(req,resp);
        }else if(url.equals("/dept/update")){
            update(req,resp);
        }else if(url.equals("/dept/delete")){
            delete(req,resp);
        }else if(url.equals("/dept/get")){
            get(req,resp);
        }
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        DeptInf deptInf = deptService.getDept(new Long(id));
        req.setAttribute("updateDept",deptInf);
        req.getRequestDispatcher("/jsp/Dept_update.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if(id!=null) deptService.deleteDept(new Long(id));
        resp.sendRedirect("/dept/list");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name=req.getParameter("name");
        String remark=req.getParameter("remark");
        DeptInf deptInf=DeptInf.builder()
                .id(new Long(id))
                .name(name)
                .remark(remark)
                .build();
        deptService.updateDept(deptInf);
        resp.sendRedirect("/dept/list");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limti=req.getParameter("pageNo");
        String name=req.getParameter("name");
        QueryVo queryVo=new QueryVo();
        queryVo.setSize(10);
        if(name==null)queryVo.setKeyWords("");
        queryVo.setLimit(limti!=null?Integer.parseInt(limti):1);
        Page page = deptService.getDeptList(queryVo);
        req.setAttribute("pageInfo",page);
        req.getRequestDispatcher("/jsp/Dept_index.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name=req.getParameter("name");
        String remark=req.getParameter("remark");
        DeptInf deptInf=DeptInf.builder()
                .name(name)
                .remark(remark)
                .build();
        deptService.addDept(deptInf);
        resp.sendRedirect("/dept/list");
    }


}
