package countLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * 
 * <p>
 * 浠ｇ����璁＄袄1�7/p>
 * <p>
 * 杩�����璁＄����1�7���唬���锛����锛�java .xml .jsp ��ￄ1�7 绫诲ￄ1�7
 * 缁��src涓����欢澶�1�7�唬����ￄ1�7 锛������界�璁�rc浠ュ����浠跺す锛�ncluding
 * other source folder like test锛�
 * </p>
 * 
 * Project: Zpos_2.0.0 Copyright: @���杈版�淇℃����������
 * 
 * @version 1.0
 * @author Ye_WD 2012-10-16
 * 
 */
public class CountLinesTest {

    private CountLinesTest() {

    }

    /**
     * ����规�
     * 
     * @throws Exception
     */
    public static void countClassDirLines(String className) throws Exception {

        // 椤圭���欢澶�
        String project = System.getProperty("user.dir");

        // 绫绘�浠跺す
        String classPath = className.substring(0, className.lastIndexOf('.'));
        classPath = classPath.replace('.', '/');

        String path = project + "/src/" + classPath;

        System.out.println(path);
        File f = new File(path);
        countLines(f);
    }

    /** 绋�ￄ1�7 */
    static long normalLines = 0;

    /** 娉ㄩￄ1�7 */
    static long commentLines = 0;

    /** 绌虹ￄ1�7 */
    static long whiteLines = 0;

    public static void main(String[] args) throws Exception {
        // �峰�褰��java��欢璺�����寮ￄ1�7
        // System.out.println(new File("").getCanonicalPath());
        // //�峰������矾寰ￄ1�7
        // 椤圭�缁��
//        String path = System.getProperty("user.dir");
        String path = "/home/projects/UnderstandingJVM";
        System.out.println(path);
        countLines(new File(path));
    }

    private static void countLines(File file) throws Exception {

        // File[] codeFiles= file.listFiles();
        File[] codeFiles = IOUtil.findByRegex(file, "" + ".*\\.java$||"
                + ".*\\.js$||" + ".*\\.jsp$");
        for (File child : codeFiles) {
            // if(child.getName().matches(".*\\.java$")){
            parse(child);
            // }
        }
        System.out.println("Code:    " + normalLines);
        System.out.println("Comment: " + commentLines);
        System.out.println("Space:   " + whiteLines);
    }

    private static void parse(File f) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(f));
        String line = "";
        boolean comment = false;
        while ((line = in.readLine()) != null) {
            line = line.trim(); //
            if (line.matches("^[\\s&&[^\\n]]*$")
                    || line.equals("{") || line.equals("}")) {
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
                if (line.endsWith("*/") || line.endsWith("-->")
                        || line.endsWith("--%>")) {
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
