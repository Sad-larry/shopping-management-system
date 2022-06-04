package work.moonzs.dao;

import work.moonzs.pojo.Goods;
import work.moonzs.pojo.Salesman;
import work.moonzs.utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moondust月尘
 * <p>
 * GoodsDAO里面实现对GOODS表的操作方法
 */
public class GoodsDAO {
    /**
     * 插入商品，传入的是一个商品对象
     *
     * @param goods xxx
     */
    public void insertGoods(Goods goods) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO goods values(null,?,?,?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, goods.getGname());
            psmt.setDouble(2, goods.getGprice());
            psmt.setInt(3,goods.getGnum());
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }

    /**
     * 删除商品，传入的是一个商品对象
     *
     * @param gname xxx
     */
    public void deleteGoods(String gname) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "delete from goods where gname=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, gname);
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }

    /**
     * 修改商品名称，传入的是商品旧名称和新名称
     *
     */
    public void updateGoodsName(String oldName, String newName) {
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "update goods set gname=? where gname=?";
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
    /**
     * 修改商品价格，传入的是商品名称和商品价格
     *
     */
    public void updateGoodsPrice(String gname, double gprice){
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "update goods set gprice=? where gname=?";
            psmt = conn.prepareStatement(sql);
            psmt.setDouble(1, gprice);
            psmt.setString(2, gname);
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }

    /**
     * 修改商品数量，传入的是商品名称和商品数量
     *
     */
    public void updateGoodsNum(String gname, int gnum){
        Connection conn = null;
        PreparedStatement psmt = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "update goods set gnum=? where gname=?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, gnum);
            psmt.setString(2, gname);
            psmt.executeUpdate();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt);
        }
    }

    /**
     * 查询所有商品
     *
     */
    public List<Goods> selectAllGoods() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Goods> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM goods";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Goods(rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return list;
    }

    /**
     * 按商品数量升序查询,查询所有商品
     *
     */
    public List<Goods> selectAllGoodsByNum() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Goods> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM goods order by gnum asc";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Goods(rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return list;
    }

    /**
     * 按商品价格升序查询,查询所有商品
     *
     */
    public List<Goods> selectAllGoodsByPrice() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Goods> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM goods order by gprice asc";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Goods(rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return list;
    }

    /**
     * 输入关键字查询商品,查询所有商品
     *
     */
    public List<Goods> selectByKeyWord(String gname) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        List<Goods> list = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM goods WHERE gname like ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, "%" + gname + "%");
            rs = psmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Goods(rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return list;
    }

    /**
     * 根据商品名称查询商品，传入的是一个商品对象
     *
     * @param gname xxx
     */
    public Goods selectByName(String gname) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Goods goods = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM goods WHERE gname=?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, gname);
            rs = psmt.executeQuery();
            if (rs.next()) {
                goods = new Goods(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResource(conn, psmt, rs);
        }
        return goods;
    }
}
