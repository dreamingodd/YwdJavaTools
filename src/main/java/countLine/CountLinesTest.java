package countLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 
 * <p>
 * Code line counter
 * </p>
 * <p>
 * Run this class you will see the result.
 * </p>
 * 
 * @version 1.0
 * @author Ye_WD
 * @date 2012-10-16
 * 
 * @date 2016-11-04
 *       <p>
 *       Extract path and file types as configurable arguments.
 *       </p>
 * @date 2016-11-04
 *       <p>
 *       Extract path and file types as configurable arguments.
 *       </p>
 * 
 */
public class CountLinesTest {

    // Arguments
    // public static String path = "C:\\Users\\Administrator\\workspace\\spring_boot_test_1";
//    public static String path = "E:\\project\\choosefine_market";
    public static String path = "C:\\Development\\project\\github\\YwdJavaTools";
//    public static String path = "E:\\project\\choosefine_market\\shopping\\src\\test\\java\\com\\choosefine\\shopping\\merchandise";
//    public static String path = "E:\\project_research\\liquibase";
//    public static String path = "E:\\project\\choosefine_base";
    public static String[] fileTypes = { "java", "xml", "sql" };
    public static String[] exclusions = { "target", "model", ".idea" };
    public static boolean showCountedFile = false;

    private CountLinesTest() {

    }

    public static void countClassDirLines(String className) throws Exception {

        // System path
        String project = System.getProperty("user.dir");

        // get current project path
        String classPath = className.substring(0, className.lastIndexOf('.'));
        classPath = classPath.replace('.', '/');

        String thisPath = project + "/src/" + classPath;

        if (path == null || "".equals(path)) {
            path = thisPath;
        }

        System.out.println(path);
        File root = new File(path);
        countLines(root);
    }

    /** Code */
    static long normalLines = 0;

    /** Comment */
    static long commentLines = 0;

    /** Space */
    static long whiteLines = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("Position:" + path);
        countLines(new File(path));
    }

    /**
     * Convert an array to some string regex, for instance,
     * ".*\\.java$||.*\\.js$||.*\\.jsp$"
     * 
     * @param fileTypes
     * @return fileType regex
     */
    public static String convertFileTypesToRegex(String[] fileTypes) {
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < fileTypes.length; i++) {
            if (i == fileTypes.length - 1) {
                regex.append(".*\\.").append(fileTypes[i]).append("$");
            } else {
                regex.append(".*\\.").append(fileTypes[i]).append("$||");
            }
        }
        System.out.println("Regex:   " + regex.toString());
        return regex.toString();
    }

    private static void countLines(File file) throws Exception {

        File[] codeFiles = IOUtil.findByRegex(file, convertFileTypesToRegex(fileTypes));
        for (File child : codeFiles) {
            boolean excluded = false;
            for (String exStr : exclusions) {
                if (child.getAbsolutePath().contains(exStr)) {
                    excluded = true;
                }
            }
            if (!excluded) {
                if (showCountedFile) {
                    System.out.println(child.getAbsolutePath());
                }
                parse(child);
            }
        }
        showFileCount();
        System.out.println("Code:    " + normalLines);
        System.out.println("Comment: " + commentLines);
        System.out.println("Space:   " + whiteLines);
    }

    private static void showFileCount() {
        
    }
    
    private static void parse(File f) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(f));
        String line = "";
        boolean comment = false;
        while ((line = in.readLine()) != null) {
            line = line.trim(); //
            if (line.matches("^[\\s&&[^\\n]]*$") || line.equals("{") || line.equals("}")) {
                // System.out.println(line);
                whiteLines++;
            } else if (line.startsWith("<!--") && !line.endsWith("-->")) {
                commentLines++;
                comment = true;
            } else if ((line.startsWith("/*") && line.endsWith("*/"))
                    || (line.startsWith("<%--") && line.endsWith("--%>"))
                    || (line.startsWith("<!--") && line.endsWith("-->"))) {
                commentLines++;
            } else if ((line.startsWith("/*") && !line.endsWith("*/"))
                    || (line.startsWith("<%--") && !line.endsWith("--%>"))) {
                commentLines++;
                comment = true;
            } else if (true == comment) {
                commentLines++;
                if (line.endsWith("*/") || line.endsWith("-->") || line.endsWith("--%>")) {
                    comment = false;
                }
            } else if (line.startsWith("//")) {
                commentLines++;
            } else {
                normalLines++;
            }
        }
        in.close();
    }
}
