package com.hitfriend.dc.service;

import com.hitfriend.dc.exception.InvalidParamException;
import com.hitfriend.dc.mapper.MediaMapper;
import com.hitfriend.dc.po.Media;
import com.hitfriend.dc.util.D;
import com.hitfriend.dc.util.E;
import com.hitfriend.dc.util.Global;
import com.hitfriend.dc.util.MediaValid;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class FileServiceImpl implements FileService{
    //public static final String UPLOAD_FILE_DIR_PATH = "/upload_files/";
    public static final String IMAGE_PATH = "/upload_files/images/";
    public static final String AUDIO_PATH = "/upload_files/audios/";
    public static final String VIDEO_PATH = "/upload_files/videos/";

    private MediaMapper mediaMapper;
    public FileServiceImpl(){
        SqlSession session = D.getConn();
        mediaMapper = session.getMapper(MediaMapper.class);
    }
    @Override
    public String uploadFile(String path, Part part, Media media) throws IOException {
        String uploadDir = path;
        String fileName = Long.toHexString(System.currentTimeMillis());
        String returnPath =null;

        if(media.getType()== Global.MEDIA_IMAGE){
            uploadDir += IMAGE_PATH;
            returnPath = IMAGE_PATH;
        }else if(media.getType()==Global.MEDIA_AUDIO){
            uploadDir += AUDIO_PATH;
            returnPath = AUDIO_PATH;
        }else if(media.getType()==Global.MEDIA_VIDEO){
            uploadDir += VIDEO_PATH;
            returnPath = VIDEO_PATH;
        }else {
            throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,"不支持的文件类型");
        }

        //判断目录是否存在，若不存在则创建
        File file = new File(uploadDir);
        if(!file.exists()){
            file.mkdirs();
        }

        //验证格式和大小
        MediaValid mediaValid = new MediaValid();
        mediaValid.valid(part,media.getType());
        //获取文件后缀
        String orgName = part.getSubmittedFileName();
        String sufix  = orgName.substring(orgName.lastIndexOf("."),orgName.length());

        uploadDir += (fileName+sufix);
        //返回给用户的路径
        returnPath +=(fileName+sufix);

        media.setUrl(returnPath);


        part.write(uploadDir);

        mediaMapper.insert(media);
        return returnPath;
    }
}
