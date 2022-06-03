package work.moonzs.dao;

import work.moonzs.pojo.Salesman;
import work.moonzs.utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Moondust月尘
 * <p>
 * SalesmanDAO里面实现对SALESMAN表的操作方法
 */
public class SalesmanDAO {
    /**
     * 插入用户，传入的是一个销售员对象
     *
     * @param salesman xxx
     */
    public void insertSalesman(Salesman salesman) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO salesman values(?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, salesman.getSid());
            psmt.setString(2, salesman.getSname());
            psmt.setString(3, salesman.getSpassword());
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }

    public boolean querySalesman(Salesman salesman) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        boolean isLogin = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM salesman WHERE username=? AND password=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, salesman.getSname());
            psmt.setString(2, salesman.getSpassword());
            rs = psmt.executeQuery();
            isLogin = rs.next();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return isLogin;
    }

    /**
     * 删除用户，传入的是一个销售员对象
     *
     * @param salesman xxx
     */
    public void deleteSalesman(Salesman salesman){
        try{
            Connection conn = DBUtils.getConnection();
            String sql = "delete from salesman where sid=?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, salesman.getSid());
            psmt.executeUpdate();
            DBUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改密码，传入的是一个销售员对象
     *
     * @param salesman xxx
     */
    public void updateSalesman(Salesman salesman){
        try{
            Connection conn = DBUtils.getConnection();
            String sql = "update salesman set spassword=? where sid=?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, salesman.getSpassword());
            psmt.setInt(2, salesman.getSid());
            psmt.executeUpdate();
            DBUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
