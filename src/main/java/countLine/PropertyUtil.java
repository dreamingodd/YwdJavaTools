package countLine;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Load properties.properties.
 * @Author Ye_Wenda
 * @Date 1/4/2017
 *
 */
public class PropertyUtil {

    public static final String propertyRelativePath = "\\src\\main\\resources\\properties_template.properties";

    private String path;
    private String[] fileTypes;
    private String[] exclusions;
    private boolean showCountedFile;


    private int barCount;

    private String splitFolder;

    public PropertyUtil() {
        String sysPath = System.getProperty("user.dir");
        String propPath = System.getenv("PROP_PATH");
//        String propertyPath = sysPath + propertyRelativePath;
        collectProperties(propPath);
    }

    private void collectProperties(String propertyPath) {
        Properties properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream(propertyPath);
            properties.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Property file not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
        String path = (String) properties.get("path");
        String fileTypes = (String) properties.get("fileTypes");
        String splitFolder = (String) properties.get("splitFolder");
        String exclusions = (String) properties.get("exclusions");
        String showCountedFile = (String) properties.get("showCountedFile");
        int barCount = Integer.parseInt((String) properties.get("barCount"));

        this.barCount = barCount;
        this.splitFolder = splitFolder;
        this.path = path;
        this.fileTypes = fileTypes.split(",");
        this.exclusions = exclusions.split(",");
        this.showCountedFile = "1".equals(showCountedFile) ? true : false;
    }

    public String getPath() {
        return path;
    }

    public String[] getFileTypes() {
        return fileTypes;
    }

    public String[] getExclusions() {
        return exclusions;
    }

    public boolean isShowCountedFile() {
        return showCountedFile;
    }

    public static void main(String[] args)throws Exception {
        new PropertyUtil();
    }

    public int getBarCount() {
        return barCount;
    }
    public String getSplitFolder() {
        return splitFolder;
    }
}
