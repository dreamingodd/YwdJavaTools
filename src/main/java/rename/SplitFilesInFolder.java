package rename;

import java.io.File;

public class SplitFilesInFolder {


    //    private static String rootName = "N:\\download";
//    private static String rootName = "F:\\CNTV\\ccccc";
//    private static String rootName = "F:\\new";
    private static String rootName = "D:\\git\\test\\facetest3 - 4 - 副本 (3)";

    private static String[] renameType = {"avi", "mkv", "mp4", "rmvb", "wmv"};

    public static void main(String[] args) {
        rename();
//        move();
    }

    private static void rename() {

        File root = new File(rootName);
        File greenFolder = new File(rootName + "\\green");
        File blackFolder = new File(rootName + "\\black");
        System.out.println(greenFolder.getPath());
        System.out.println(blackFolder.getPath());
        greenFolder.mkdir();
        blackFolder.mkdir();

        for (File file : root.listFiles()) {

            if (file.getName().endsWith(".jpg")) {
                if (file.getName().contains("-")) {
                    System.out.println(root.getPath() + "\\black\\" + file.getName());
                    file.renameTo(new File(root.getPath() + "\\black\\" + file.getName()));
                } else {
                    System.out.println(root.getPath() + "\\green\\" + file.getName());
                    file.renameTo(new File(root.getPath() + "\\green\\" + file.getName()));
                }
            }
        }
    }

    private static void move() {
        File root = new File(rootName);
        for (File folder : root.listFiles()) {
            if (folder.isDirectory()) {
                for (File file : folder.listFiles()) {
                    if (isRenameTarget(file.getName())) {
                        file.renameTo(new File(getPathWithoutFileName(folder.getPath()) + "\\" + file.getName()));
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
