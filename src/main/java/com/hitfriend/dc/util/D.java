package com.hitfriend.dc.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.io.InputStream;

/**
 * 数据库工具类
 * */
public class D {
    private static SqlSessionFactory sqlSessionFactory;
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    private static Logger log = LogManager.getLogger(D.class);
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("mybatis配置文件读取失败");
            throw new RuntimeException("mybatis配置文件读取失败");

        }
        sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
    }
    /**
     * 创建数据库连接
     */
    public static SqlSession getConn() {
        SqlSession sqlSession = threadLocal.get();
        if(sqlSession == null){
            sqlSession = sqlSessionFactory.openSession();
            threadLocal.set(sqlSession);
        }
        log.info("创建数据库连接" + sqlSession);
        return  sqlSession;
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConn(){
        SqlSession sqlSession = threadLocal.get();
        log.info("关闭数据库连接" + sqlSession);
        if(sqlSession != null){
            //sqlSession = sqlSessionFactory.openSession();
            sqlSession.commit();
            sqlSession.close();
            threadLocal.remove();
        }
    }

//    public static void main(String[] args) throws IOException {
//        System.out.println(D.getConn());
//    }
}
