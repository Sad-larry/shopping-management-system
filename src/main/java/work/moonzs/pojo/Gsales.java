package work.moonzs.pojo;


import java.sql.Date;

/**
 * @author Moondust月尘
 */
public class Gsales {
    /**
     * 购物车ID
     */
    private Integer gsid;
    /**
     * 商品ID
     */
    private Integer gid;
    /**
     * 销售员ID
     */
    private Integer sid;
    /**
     * 销售日期
     */
    private Date sdate;
    /**
     * 销售的商品数量
     */
    private Integer snum;

    public Gsales(Integer gid, Integer sid, Date sdate, Integer snum) {
        this.gid = gid;
        this.sid = sid;
        this.sdate = sdate;
        this.snum = snum;
    }

    public Integer getGsid() {
        return gsid;
    }

    public void setGsid(Integer gsid) {
        this.gsid = gsid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Integer getSnum() {
        return snum;
    }

    public void setSnum(Integer snum) {
        this.snum = snum;
    }

    @Override
    public String toString() {
        return "GsalesDAO{" +
                "gsid=" + gsid +
                ", gid=" + gid +
                ", sid=" + sid +
                ", sdate=" + sdate +
                ", snum=" + snum +
                '}';
    }
}
