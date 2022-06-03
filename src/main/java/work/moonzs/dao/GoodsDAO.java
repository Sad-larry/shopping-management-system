package work.moonzs.dao;

import work.moonzs.pojo.Goods;
import work.moonzs.pojo.Salesman;
import work.moonzs.utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO goods values(?,?,?,?)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, goods.getGid());
            psmt.setString(2, goods.getGname());
            psmt.setDouble(3, goods.getGprice());
            psmt.setInt(4,goods.getGnum());
            psmt.executeUpdate();
            DBUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除商品，传入的是一个商品对象
     *
     * @param goods xxx
     */
    public void deleteGoods(Goods goods) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "delete from goods where gid=?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, goods.getGid());
            psmt.executeUpdate();
            DBUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改商品价格，传入的是一个商品对象
     *
     * @param goods xxx
     */
    public void updateGoods(Goods goods){
        try{
            Connection conn = DBUtils.getConnection();
            String sql = "update salesman set gprice=?,gnum=? where gid=?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setDouble(1, goods.getGprice());
            psmt.setInt(2, goods.getGnum());
            psmt.setInt(3, goods.getGid());
            psmt.executeUpdate();
            DBUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询商品，传入的是一个商品对象
     *
     * @param goods xxx
     */
    public void queryGoods(Goods goods) {
        String sql = "SELECT * FROM goods";
        try{
            Connection conn = DBUtils.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.execute();
            DBUtils.closeResource(conn, psmt);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
