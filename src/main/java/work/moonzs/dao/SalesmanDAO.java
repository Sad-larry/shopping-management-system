package work.moonzs.dao;

import work.moonzs.pojo.Salesman;
import work.moonzs.utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO salesman values(?,?,?)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, salesman.getSid());
            psmt.setString(2, salesman.getSname());
            psmt.setString(3, salesman.getSpassword());
            psmt.executeUpdate();
            DBUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户，传入的是一个销售员对象
     *
     * @param salesman xxx
     */
    public void querySalesman(Salesman salesman) {
        String sql = "SELECT * FROM salesman";
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.execute();
            DBUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
