package rename;

import java.io.File;

/**
 * @Author Dreamingodd
 * @Date 2019/12/29.
 */
public class ReplaceNameInFolder {

    private static String rootName = "D:\\Game\\1.Skills\\PES\\C.S.斯匡诺尔\\I-20";
    private static String preText = "z";
    private static String newText = "辶";

    public static void main(String[] args) {
        rename(preText, newText);
    }

    private static void rename(String preText, String newText) {
        File root = new File(rootName);
        for (File file : root.listFiles()) {
            file.renameTo(new File(file.getPath().replace(preText, newText)));
        }
    }
}
