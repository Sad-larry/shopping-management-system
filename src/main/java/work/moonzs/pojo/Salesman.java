package work.moonzs.pojo;

/**
 * @author Moondust月尘
 */
public class Salesman {
    /**
     * 销售员ID
     */
    private Integer sid;

    /**
     * 销售员用户名
     */
    private String sname;
    /**
     * 销售员密码
     */
    private String spassword;

    public Salesman() {
    }

    public Salesman(String sname, String spassword) {
        this.sname = sname;
        this.spassword = spassword;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", spassword='" + spassword + '\'' +
                '}';
    }
}
