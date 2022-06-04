package work.moonzs.dao;

import work.moonzs.pojo.Salesman;
import work.moonzs.utils.DBUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            String sql = "INSERT INTO salesman values(null,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, salesman.getSname());
            psmt.setString(2, salesman.getSpassword());
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
            String sql = "SELECT * FROM salesman WHERE sname=? AND spassword=?";
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
     * @param salesmanName xxx
     */
    public void deleteSalesman(String salesmanName) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from salesman where sname=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, salesmanName);
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }

    public void updateSalesmanName(String oldName, String newName) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "update salesman set sname=? where sname=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, newName);
            psmt.setString(2, oldName);
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }

    public void updateSalesmanPassword(String sname, String newPassword) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "update salesman set spassword=? where sname=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, newPassword);
            psmt.setString(2, sname);
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }

    public List<Salesman> selectByKeyWord(String sname) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Salesman> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM salesman WHERE sname like ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + sname + "%");
            rs = psmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Salesman(rs.getString(2), rs.getString(3)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return list;
    }

    public List<Salesman> selectAllSalesman() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Salesman> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM salesman";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Salesman(rs.getString(2), rs.getString(3)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return list;
    }

    public Salesman selectByName(String sname) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Salesman salesman = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM salesman WHERE sname=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, sname);
            rs = psmt.executeQuery();
            if (rs.next()) {
                salesman = new Salesman(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return salesman;
    }
}
