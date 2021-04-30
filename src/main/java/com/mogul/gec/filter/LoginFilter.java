package com.mogul.gec.filter;

import com.mogul.gec.pojo.UserInf;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        httpServletRequest.setCharacterEncoding("UTF-8");
        UserInf user= (UserInf) httpServletRequest.getSession().getAttribute("nowUser");
        String url=httpServletRequest.getServletPath();
        //放行数组
        List<String> urls=new ArrayList<>();
        urls.add("/Manager_login.jsp");
        urls.add("/ManagerLogin");
        urls.add("/Number.jsp");

        if(urls.contains(url)||url.startsWith("/assets")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(user==null){
            httpServletResponse.sendRedirect("Manager_login.jsp");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

}
