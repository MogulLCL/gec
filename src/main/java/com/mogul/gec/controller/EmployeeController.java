package com.mogul.gec.controller;

import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.EmployeeInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.service.EmployeeService;
import com.mogul.gec.service.impl.EmployeeServiceImpl;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.EmployeeVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/employee/list","/employee/add","/employee/update","/employee/delete","/employee/get"})
public class EmployeeController extends HttpServlet {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        if(url.equals("/employee/list")){
            list(req,resp);
        }else if(url.equals("/employee/add")){
            add(req,resp);
        }else if(url.equals("/employee/update")){
            update(req,resp);
        }else if(url.equals("/employee/delete")){
            delete(req,resp);
        }else if(url.equals("/employee/get")){
            get(req,resp);
        }
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        EmployeeInf jobInf = employeeService.getEmployee(new Long(id));
        req.setAttribute("updateEmp",jobInf);
        req.getRequestDispatcher("/jsp/Emp_update.jsp").forward(req,resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if(id!=null) {
            long empId=new Long(id);
            String image =employeeService.getEmployee(empId).getImgname();
            employeeService.deleteEmployee(new Long(id));
            if(image!=null&&!image.equals("")) {
                String url = req.getServletContext().getRealPath("/photo");
                new File(url + "/" + image).delete();
            }
        }
        resp.sendRedirect("/employee/list");
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name=req.getParameter("name");
        String phone=req.getParameter("phone");
        String deptId=req.getParameter("deptId");
        String jobId=req.getParameter("jobId");
        String email=req.getParameter("email");
        String education=req.getParameter("education");
        System.out.println(deptId);
        System.out.println(jobId);
        EmployeeInf employeeInf=EmployeeInf.builder()
                .id(new Long(id))
                .name(name)
                .phone(phone)
                .deptName(deptId)
                .jobName(jobId)
                .email(email)
                .education(education)
                .build();
        employeeService.updateEmployee(employeeInf);
        resp.sendRedirect("/employee/list");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limti=req.getParameter("pageNo");
        String name=req.getParameter("name");
        String deptId=req.getParameter("deptId");
        System.out.println(deptId);
        EmployeeVo employeeVo=EmployeeVo.builder()
                .size(10)
                .limit(limti!=null?Integer.parseInt(limti):1)
                .name(name==null?"":name)
                .deptId(deptId==null||"0".equals(deptId)?"":deptId)
                .build();
        Page page = employeeService.getEmployeeList(employeeVo);
        req.setAttribute("pageInfo",page);
        req.getRequestDispatcher("/jsp/Employee_index.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String cardId=req.getParameter("cardId");
        String party=req.getParameter("party");
        String education=req.getParameter("education");
        String race=req.getParameter("race");
        String phone=req.getParameter("phone");
        String email=req.getParameter("email");
        String sex=req.getParameter("sex");
        String deptId=req.getParameter("deptId");
        String jobId=req.getParameter("jobId");
        Part part = req.getPart("file");
        String url=req.getServletContext().getRealPath("/photo");
        String headerInfo = part.getHeader("content-disposition");
        String time=new Date().getTime()+"_";
        String fileName = new Date().getTime() + "_" + headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);
        EmployeeInf employeeInf=EmployeeInf.builder()
                .name(name)
                .password(password)
                .cardId(cardId)
                .party(party)
                .education(education)
                .race(race)
                .phone(phone)
                .email(email)
                .sex(new Long(sex))
                .deptName(deptId)
                .jobName(jobId)
                .imgname("".equals(fileName)?"":time+fileName)
                .build();
        if("".equals(fileName)) {
            File file = new File(url);
            if (!file.exists()) file.mkdirs();
            part.write(url + "/" + time + fileName);
        }
        new Date();
        employeeService.addEmployee(employeeInf);
        resp.sendRedirect("/employee/list");
    }
}
