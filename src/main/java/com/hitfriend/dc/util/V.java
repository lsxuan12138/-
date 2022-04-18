package com.hitfriend.dc.util;

import com.hitfriend.dc.exception.InvalidParamException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//验证
public class V {
    /*
    * 验证字段是否为空
    * */
    public static void vaild(HttpServletRequest req,String[] keys){
        for (String key : keys){
            String value = req.getParameter(key);
            if(value == null || value.length() == 0){
                throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,key + "不能为空");
            }
        }
    }
    /**
     * 自动创建实体对象
     * @param req
     * @param cls
     * @param keys
     * @return
     */
    public static <T> T entity(HttpServletRequest req, Class<?> cls, String[] keys) {
        T instance = null;

        try {
            instance = (T)cls.newInstance();
            for(String key : keys) {
                if(req.getParameter(key) ==null) continue;
                String value = req.getParameter(key);
                String param = NameUtil.convert2Caml(key);
                //获取指定字段
                Field field = cls.getDeclaredField(param);
                param = NameUtil.firstUpper(param);
                String optName = "set" + param;

                //获取字段的数据类型
                Class<?> dataType = field.getType();
                String typeName = dataType.getTypeName();
                Object setValue = null;

                if(typeName.equals("java.lang.Integer") || typeName.equals("int")) {
                    setValue = Integer.parseInt(value);
                } else if(typeName.equals("java.lang.Long") || typeName.equals("long")) {
                    setValue = Long.parseLong(value);
                } else if(typeName.equals("java.lang.Double") || typeName.equals("double")) {
                    setValue = Double.parseDouble(value);
                } else if(typeName.equals("java.lang.String")) {
                    setValue = value;
                } else if(typeName.equals("java.util.Date")) {
                    //TODO 转化为日期类型
                } else {
                    setValue = dataType.cast(value);
                }

                Method method = cls.getMethod(optName, dataType);
                method.invoke(instance, setValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE, "实例化参数失败");
        }
        return instance;
    }

}
