package com.taritari.blog.utils;

import com.taritari.blog.config.YmlConfig;

import java.sql.*;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-04 20:47
 * @description
 */
public class MysqlUtil {
    private YmlConfig ymlConfig = new YmlConfig();
    private Map<String, Object> datasource = ymlConfig.getApplication("datasource");
    private Map<String, Object> data = (Map<String, Object>)datasource.get("dev");
    private String url = data.get("url").toString();
    private String user = data.get("username").toString();
    private String password = data.get("password").toString();

    public Connection getConnection() throws SQLException {
        try {
            System.out.println(datasource);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(url, user, password);
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

