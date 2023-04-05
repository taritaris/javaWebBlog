package com.taritari.blog.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author taritari
 * @date 2023-04-04 21:09
 * @description
 */
public class CurdUtil {
    private void setParams(PreparedStatement ps, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
        }
    }
    public List<Map<String, Object>> queryForList(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MysqlUtil mysqlUtil = new MysqlUtil();
        try {
            conn = mysqlUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            rs = pstmt.executeQuery();
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                resultList.add(map);
            }
            return resultList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            mysqlUtil.closeConnection(conn);
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
   * curd
   * */
    public int execute(String sql, Object[] params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        MysqlUtil mysqlUtil = new MysqlUtil();
        int result = 0;
        try {
            conn = mysqlUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            setParams(pstmt, params);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            mysqlUtil.closeConnection(conn);
        }
        return result;
    }
    public static void main(String[] args) {
        CurdUtil curdUtil = new CurdUtil();
        List<Map<String, Object>> maps = curdUtil.queryForList("select * from blog_user",null);
        System.out.println(maps);
//        String sql = "INSERT INTO blog_user(number, username,password,birthday,message,title_img_path) VALUES(?, ?, ?, ?, ?, ?)";
//        Object[] params = {"12312312","李四","123456","2022-01-05","admin","/123"};
//        int execute = curdUtil.execute(sql, params);
//        System.out.println(execute);

    }
}
