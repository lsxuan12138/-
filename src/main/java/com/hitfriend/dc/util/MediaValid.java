package com.hitfriend.dc.util;

import com.hitfriend.dc.exception.InvalidParamException;

import javax.servlet.http.Part;

public class MediaValid {
    private static final String[] IMAGE_TYPES = {"jpeg","gif","png","bmp"};
    private static final int IMAGE_LIMIT = 1024*1024;
    private static final String[] AUDIO_TYPES = {"mp3"};
    private static final int AUDIO_LIMIT = 1024*1024 * 30;
    private static final String[] VIDEO_TYPES = {"mp4","avi","wmv"};
    private static final int VIDEO_LIMIT = 1024*1024*300;

    public void valid(Part part, int type ){
        String[] formats =null;
        int limitSize = 0;
        if(type == Global.MEDIA_IMAGE){
            formats = IMAGE_TYPES;
            limitSize = IMAGE_LIMIT;
        }else if(type==Global.MEDIA_AUDIO){
            formats = AUDIO_TYPES;
            limitSize = AUDIO_LIMIT;
        }else if(type==Global.MEDIA_VIDEO){
            formats = VIDEO_TYPES;
            limitSize = VIDEO_LIMIT;
        }
        //验证文件格式
        String fileType = part.getContentType();
        boolean isValidFormat = false;
        for(String format : formats){
            if(fileType.endsWith(format)){
                isValidFormat = true;
                break;
            }
        }

        if (!isValidFormat){
            throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,"上传文件不合法");
        }
        //验证文件大小
        long size = part.getSize();
        if(size>limitSize){
            throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,"上传文件超过" + (limitSize/1024/1024) + "M");
        }
    }
}
