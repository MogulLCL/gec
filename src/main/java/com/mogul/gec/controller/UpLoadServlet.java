package com.mogul.gec.controller;


import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.service.DocumentService;
import com.mogul.gec.service.impl.DocumentServiceImpl;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;

@MultipartConfig
@WebServlet(urlPatterns = {"/upload/list","/upload/add","/upload/update","/upload/delete","/upload/get","/upload/dow"})
public class UpLoadServlet extends HttpServlet {

    private DocumentService documentService=new DocumentServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        if(url.equals("/upload/list")){
            list(req,resp);
        }else if(url.equals("/upload/add")){
            add(req,resp);
        }else if(url.equals("/upload/update")){
            update(req,resp);
        }else if(url.equals("/upload/delete")){
            delete(req,resp);
        }else if(url.equals("/upload/get")){
            get(req,resp);
        }else if(url.equals("/upload/dow")){
            dow(req,resp);
        }
    }

    protected void dow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        DocumentInf documentInf = documentService.getDocumentInfo(new Long(id));
        if (documentInf != null) {
            String url=req.getServletContext().getRealPath("/upload");
            File file=new File(url+"/"+documentInf.getFilename());
            try (InputStream inputStream = new FileInputStream(file);
                 ServletOutputStream outputStream = resp.getOutputStream();) {
                byte[] a = new byte[(int) file.length()];
                inputStream.read(a);
                resp.setHeader("Content-Disposition", "attachment; filename=" + documentInf.getFilename() + "");
                outputStream.write(a);
                outputStream.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        DocumentInf documentInf = documentService.getDocumentInfo(new Long(id));
        req.setAttribute("updateDocument",documentInf);
        req.getRequestDispatcher("/jsp/Document_update.jsp").forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String remark=req.getParameter("remark");
        Part part=req.getPart("filename");
        String headerInfo = part.getHeader("content-disposition");
        String time=new Date().getTime()+"_";
        String filename = headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);
        UserInf userInf= (UserInf) req.getSession().getAttribute("nowUser");
        if(id!=null) {
            long docId=new Long(id);
            String image =documentService.getDocumentInfo(docId).getFilename();
            DocumentInf documentInf=new DocumentInf();
            documentInf.setId(docId);
            documentInf.setUserId(userInf.getId());
            documentInf.setFilename("".equals(filename)?image:time+filename);
            documentInf.setRemark(remark);
            if(!"".equals(filename)) {
                String url = req.getServletContext().getRealPath("/upload");
                new File(url + "/" + image).delete();
                File file = new File(url);
                if (!file.exists()) file.mkdirs();
                part.write(url + "/" + time +filename);
            }
            documentService.updateDocumentInfo(documentInf);
        }
        resp.sendRedirect("/upload/list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if(id!=null) {
            long docId=new Long(id);
            String image =documentService.getDocumentInfo(docId).getFilename();
            documentService.delete(docId);
            if(image!=null&&!image.equals("")) {
                String url = req.getServletContext().getRealPath("/upload");
                new File(url + "/" + image).delete();
            }
        }
        resp.sendRedirect("/upload/list");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String remark=req.getParameter("remark");
        Part part = req.getPart("file");
        String url=req.getServletContext().getRealPath("/upload");
        String headerInfo = part.getHeader("content-disposition");
        String time=new Date().getTime()+"_";
        String fileName = headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);
        UserInf userInf= (UserInf) req.getSession().getAttribute("nowUser");
        if(!"".equals(fileName)) {
            DocumentInf documentInf = new DocumentInf();
            documentInf.setUserId(userInf.getId());
            documentInf.setRemark(remark);
            documentInf.setFilename(time+fileName);
            File file = new File(url);
            if (!file.exists()) file.mkdirs();
            part.write(url + "/" + time +fileName);
            documentService.addDocumentInfo(documentInf);
        }
        resp.sendRedirect("/upload/list");
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String limti=req.getParameter("pageNo");
        String filename=req.getParameter("filename");
        QueryVo documentVo=new QueryVo();
        documentVo.setSize(10);
        documentVo.setKeyWords(filename==null?"":filename);
        documentVo.setLimit(limti==null?1:Integer.parseInt(limti));
        Page page=documentService.getDocumentList(documentVo);
        req.setAttribute("pageInfo",page);
        req.getRequestDispatcher("/jsp/Document_index.jsp").forward(req,resp);
    }
}
