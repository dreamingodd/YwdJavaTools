package rename;

import java.io.File;

/**
 * @Author Dreamingodd
 * @Date 2019/12/29.
 */
public class ReplaceNameInFolder {

    private static String rootName = "D:\\GameSkills\\PES\\C.S.斯匡诺尔\\I-44";
    private static String preText = "2043";
    private static String newText = "2044";
//    private static String rootName = "D:\\6.Matches\\Basketball\\2020-22";
//    private static String preText = "【天下足球网www.txzqw.me】";
//    private static String newText = "";

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
