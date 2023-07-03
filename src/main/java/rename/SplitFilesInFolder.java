package rename;

import countLine.PropertyUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitFilesInFolder {



    public static void main(String[] args) throws IOException {
        split();
    }

    private static void split() throws IOException {


        PropertyUtil propertyUtil = new PropertyUtil();
        String[] fileTypesStrings = propertyUtil.getFileTypes();
        String rootPath = propertyUtil.getSplitFolder();

        backup(rootPath);

        int barCount = propertyUtil.getBarCount();

        System.out.println("target          : " + rootPath);
        System.out.println("Folders up to   : " + barCount);
        System.out.println("Including Types : " + Arrays.toString(propertyUtil.getFileTypes()));

        List<String> fileTypes = new ArrayList<>();
        for (int i = 0; i < fileTypesStrings.length; i++) {
            fileTypes.add(fileTypesStrings[i]);
        }

        File root = new File(rootPath);
        List<File> barFolders = new ArrayList<File>();

        for (int i = 0; i <= barCount; i++) {
            File barFolder = new File(rootPath + "\\Bar" + i);
            barFolder.mkdir();
            barFolders.add(barFolder);
        }

        for (File file : root.listFiles()) {

            if (fileTypes.contains(getType(file.getName()))) {
                if (file.getName().contains("-")) {
                    String barNumberString = file.getName().substring(file.getName().lastIndexOf("-") + 1, file.getName().lastIndexOf("-") + 2);
                    int barNumber = Integer.parseInt(barNumberString);
                    file.renameTo(new File(barFolders.get(barNumber) + "\\" + file.getName()));
                } else {
                    file.renameTo(new File(barFolders.get(0) + "\\" + file.getName()));
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

    public static void backup(String rootPath) throws IOException {
        File originalFolder = new File(rootPath);
        File backupFolder = new File(rootPath + " -backup");
        backupFolder.mkdir();
        FileUtils.copyDirectory(originalFolder, backupFolder);
    }
}
