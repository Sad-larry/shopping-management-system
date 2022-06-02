package work.moonzs.utils;

import java.util.Scanner;

/**
 * @author Moondust月尘
 * <p>
 * 封装输入类
 */
public class InputUtils {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * 负责读取一行限制长度的字符串
     * 进入相应的方法类型转换
     *
     * @param limit xxx
     * @return xxx
     */
    private static String readKeyBoard(int limit) {
        String line = "";
        while (SCANNER.hasNext()) {
            line = SCANNER.nextLine();
            if (line.length() < 1 || line.length() > limit) {
                System.out.print("输入长度（不大于" + limit + "）错误，请重新输入：");
                continue;
            }
            break;
        }
        return line;
    }

    /**
     * 用于登录系统时的选择
     *
     * @return xxx
     */
    public static char readMenuSelectionForSystem() {
        char c;
        while (true) {
            c = readKeyBoard(1).charAt(0);
            if (c != '1' && c != '2') {
                System.out.print("选择错误，请重新输入：");
            } else {
                break;
            }
        }
        return c;
    }

    /**
     * 用于商品维护，售货员管理时的选择
     *
     * @return xxx
     */
    public static char readMenuSelectionForGoodsAndSaleMan() {
        char c;
        while (true) {
            c = readKeyBoard(1).charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5') {
                System.out.print("选择错误，请重新输入：");
            } else {
                break;
            }
        }
        return c;
    }

    /**
     * 读取长度不超过6位数的整数
     * @return xxx
     */
    public static int readNumber() {
        int n = 0;
        while (true) {
            try {
                n = Integer.parseInt(readKeyBoard(6));
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return n;
    }

    /**
     * 用于浮点数的输入，长度不超过10
     * @return xxx
     */
    public static double readDouble() {
        double d;
        while (true) {
            try {
                d = Double.parseDouble(readKeyBoard(10));
                break;
            } catch (NumberFormatException e) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
        return d;
    }

    /**
     * 用于读取长度不超过10的字符串
     * @return xxx
     */
    public static String readString() {
        return readKeyBoard(10);
    }

    /**
     * 用于确认选择的输入。该方法从键盘读取‘Y’或’N’，并将其作为方法的返回值。
     * @return xxx
     */
    public static char readConfirmSelection() {
        char c;
        while (true){
            c = readKeyBoard(1).toUpperCase().charAt(0);
            if (c != 'Y' && c != 'N') {
                break;
            } else {
                System.out.print("选择错误，请重新输入：");
            }
        }
        return c;
    }
}
