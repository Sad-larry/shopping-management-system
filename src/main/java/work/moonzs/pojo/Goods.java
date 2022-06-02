package work.moonzs.pojo;

/**
 * @author Moondust月尘
 */
public class Goods {
    /**
     * 商品ID
     */
    private Integer gid;
    /**
     * 商品名称
     */
    private String gname;
    /**
     * 商品价格
     */
    private Double gprice;
    /**
     * 商品存货
     */
    private Integer gnum;

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gprice=" + gprice +
                ", gnum=" + gnum +
                '}';
    }
}
