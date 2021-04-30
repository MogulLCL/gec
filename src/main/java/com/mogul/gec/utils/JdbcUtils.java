package com.mogul.gec.utils;

import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

     static String url;
     static String user;
     static String password;

    static {
        Properties properties=new Properties();
        try {
            ClassLoader classLoader=JdbcUtils.class.getClassLoader();
            properties.load(classLoader.getResourceAsStream("mysql.properties"));
            Class.forName(properties.getProperty("driver"));
            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            System.out.println("获取连接失败");
        }
        return connection;
    }

}
