package work.moonzs.dao;

import work.moonzs.pojo.Salesman;
import work.moonzs.utils.SqlUtils;

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
    public void insertData(Salesman salesman) {
        try {
            Connection conn = SqlUtils.getConnection();
            String sql = "INSERT INTO salesman values(?,?,?)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, salesman.getSid());
            psmt.setString(2, salesman.getSname());
            psmt.setString(3, salesman.getSpassword());
            psmt.executeUpdate();
            SqlUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
