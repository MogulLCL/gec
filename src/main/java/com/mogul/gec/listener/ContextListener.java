package com.mogul.gec.listener;

import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.service.EmployeeService;
import com.mogul.gec.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class ContextListener implements ServletContextListener {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        List<DeptInf> deptInfs=employeeService.getDepts();
        List<JobInf> jobInfs=employeeService.getJobs();
        servletContext.setAttribute("deptSelectList",deptInfs);
        servletContext.setAttribute("jobSelectList",jobInfs);
        System.out.println("web初始化成功！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
