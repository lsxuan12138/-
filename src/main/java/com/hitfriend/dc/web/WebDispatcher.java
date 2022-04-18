package com.hitfriend.dc.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitfriend.dc.exception.BaseException;
import com.hitfriend.dc.exception.InvalidParamException;
import com.hitfriend.dc.privilege.LoginFilter;
import com.hitfriend.dc.privilege.PrivilegeFilter;
import com.hitfriend.dc.privilege.WhiteFilter;
import com.hitfriend.dc.util.E;
import com.hitfriend.dc.util.Global;
import com.hitfriend.dc.util.NameUtil;
import org.apache.ibatis.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;


/**
 * 程序分发器
 * 约定使用  /项目名/类目/方法名/  这种格式的访问方式
 * */
@WebServlet("/app/*")
@MultipartConfig
public class WebDispatcher extends HttpServlet {
    public static final String PROJECT_NAME = "/hitfriend_war_exploded/app/";
    private static final String PACKAGE_NAME = "com.hitfriend.dc.controller";
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Logger log = LogManager.getLogger(WebDispatcher.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Access-Control-Allow-Origin","*");
        //resp.setContentType("application/json");
        //获取用户请求地址
        String uri = req.getRequestURI();

        //System.out.println(uri);
        //打印日志 请求路径及参数
        log.info(req.getRemoteHost() + "请求地址" + uri);
        Enumeration<String> paramKeys = req.getParameterNames();
        while (paramKeys.hasMoreElements()){
            String key = paramKeys.nextElement();
            log.info("请求参数" + key + "--->"+req.getParameter(key));
        }
        //去掉项目名称
        uri = uri.replace(PROJECT_NAME,"");
        //存储请求路径到request对象中
        req.setAttribute(Global.REQ_PATH,uri);
        //验证
        MyFilterChain chain = new MyFilterChain();
        //白名单验证
        MyFilter whiteFilter = new WhiteFilter();
        //登录验证
        MyFilter loginFilter = new LoginFilter();
        //权限验证
        MyFilter privilegeFilter = new PrivilegeFilter();
        chain.addFilter(whiteFilter).addFilter(loginFilter).addFilter(privilegeFilter);

        ApiResult apiResult = new ApiResult();
        try {
            //打开验证链
            chain.doFilter(req, resp);

//            Enumeration<String> headers = req.getHeaderNames();
//            log.info("header-------------------------------");
//            while(headers.hasMoreElements()){
//                String key = headers.nextElement();
//                log.info(key + ":" + req.getHeader(key));
//            }
//            log.info("header-------------------------------");
            if(req.getHeader("content-type")!=null && req.getHeader("content-type").startsWith("multipart/form-data")){
                if(req.getPart("file") !=null){
                    //System.out.println(req.getPart("file"));
                String uploadDir =this.getServletContext().getRealPath("/");
                log.info("设置文件上传路径" + uploadDir);
                req.setAttribute("upload_path", uploadDir);
            }
            }
            //如果是多媒体类型，设置上传目录的路径
//

            String[] reqUri = uri.split("/");
            //判断请求地址是否合法
            if(reqUri.length<2)throw new InvalidParamException(E.INVALID_PARAM_ERROR_CODE,E.INVALID_PARAM_ERROR_INFO);

            String cat = reqUri[0];//操作对象--》 类名
            String opt = reqUri[1];//具体操作--》方法名及参数
            //名称转换 将abc_abc转换成abcAbc形式
            cat = NameUtil.convert2Caml(cat);
            cat = NameUtil.firstUpper(cat);
            opt = NameUtil.convert2Caml(opt);

            //类名
            String catName = PACKAGE_NAME + "." + cat + "Controller";
            //获取指定类的 类对象
            Class<?> clx = Class.forName(catName);
            //创建对象实例
            Object instance = clx.newInstance();
            //获取要调用的方法
            Method method = clx.getMethod(opt,HttpServletRequest.class,HttpServletResponse.class);
            Object obj = method.invoke(instance,req,resp);
            if(obj != null) apiResult.setData(obj);
        } catch (InvocationTargetException e) {
            Throwable targetE = e.getTargetException();
            if(targetE instanceof BaseException){
                BaseException srcException = (BaseException) targetE;
                apiResult.setCode(srcException.getCode());
                apiResult.setMsg(srcException.getMessage());
            }else{
                e.printStackTrace();
                apiResult.setCode(500);
                apiResult.setMsg("程序内部错误");//检查数据库是否打开
            }
        }catch (ReflectiveOperationException e) {
            e.printStackTrace();
            apiResult.setCode(E.REQ_PATH_ERROR_CODE);
            apiResult.setMsg(E.REP_PATH_ERROR_INFO);
        }catch (BaseException e) {
            //e.printStackTrace();
            apiResult.setCode(e.getCode());
            apiResult.setMsg(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            apiResult.setCode(500);
            apiResult.setMsg("程序内部错误");
        }
        String rsStr = objectMapper.writeValueAsString(apiResult);


        log.info("返回数据" + rsStr);
        resp.getWriter().write(rsStr);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
