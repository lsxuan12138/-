package com.hitfriend.dc.util;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    public static Map<String, HttpSession> sessionPool = new HashMap<String,HttpSession>();
    /**
    * 存储session
    * */
    public static void saveSession(String sessionId,HttpSession session){
        sessionPool.put(sessionId,session);
    }
    /**
    * 根据sessionId获取session
    * */
    public static HttpSession getSession(String sessionId){
        return sessionPool.get(sessionId);
    }
    /**
     * 根据sessionId删除session
     * */
    public static void removeSession(String sessionId){
        sessionPool.remove(sessionId);
    }
}
