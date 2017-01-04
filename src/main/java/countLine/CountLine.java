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
public class CountLine {

    private static String position;

    private static String regexString;

    /** Code */
    private static long normalLines = 0;

    /** Comment */
    private static long commentLines = 0;

    /** Space */
    private static long whiteLines = 0;

    private CountLine() {

    }

    public static void main(String[] args) throws Exception {
        countLines(new PropertyUtil());
        System.out.println("Position:" + position);
        System.out.println("Regex:   " + regexString);
        System.out.println("Code:    " + normalLines);
        System.out.println("Comment: " + commentLines);
        System.out.println("Space:   " + whiteLines);
    }

    /**
     * Convert an array to some string regex, for instance,
     * ".*\\.java$||.*\\.js$||.*\\.jsp$"
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
        regexString = regex.toString();
        return regex.toString();
    }

    private static void countLines(PropertyUtil propertyUtil) throws Exception {
        position = propertyUtil.getPath();
        File file = new File(position);
        File[] codeFiles = IOUtil.findByRegex(file, convertFileTypesToRegex(propertyUtil.getFileTypes()));
        for (File child : codeFiles) {
            boolean excluded = false;
            for (String exStr : propertyUtil.getExclusions()) {
                if (child.getAbsolutePath().contains(exStr)) {
                    excluded = true;
                }
            }
            if (!excluded) {
                if (propertyUtil.isShowCountedFile()) {
                    System.out.println(child.getAbsolutePath());
                }
                parse(child);
            }
        }
        showFileCount();
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
