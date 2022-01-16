package com.bobo.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Description 配置操作类
 * @Date 2022/1/16 12:21 PM
 * @Created by bobo
 */
public class PropertiesReaderUtils {
    //创建Properties对象
    private static Properties property = new Properties();

    //在静态块中加载资源
    static {
        //使用try(){}.. 获取数据源
        //注意 * 这是jdk1.7开始支持的特性，如果使用的是低版本 需要提升jdk版本 或者更改写法
        try (
                InputStream in = PropertiesReaderUtils.class.getResourceAsStream("/aliyun.properties");
        ) {
            property.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回Properties对象
     * @return
     */
    public static Properties getProperties(){
        return property;
    }

    /**
     * 获取字符串类型的值
     * @param key
     * @return
     */
    public static String get(String key) {
        return property.getProperty(key);
    }

    /**
     * 获取Integer类型的值
     * @param key
     * @return
     */
    public static Integer getInteger(String key) {
        String value = get(key);
        return null == value ? null : Integer.valueOf(value);
    }

    /**
     * 获取Boolean类型的值
     * @param key
     * @return
     */
    public static Boolean getBoolean(String key) {
        String value = get(key);
        return null == value ? null : Boolean.valueOf(value);
    }

    /**
     * 设置一个键值对
     * @param key
     * @param value
     */
    public static void set(String key,String value){
        property.setProperty(key,value);
    }

    /**
     * 添加一个键值对
     * @param key
     * @param value
     */
    public static void add(String key,Object value){
        property.put(key,value);
    }
}
