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

    public List<List<String>> selectTodaySales(){
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<List<String>> list = new ArrayList<>();
        List<String> l = null;
        List<Goods> listg = null;
        List<Gsales> lists = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT goods.gname,goods.gprice,goods.gnum,gsales.snum \n" +
                    "FROM gsales JOIN goods ON gsales.`gid`=goods.`gid`\n" +
                    "WHERE sdate=?";
            psmt = conn.prepareStatement(sql);
            psmt.setDate(1, new Date(System.currentTimeMillis()));
            rs = psmt.executeQuery();
            l = new ArrayList<>();
            while (rs.next()) {
                listg = new ArrayList<>();
                lists = new ArrayList<>();
                l.add(rs.getString(1), rs.getDouble(2), rs.getInt(3));
                l.add(new Gsales(rs.getInt(4)));
                l.add(listg.toString());
                l.add(lists.toString());
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

