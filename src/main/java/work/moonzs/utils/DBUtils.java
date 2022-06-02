package work.moonzs.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Moondust月尘
 * <p>
 * 用于对数据库的操作工具类
 */
public class DBUtils {
    /**
     * 获取数据库连接
     *
     * @return xxx
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException {
        InputStream is = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        Class.forName(driverClass);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库以及statement连接，preparStatement接口是statement的子接口
     * @param conn xxx
     * @param smt xxx
     */
    public static void closeResource(Connection conn, Statement smt) {
        if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        if (smt != null) {
            try {
                smt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭数据集资源
     * @param conn xxx
     * @param smt xxx
     * @param rs xxx
     */
    public static void closeResource(Connection conn, Statement smt, ResultSet rs) {
        closeResource(conn, smt);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
