package countLine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Load properties.properties.
 * @Author Ye_Wenda
 * @Date 1/4/2017
 *
 */
public class PropertyUtil {

    public static final String propertyRelativePath = "\\src\\main\\resources\\properties.properties";

    private String path;
    private String[] fileTypes;
    private String[] exclusions;
    private boolean showCountedFile;

    public PropertyUtil() {
        String sysPath = System.getProperty("user.dir");
        String propertyPath = sysPath + propertyRelativePath;
        collectProperties(propertyPath);
    }

    private void collectProperties(String propertyPath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertyPath));
        } catch (FileNotFoundException e) {
            System.out.println("Property file not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
        String path = (String) properties.get("path");
        String fileTypes = (String) properties.get("fileTypes");
        String exclusions = (String) properties.get("exclusions");
        String showCountedFile = (String) properties.get("showCountedFile");
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
}
