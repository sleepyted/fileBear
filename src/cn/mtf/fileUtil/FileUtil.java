package cn.mtf.fileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by matengfei1 on 2018/11/16.
 */
public class FileUtil {

    private Stack<File> fileStack = new Stack<>();

    public FileUtil() {

    }

    public File[] getChildren() {

        if (fileStack.size() == 0) {
            return File.listRoots();
        }
        if (!fileStack.peek().isDirectory()) {
            return new File[0];
        }
        return fileStack.peek().listFiles();
    }

    public void back() {
        if (fileStack.size() > 0) {
            fileStack.pop();
        }
    }

    public void forward(int position) {
        File[] files = getChildren();
        if (files.length == 0) {
            System.out.println(">no children");
            return;
        }
        if (position < 0 ||position >= files.length) {
            System.out.println(">file not found");
            return;
        }
        fileStack.push(getChildren()[position]);
    }

    public void remove(int position) {
        fileStack.peek().listFiles()[position].delete();
    }

    public String currentAbsolutePath() {
        if (fileStack.size() == 0) return "root|";
        return fileStack.peek().getAbsolutePath();
    }

    public void printChilden() {
        File[] file = getChildren();
        if(null == file){
            System.out.println(">file read error");
            return;
        }
        if (fileStack.size() == 0) {
            for (int i = 0; i < file.length; i++) {
                long free = file[i].getFreeSpace() / (1024 * 1024);
                long total = file[i].getTotalSpace() / (1024 * 1024); // MB
                System.out.println(i + ">" + file[i] + "  [free/total:" + free + "MB/" + total + "MB]");
            }
        } else {
            for (int i = 0; i < file.length; i++) {
                System.out.println(i + ">" + file[i].getName());
            }
        }
    }

}
