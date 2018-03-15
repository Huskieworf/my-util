package com.homework1.util;

import java.sql.*;

public class MySqlUtil {
    /**
     * 1加载驱动  只需要加载一次
     * 2获取连接
     * 3创建预处理器
     * 4执行增删改查
     * 5关闭
     */

    static {
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/web", "root", "yifan520");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 关闭资源
     * @param rs
     * @param pst
     * @param conn
     */
    public static void close(ResultSet rs, PreparedStatement pst, Connection conn) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
