package com.hitfriend.dc.util;

import com.hitfriend.dc.exception.InvalidParamException;

import javax.servlet.http.HttpServletRequest;

public class GetOpenidUtil {
    public static String[] getOpenid(HttpServletRequest req){
        String openidAndSessionid = OpenidAndSessionidUtil.getOpenid(req.getParameter("code"));
        //String openid ="{\"session_key\":\"2GdG+t6ad78XpvV14NvRgQ==\",\"openid\":\"o__Mb45mcemfCRo1jGqq1-czzNDs\"}";
        String[] array= openidAndSessionid.split("\"");
        if("errcode".equals(array[1])) throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,"微信接口错误");
        return array;
    }
}
