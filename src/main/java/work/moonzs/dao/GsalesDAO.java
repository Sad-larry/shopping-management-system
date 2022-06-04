package work.moonzs.dao;

import work.moonzs.utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Moondust月尘
 * <p>
 * GsalesDAO里面实现对GSALES表的操作方法
 */
public class GsalesDAO {
    public void insertGsales(int gid, int sid, int snum) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO gsales values(null,?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, gid);
            psmt.setInt(2, sid);
            psmt.setDate(3, new Date(System.currentTimeMillis()));
            psmt.setInt(4, snum);
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }
}

