package com.hitfriend.dc.service;

import com.hitfriend.dc.po.Media;

import javax.servlet.http.Part;
import java.io.IOException;

public interface FileService {
    String uploadFile(String path, Part part, Media media)throws IOException;
}
