package work.moonzs.dao;

import work.moonzs.pojo.Goods;
import work.moonzs.pojo.Gsales;
import work.moonzs.pojo.Salesman;
import work.moonzs.utils.DBUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public List<List<String>> selectTodaySales() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<List<String>> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql =
                    "SELECT goods.gname,goods.gprice,goods.gnum,gsales.snum FROM gsales JOIN goods ON gsales.`gid`=goods.`gid` WHERE sdate=?";
            psmt = conn.prepareStatement(sql);
            psmt.setDate(1, new Date(System.currentTimeMillis()));
            rs = psmt.executeQuery();
            List<String> l = null;
            while (rs.next()) {
                l = new ArrayList<>();
                l.add(rs.getString(1));
                l.add(String.valueOf(rs.getDouble(2)));
                l.add(String.valueOf(rs.getInt(3)));
                l.add(String.valueOf(rs.getInt(4)));
                if (rs.getInt(3) < 10) {
                    l.add("*该商品已不足10件");
                } else {
                    l.add("");
                }
                list.add(l);
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return list;
    }
}

