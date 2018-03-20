package rename;

import java.io.File;

/**
 * 修改文件夹内的视频文件的名字为文件夹的名字
 * 
 *
 * @author Dreamingodd
 * @Date 2017年4月9日
 */
public class RenameFilesInFolder {

//    private static String rootName = "N:\\download";
//    private static String rootName = "F:\\CNTV\\ccccc";
//    private static String rootName = "F:\\new";
  private static String rootName = "N:\\Series\\Adult Video\\2018_2";

    private static String[] renameType = {"avi", "mkv", "mp4", "rmvb", "wmv"};

    public static void main(String[] args) {
        File root = new File(rootName);
        for (File folder : root.listFiles()) {
            boolean downloadCompleted = true;
            if (folder.isDirectory()) {
                for (File file : folder.listFiles()) {
                    if (file.getName().endsWith(".bt.td")) {
                        downloadCompleted = false;
                    }
                }
                if (downloadCompleted) {
                    int renamed = 0;
                    for (File file : folder.listFiles()) {
                        if (isRenameTarget(file.getName())) {
                            if (renamed == 0) {
                                file.renameTo(new File(getPathWithoutFileName(file.getPath()) + "\\"
                                        + folder.getName() + "." + getType(file.getName())));
                            } else {
                                file.renameTo(new File(getPathWithoutFileName(file.getPath()) + "\\"
                                        + folder.getName() + renamed + "." + getType(file.getName())));
                            }
                            renamed++;
                        }
                    }
                }
            }
        }
    }

    private static String getType(String fileName) {
        int dotPosition = fileName.lastIndexOf(".");
        return fileName.substring(dotPosition + 1);
    }

    private static String getNameWithoutType(String fileName) {
        int dotPosition = fileName.lastIndexOf(".");
        return fileName.substring(0, dotPosition);
    }

    private static String getPathWithoutFileName(String path) {
        int dotPosition = path.lastIndexOf("\\");
        return path.substring(0, dotPosition);
    }

    private static boolean isRenameTarget(String fileName) {
        boolean isRenameTarget = false;
        for (String type : renameType) {
            if (fileName.endsWith(type)) {
                isRenameTarget = true;
            }
        }
        return isRenameTarget;
    }
}
