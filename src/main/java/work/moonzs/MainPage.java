package work.moonzs;

import work.moonzs.dao.GoodsDAO;
import work.moonzs.dao.GsalesDAO;
import work.moonzs.dao.SalesmanDAO;
import work.moonzs.pojo.Goods;
import work.moonzs.pojo.Gsales;
import work.moonzs.pojo.Salesman;
import work.moonzs.utils.InputUtils;

import java.util.*;

/**
 * @author Moondust月尘
 */
public class MainPage {
    private static int salesman_id = 0;

    /**
     * 主程序入口
     *
     * @param args x
     */
    public static void main(String[] args) {
        welcomePage();
    }

    public static void welcomePage() {
        boolean isRun = true;
        while (isRun) {
            System.out.println("欢迎使用商超购物管理系统");
            System.out.println("\t1、登录系统");
            System.out.println("\t2、退出");
            System.out.println("*******************************");
            System.out.print("请选择，输入数字：");
            char input = InputUtils.readMenuSelectionForSystem();
            switch (input) {
                case '1':
                    if (loginPage()) {
                        mainPage();
                    }
                    break;
                case '0':
                case '2':
                    System.out.println("退出...");
                    isRun = false;
                    break;
                default:
                    System.out.println("NONE");
                    break;
            }
        }
    }

    /**
     * 销售员登录操作
     *
     * @return xxx
     */
    public static boolean loginPage() {
        SalesmanDAO salesmanDAO = new SalesmanDAO();
        int loginCount = 2;
        boolean isLogin = false;
        for (; loginCount >= 0; loginCount--) {
            System.out.print("请输入用户名：");
            String username = InputUtils.readString();
            System.out.print("请输入密码：");
            String password = InputUtils.readString();
            isLogin = salesmanDAO.querySalesman(new Salesman(username, password));
            if (isLogin || loginCount == 0) {
                Salesman salesman = salesmanDAO.selectByName(username);
                salesman_id = salesman.getSid();
                break;
            }
            System.out.println("用户名和密码不匹配!");
            System.out.println("您还有" + loginCount + "次登陆机会，请重新输入!");
        }
        return isLogin;
    }

    public static void mainPage() {
        boolean isRun = true;
        while (isRun) {
            System.out.println("*******************************");
            System.out.println("\t1、商品维护");
            System.out.println("\t2、前台收银");
            System.out.println("\t3、商品管理");
            System.out.println("*******************************");
            System.out.print("请选择，输入数字或者按0退出：");
            char input = InputUtils.readMenuSelectionForMain();
            switch (input) {
                case '0':
                    System.out.println("上一级...");
                    isRun = false;
                    break;
                case '1':
                    System.out.println("执行显示商品维护菜单");
                    goodsMain();
                    break;
                case '2':
                    System.out.println("前台收银...");
                    saleShopping();
                    break;
                case '3':
                    System.out.println("执行商品管理");
                    goodsManager();
                    break;
                default:
                    System.out.println("NONE");
                    break;
            }
        }

    }

    public static void goodsMain() {
        boolean isRun = true;
        while (isRun) {
            System.out.println("商超购物管理系统>>商品维护");
            System.out.println("*******************************");
            System.out.println("\t1、添加商品");
            System.out.println("\t2、更改商品");
            System.out.println("\t3、删除商品");
            System.out.println("\t4、显示所有商品");
            System.out.println("\t5、查询商品");
            System.out.println("*******************************");
            System.out.print("请选择，输入数字或者按0退出：");
            char input = InputUtils.readMenuSelectionForGoodsAndSaleMan();
            switch (input) {
                case '0':
                    System.out.println("上一级...");
                    isRun = false;
                    break;
                case '1':
                    System.out.println("执行添加商品操作：");
                    insertGoods();
                    break;
                case '2':
                    System.out.println("执行更改商品操作：");
                    updateGoods();
                    break;
                case '3':
                    System.out.println("执行删除商品操作：");
                    deleteGoods();
                    break;
                case '4':
                    System.out.println("执行显示所有商品操作：");
                    listGoods();
                    break;
                case '5':
                    System.out.println("执行查询商品操作：");
                    queryGoods();
                    break;
                default:
                    System.out.println("NONE");
                    break;
            }
        }
    }

    public static void saleShopping() {
        System.out.println("\t1、购物结算");
        double totalPrice = 0d;
        GoodsDAO goodsDAO = new GoodsDAO();
        List<List<String>> buyCart = new ArrayList<>();
        while (true) {
            System.out.print("输入商品关键字：");
            String keyword = InputUtils.readString();
            List<Goods> list = goodsDAO.selectByKeyWord(keyword);
            if (list != null) {
                System.out.printf("%-10s %-10s %-10s %-10s\n", "商品名称", "商品价格", "商品数量", "备注");
                for (Goods goods : list) {
                    System.out.printf("%-10s %-10.2f %-10d\n", goods.getGname(), goods.getGprice(), goods.getGnum());
                }
                System.out.print("请选择商品：");
                String gname = InputUtils.readString();
                Goods goods = goodsDAO.selectByName(gname);
                if (goods == null) {
                    System.out.println("未查询到数据");
                } else {
                    System.out.print("请输入购买数量");
                    int buyNum = InputUtils.readNumber();
                    if (buyNum > goods.getGnum()) {
                        System.out.println("数量超额");
                    } else {
                        List<String> list1 = new ArrayList<>();
                        list1.add(gname);
                        list1.add(String.valueOf(goods.getGid()));
                        list1.add(String.valueOf(buyNum));
                        list1.add(String.valueOf(goods.getGnum() - buyNum));
                        buyCart.add(list1);
                        totalPrice += buyNum * goods.getGprice();
                        System.out.printf("%-10s %-10.2f 购买数量%-10d %-10s总价%-10.2f\n", goods.getGname(), goods.getGprice(), buyNum, goods.getGname(), buyNum * goods.getGprice());
                    }
                }
            }
            System.out.print("是否继续添加商品（y/n）:");
            char c = InputUtils.readConfirmSelection();
            if (c != 'y') {
                break;
            }
        }
        System.out.println("总计：" + totalPrice);
        System.out.print("请输入实际交费金额：");
        double money = InputUtils.readDouble();
        if (money < totalPrice) {
            System.out.println("余额不足");
            return;
        }
        GsalesDAO gsalesDAO = new GsalesDAO();
        for (List<String> l : buyCart) {
            gsalesDAO.insertGsales(Integer.parseInt(l.get(1)), salesman_id, Integer.parseInt(l.get(2)));
            goodsDAO.updateGoodsNum(l.get(0), Integer.parseInt(l.get(3)));
        }
        System.out.println("找钱：" + (money - totalPrice));
        System.out.println("谢谢光临");
    }

    public static void goodsManager() {
        boolean isRun = true;
        while (isRun) {
            System.out.println("商超购物管理系统>>商品管理");
            System.out.println("*******************************");
            System.out.println("\t1、列出当日卖出商品列表");
            System.out.println("\t2、售货员管理");
            System.out.println("*******************************");
            char input = InputUtils.readMenuSelectionForSystem();
            switch (input) {
                case '0':
                    System.out.println("上一级...");
                    isRun = false;
                    break;
                case '1':
                    System.out.println("执行列出当日卖出商品列表操作");
                    listGoodsForSaledToday();
                    break;
                case '2':
                    System.out.println("售货员管理...");
                    salesmanManager();
                    break;
                default:
                    System.out.println("NONE");
                    break;
            }
        }
    }

    public static void insertGoods() {
        GoodsDAO goodsDAO = new GoodsDAO();
        System.out.print("添加商品的名称：");
        String gname = InputUtils.readString();
        System.out.print("添加商品的价格：");
        Double gprice = InputUtils.readDouble();
        System.out.print("添加商品的数量：");
        Integer gnum = InputUtils.readNumber();
        System.out.print("是否确定添加该商品(y/n)：");
        char c = InputUtils.readConfirmSelection();
        if (c == 'y') {
            goodsDAO.insertGoods(new Goods(gname, gprice, gnum));
            System.out.println("添加商品成功");
        }
        System.out.println("是否继续添加商品(y/n)：");
        c = InputUtils.readConfirmSelection();
        if (c == 'y') {
            insertGoods();
        }
    }

    public static void updateGoods() {
        System.out.print("请输入更改商品名称：");
        String gname = InputUtils.readString();
        GoodsDAO goodsDAO = new GoodsDAO();
        Goods goods = goodsDAO.selectByName(gname);
        if (goods != null) {
            System.out.printf("%-10s%-10s%-10s\n", "商品名称", "商品价格", "商品数量");
            System.out.printf("%-10s%-10s%-10s\n", goods.getGname(), goods.getGprice(), goods.getGnum());
            System.out.println("选择您要更改的内容：");
            System.out.println("\t1、更改商品名称：");
            System.out.println("\t2、更改商品价格：");
            System.out.println("\t3、更改商品数量：");
            char c = InputUtils.readMenuSelectionForGoodsAndSaleMan();
            if (c == '1') {
                System.out.print("请输入要更改商品名称：");
                String newName = InputUtils.readString();
                System.out.print("是否确定更改(y/n)");
                char isUpdate = InputUtils.readConfirmSelection();
                if (isUpdate == 'y') {
                    goodsDAO.updateGoodsName(gname, newName);
                    System.out.println("更改成功");
                }
            } else if (c == '2') {
                System.out.print("请输入要更改商品价格：");
                double newPrice = InputUtils.readDouble();
                System.out.print("是否确定更改(y/n)");
                char isUpdate = InputUtils.readConfirmSelection();
                if (isUpdate == 'y') {
                    goodsDAO.updateGoodsPrice(gname, newPrice);
                    System.out.println("更改成功");
                }

            } else if (c == '3') {
                System.out.print("请输入要更改商品数量：");
                Integer newNum = InputUtils.readNumber();
                System.out.print("是否确定更改(y/n)");
                char isUpdate = InputUtils.readConfirmSelection();
                if (isUpdate == 'y') {
                    goodsDAO.updateGoodsNum(gname, newNum);
                    System.out.println("更改成功");
                }
            }
        } else {
            System.out.println("未查询到数据");
        }
        System.out.println("是否继续更改商品(y/n)：");
        char c3 = InputUtils.readConfirmSelection();
        if (c3 == 'y') {
            updateGoods();
        }
    }

    public static void deleteGoods() {
        System.out.print("输入删除的商品名称：");
        String gname = InputUtils.readString();
        GoodsDAO goodsDAO = new GoodsDAO();
        Goods goods = goodsDAO.selectByName(gname);
        if (goods != null) {
            System.out.printf("%-10s%-10s%-10s\n", "商品名称", "商品价格", "商品数量");
            System.out.printf("%-10s%-10s%-10s\n", goods.getGname(), goods.getGprice(), goods.getGnum());
            System.out.println("是否确定要删除(y/n)?：");
            char c = InputUtils.readConfirmSelection();
            if (c == 'y') {
                goodsDAO.deleteGoods(gname);
                System.out.println("删除成功");
            }
        } else {
            System.out.println("为查询到数据");
        }
        System.out.println("是否继续(y/n)?：");
        char c3 = InputUtils.readConfirmSelection();
        if (c3 == 'y') {
            deleteGoods();
        }
    }

    public static void listGoods() {
        System.out.println("显示所有商品");
        GoodsDAO goodsDAO = new GoodsDAO();
        List<Goods> list = goodsDAO.selectAllGoods();
        if (list != null) {
            System.out.printf("%-10s %-10s %-10s\n", "商品名称", "商品价格", "商品数量");
            for (Goods goods : list) {
                System.out.printf("%-10s %-10.2f %-10d\n", goods.getGname(), goods.getGprice(), goods.getGnum());
            }
        }
    }

    public static void queryGoods() {
        GoodsDAO goodsDAO = new GoodsDAO();
        System.out.println("\t1、按商品数量升序查询");
        System.out.println("\t2、按商品价格升序查询");
        System.out.println("\t3、输入关键字查询商品");
        char c = InputUtils.readMenuSelectionForMain();
        if (c == '1') {
            List<Goods> list = goodsDAO.selectAllGoodsByNum();
            if (list != null) {
                System.out.printf("%-10s%-10s%-10s\n", "商品名称", "商品价格", "商品数量");
                for (Goods goods : list) {
                    System.out.printf("%-10s%-10.2f%-10d\n", goods.getGname(), goods.getGprice(), goods.getGnum());
                }
            }
        } else if (c == '2') {
            List<Goods> list = goodsDAO.selectAllGoodsByPrice();
            if (list != null) {
                System.out.printf("%-10s%-10s%-10s\n", "商品名称", "商品价格", "商品数量");
                for (Goods goods : list) {
                    System.out.printf("%-10s%-10.2f%-10d\n", goods.getGname(), goods.getGprice(), goods.getGnum());
                }
            }
        } else if (c == '3') {
            System.out.print("输入要查询的商品名称关键字：");
            String gname = InputUtils.readString();
            List<Goods> list = goodsDAO.selectByKeyWord(gname);
            if (list != null) {
                System.out.printf("%-10s%-10s%-10s\n", "商品名称", "商品价格", "商品数量");
                for (Goods goods : list) {
                    System.out.printf("%-10s%-10.2f%-10d\n", goods.getGname(), goods.getGprice(), goods.getGnum());
                }
            }
        }
        System.out.print("是否继续（y/n）：");
        char c3 = InputUtils.readConfirmSelection();
        if (c3 == 'y') {
            queryGoods();
        }
    }

    public static void listGoodsForSaledToday() {
        System.out.println("今日售出商品：");
        GsalesDAO gsalesDAO = new GsalesDAO();
        List<List<String>> list = gsalesDAO.selectTodaySales();
        List<Goods> listg= null;
        List<Salesman> lists = null;
        if(list != null) {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "商品名称", "商品价格", "商品数量", "销量", "备注");
            for(List<String> l : list){
                System.out.printf("%-10s %-10s %-10s %-10s %-10s", l.get(0), l.get(1), l.get(2), l.get(3));
                if((Integer.parseInt(l.get(2)) - Integer.parseInt(l.get(3))) < 10){
                    System.out.printf("%-10s\n", "*该商品已不足10件");
                } else{
                    System.out.println();
                }
            }
        }
    }

    public static void salesmanManager() {
        boolean isRun = true;
        while (isRun) {
            System.out.println("商超购物管理系统>>商品管理>>售货员管理.");
            System.out.println("*******************************");
            System.out.println("\t1、添加售货员");
            System.out.println("\t2、更改售货员");
            System.out.println("\t3、删除售货员");
            System.out.println("\t4、显示所有售货员");
            System.out.println("\t5、查询售货员");
            System.out.println("*******************************");
            System.out.print("请选择，输入数字或者按0退出：");
            char input = InputUtils.readMenuSelectionForGoodsAndSaleMan();
            switch (input) {
                case '0':
                    System.out.println("上一级...");
                    isRun = false;
                    break;
                case '1':
                    System.out.println("执行添加售货员操作:");
                    insertSalesman();
                    break;
                case '2':
                    System.out.println("执行更改售货员操作:");
                    updateSalesman();
                    break;
                case '3':
                    System.out.println("执行删除售货员操作");
                    deleteSalesman();
                    break;
                case '4':
                    System.out.println("执行显示所有售货员操作");
                    listSalesman();
                    break;
                case '5':
                    System.out.println("执行查询售货员操作");
                    querySalesman();
                    break;
                default:
                    System.out.println("NONE");
                    break;
            }
        }
    }

    public static void insertSalesman() {
        System.out.print("添加售货员姓名：");
        String sname = InputUtils.readString();
        System.out.print("添加售货员密码：");
        String spassword = InputUtils.readString();
        System.out.print("是否继续（y/n）：");
        char c = InputUtils.readConfirmSelection();
        if (c == 'y') {
            new SalesmanDAO().insertSalesman(new Salesman(sname, spassword));
            System.out.println("添加成功");
        }
        System.out.print("是否继续添加（y/n）：");
        c = InputUtils.readConfirmSelection();
        if (c == 'y') {
            insertSalesman();
        }
    }

    public static void updateSalesman() {
        System.out.print("输入更改的售货员姓名：");
        String sname = InputUtils.readString();
        SalesmanDAO salesmanDAO = new SalesmanDAO();
        Salesman salesman = salesmanDAO.selectByName(sname);
        if (salesman != null) {
            System.out.printf("%-10s%-10s\n", "售货员姓名", "售货员密码");
            System.out.printf("%-10s%-10s\n", salesman.getSname(), salesman.getSpassword());
            System.out.println("选择您要更改的内容：");
            System.out.println("\t1、更改售货员姓名");
            System.out.println("\t2、更改售货员密码");
            char c = InputUtils.readMenuSelectionForSystem();
            if (c == '1') {
                System.out.print("请输入已更改售货员姓名：");
                String newName = InputUtils.readString();
                System.out.print("是否继续（y/n）：");
                char c2 = InputUtils.readConfirmSelection();
                if (c2 == 'y') {
                    salesmanDAO.updateSalesmanName(sname, newName);
                    System.out.println("更新成功");
                }
            } else if (c == '2') {
                System.out.print("请输入已更改售货员密码：");
                String newPassword = InputUtils.readString();
                System.out.print("是否继续（y/n）：");
                char c2 = InputUtils.readConfirmSelection();
                if (c2 == 'y') {
                    salesmanDAO.updateSalesmanPassword(sname, newPassword);
                    System.out.println("更新成功");
                }
            }
        } else {
            System.out.println("未查询到数据");
        }
        System.out.print("是否继续更新（y/n）：");
        char c = InputUtils.readConfirmSelection();
        if (c == 'y') {
            updateSalesman();
        }
    }

    public static void deleteSalesman() {
        System.out.print("输入删除的售货员姓名：");
        String sname = InputUtils.readString();
        SalesmanDAO salesmanDAO = new SalesmanDAO();
        Salesman salesman = salesmanDAO.selectByName(sname);
        if (salesman != null) {
            System.out.printf("%-10s%-10s\n", "售货员姓名", "售货员密码");
            System.out.printf("%-10s%-10s\n", salesman.getSname(), salesman.getSpassword());
            System.out.print("是否确定删除（y/n）：");
            char c = InputUtils.readConfirmSelection();
            if (c == 'y') {
                new SalesmanDAO().deleteSalesman(sname);
                System.out.println("删除成功");
            }
        } else {
            System.out.println("未查询到数据");
        }
        System.out.print("是否继续（y/n）：");
        char c = InputUtils.readConfirmSelection();
        if (c == 'y') {
            deleteSalesman();
        }
    }

    public static void listSalesman() {
        SalesmanDAO salesmanDAO = new SalesmanDAO();
        List<Salesman> list = salesmanDAO.selectAllSalesman();
        if (list != null) {
            System.out.printf("%-10s%-10s\n", "售货员姓名", "售货员密码");
            for (Salesman salesman : list) {
                System.out.printf("%-10s%-10s\n", salesman.getSname(), salesman.getSpassword());
            }
        }
    }

    public static void querySalesman() {
        System.out.print("输入要查询的售货员姓名关键字：");
        String sname = InputUtils.readString();
        SalesmanDAO salesmanDAO = new SalesmanDAO();
        List<Salesman> list = salesmanDAO.selectByKeyWord(sname);
        if (list != null) {
            System.out.printf("%-10s%-10s\n", "售货员姓名", "售货员密码");
            for (Salesman salesman : list) {
                System.out.printf("%-10s%-10s\n", salesman.getSname(), salesman.getSpassword());
            }
        }
        System.out.print("是否继续（y/n）：");
        char c = InputUtils.readConfirmSelection();
        if (c == 'y') {
            querySalesman();
        }
    }
}
