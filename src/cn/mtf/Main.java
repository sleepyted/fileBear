package cn.mtf;

import cn.mtf.fileUtil.FileUtil;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        FileUtil fileUtil = new FileUtil();
        //loop
        do {
            //print
            System.out.println(">" + fileUtil.currentAbsolutePath());
            System.out.print(">");
            //read
            String action = scanner.nextLine();

            //execute
            try {
                int act = Integer.parseInt(action);
                fileUtil.forward(act);
            } catch (NumberFormatException e) {
                switch (action) {
                    case "pwd":
                        fileUtil.currentAbsolutePath();
                        break;
                    case "b":
                        fileUtil.back();
                        break;
                    case "ls":
                        fileUtil.printChilden();
                        break;
                    case "quit":
                        quit = true;
                        break;
                }
            }
        } while (!quit);

    }
}
