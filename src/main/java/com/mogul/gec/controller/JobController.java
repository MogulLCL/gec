package com.mogul.gec.controller;

import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.service.DeptService;
import com.mogul.gec.service.JobService;
import com.mogul.gec.service.impl.DeptServiceImpl;
import com.mogul.gec.service.impl.JobServiceImpl;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/job/list","/job/add","/job/update","/job/delete","/job/get"})
public class JobController extends HttpServlet {

    private JobService jobService=new JobServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        if(url.equals("/job/list")){
            list(req,resp);
        }else if(url.equals("/job/add")){
            add(req,resp);
        }else if(url.equals("/job/update")){
            update(req,resp);
        }else if(url.equals("/job/delete")){
            delete(req,resp);
        }else if(url.equals("/job/get")){
            get(req,resp);
        }
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        JobInf jobInf = jobService.getJob(new Long(id));
        req.setAttribute("updateJob",jobInf);
        req.getRequestDispatcher("/jsp/Job_update.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if(id!=null) jobService.deleteJob(new Long(id));
        resp.sendRedirect("/job/list");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name=req.getParameter("name");
        String remark=req.getParameter("remark");
        JobInf jobInf=JobInf.builder()
                .id(new Long(id))
                .name(name)
                .remark(remark)
                .build();
        jobService.updateJob(jobInf);
        resp.sendRedirect("/job/list");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limti=req.getParameter("pageNo");
        String name=req.getParameter("name");
        QueryVo queryVo=new QueryVo();
        queryVo.setSize(10);
        queryVo.setKeyWords(name==null?"":name);
        queryVo.setLimit(limti!=null?Integer.parseInt(limti):1);
        Page page = jobService.getJobList(queryVo);
        req.setAttribute("pageInfo",page);
        req.getRequestDispatcher("/jsp/Job_index.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name=req.getParameter("name");
        String remark=req.getParameter("remark");
        JobInf jobInf=JobInf.builder()
                .name(name)
                .remark(remark)
                .build();
        jobService.addJob(jobInf);
        resp.sendRedirect("/job/list");
    }
}
