package work.moonzs;

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
        System.out.println("欢迎使用商超购物管理系统");
        System.out.println("\t1、登录系统");
        System.out.println("\t2、退出");
        System.out.println("*******************************");
        System.out.print("请选择，输入数字：");
        int input = new Scanner(System.in).nextInt();
        if (input == 1) {
            System.out.println("登录...");
        } else {
            System.out.println("退出...");
        }
    }
}
