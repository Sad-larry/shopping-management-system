package work.moonzs;

import work.moonzs.dao.SalesmanDAO;
import work.moonzs.pojo.Salesman;
import work.moonzs.utils.InputUtils;

import java.util.Scanner;

/**
 * @author Moondust月尘
 */
public class MainPage {

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
                    System.out.println("登录...");
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
                    System.out.println("商品维护...");
                    goodsMain();
                    break;
                case '2':
                    System.out.println("前台收银...");
                    saleShopping();
                    break;
                case '3':
                    System.out.println("商品管理...");
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
                case '1':
                    System.out.println("添加商品...");
                    insertGoods();
                    break;
                case '2':
                    System.out.println("更改商品...");
                    updateGoods();
                    break;
                case '3':
                    System.out.println("删除商品...");
                    deleteGoods();
                    break;
                case '4':
                    System.out.println("显示所有商品...");
                    listGoods();
                    break;
                case '5':
                    System.out.println("查询商品...");
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
        System.out.println("输入商品关键字：");
    }

    public static void goodsManager() {
        boolean isRun = true;
        while (isRun) {
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
                    System.out.println("列出当日卖出商品列表...");
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
        System.out.println("添加商品");
    }

    public static void updateGoods() {
        System.out.println("修改商品");
    }

    public static void deleteGoods() {
        System.out.println("删除商品");
    }

    public static void listGoods() {
        System.out.println("列出所有傻瓜");
    }

    public static void queryGoods() {
        System.out.println("查询商品");
    }

    public static void listGoodsForSaledToday() {
        System.out.println("列出当日卖出商品列表");
    }

    public static void salesmanManager() {
        boolean isRun = true;
        while (isRun) {
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
                case '1':
                    System.out.println("添加售货员...");
                    insertSalesman();
                    break;
                case '2':
                    System.out.println("更改售货员...");
                    updateSalesman();
                    break;
                case '3':
                    System.out.println("删除售货员...");
                    deleteSalesman();
                    break;
                case '4':
                    System.out.println("显示所有售货员...");
                    listSalesman();
                    break;
                case '5':
                    System.out.println("查询售货员...");
                    querySalesman();
                    break;
                default:
                    System.out.println("NONE");
                    break;
            }
        }
    }

    public static void insertSalesman() {
        System.out.println("添加售货员");
    }

    public static void updateSalesman() {
        System.out.println("更改售货员");
    }

    public static void deleteSalesman() {
        System.out.println("删除售货员");
    }

    public static void listSalesman() {
        System.out.println("显示所有售货员");
    }

    public static void querySalesman() {
        System.out.println("查询售货员");
    }
}
