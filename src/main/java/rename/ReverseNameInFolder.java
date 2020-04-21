package rename;

import java.io.File;

/**
 * @Author Dreamingodd
 * @Date 2020/4/22.
 */
public class ReverseNameInFolder {

    private static String rootName = "D:\\Game\\1.Skills\\PES\\C.S.斯匡诺尔\\I-19";
    private static String moveText = "2019";

    public static void main(String[] args) {
        rename(moveText);
    }

    private static void rename(String moveText) {
        File root = new File(rootName);
        for (File file : root.listFiles()) {
            if (moveText.equals(file.getName().substring(0,4))) {
                String type = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                String prePath = file.getPath().substring(0, file.getPath().lastIndexOf("\\"));
                String newName = file.getName().substring(5, file.getName().lastIndexOf(".")) + "-" + moveText + "." + type;
                String newPath = prePath + "\\" + newName;
                System.out.println(newPath);
                file.renameTo(new File(newPath));
            }
        }
    }
}
