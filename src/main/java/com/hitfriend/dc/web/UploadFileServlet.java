package com.hitfriend.dc.web;

import com.hitfriend.dc.util.V;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/media/upload")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] params={"desc","file_type"};
        V.vaild(req,params);
        Part part = req.getPart("file");
        String type = part.getContentType();
        long size = part.getSize();
        String name = part.getName();
        String submitName = part.getSubmittedFileName();
        String dirPath = "upload_file/images";
        String baseDir = this.getServletContext().getRealPath(dirPath);
        //如果不存在，就创建文件夹
        File file = new File(baseDir);
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName = Long.toHexString(System.currentTimeMillis());
        if(type.endsWith("jpeg")){
            fileName = fileName + ".jpg";
        }else {
            return;
        }
        baseDir += fileName;

        part.write(baseDir);

        System.out.println(dirPath+fileName);
    }
}
