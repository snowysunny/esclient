package es.client.test.es;

import java.util.Scanner;

/**
 * Created by hadoop on 2017/03/20.
 */
public class Scan_Print {
    public Scan_Print() {
    }

    public static String Scan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入查询词：");
        String queryword = sc.nextLine();
        return queryword;
    }
}
