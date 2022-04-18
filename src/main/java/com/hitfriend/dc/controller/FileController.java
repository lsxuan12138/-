package com.hitfriend.dc.controller;

import com.hitfriend.dc.exception.InvalidParamException;
import com.hitfriend.dc.po.Media;
import com.hitfriend.dc.po.User;
import com.hitfriend.dc.service.FileService;
import com.hitfriend.dc.service.FileServiceImpl;
import com.hitfriend.dc.util.E;
import com.hitfriend.dc.util.SessionManager;
import com.hitfriend.dc.util.V;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

public class FileController {
    private FileService fileService;

    public FileController(){
        this.fileService=new FileServiceImpl();
    }
    public String upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] params={"file_desc","type"};
        V.vaild(req,params);
        Media media = V.entity(req,Media.class,params);
        String token = req.getParameter("token");
        //System.out.println(token);
        HttpSession session = SessionManager.getSession(token);
        User user = (User) session.getAttribute("userInfo");
        media.setUser(user);
       //User user = (User) req.getSession().getAttribute("userInfo");
        String path = (String) req.getAttribute("upload_path");
        Part part = req.getPart("file");
        if(part == null){
            throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,E.INVALID_PARAM_ERROR_INFO);
        }
        String filepath = fileService.uploadFile(path,part,media);
        return filepath;
    }
}
