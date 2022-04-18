package com.hitfriend.dc.web;

import com.hitfriend.dc.util.D;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ReqListen implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        D.closeConn();
    }
}
