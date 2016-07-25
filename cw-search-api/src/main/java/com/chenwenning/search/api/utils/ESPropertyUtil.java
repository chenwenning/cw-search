package com.chenwenning.search.api.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by chenwenning on 2016/7/9.
 */
public class ESPropertyUtil {
    private final static String PROPERTIES_FILE = "elasticsearch.properties";

    private static Properties properties = new Properties();

    static {
        initProperties();
    }

    public static Properties initProperties(){
        try {
            properties.load(ESPropertyUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperties(String name,String sdefault){
        return properties.getProperty(name,sdefault);
    }

    public static void main(String[] args){
        System.out.print(ESPropertyUtil.getProperties("transportAddresses",""));

    }
}
