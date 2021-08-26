package com.eale.spi.factory;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * 反射工厂--> 加载
 *
 * @Author HLD
 * @Date 2021/8/26 0026
 **/
public class ReflectFactory {


    @Autowired
    ApplicationContext context;

    private static final Logger logger = LoggerFactory.getLogger(ReflectFactory.class);

    public Class<?> getReflectClass(String filePath,String key){

        ClassLoader loader = ClassLoader.getSystemClassLoader();

        Class<?> c = null;
        try {
            c = Class.forName(key, false, loader);
        } catch (ClassNotFoundException x) {

        }

        return c;
    }

}
