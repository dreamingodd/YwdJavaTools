package countLine;

// package tarena.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * <p>
 * IO工具类
 * </p>
 * <p>
 * IO工具
 * </p>
 * 
 * 
 * @version 1.0
 * @author Ye_WD 2012-10-16
 * 
 */
public class IOUtil {

    private IOUtil() {
    }

    public static File[] findByFilter(File dir, FileFilter filter) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return new File[0]; //
        }
        ArrayList<File> list = new ArrayList<File>();
        for (File f : files) {
            if (f.isFile()) {
                if (filter.accept(f)) { //
                    list.add(f);
                }
            } else {
                File[] arr = findByFilter(f, filter);
                Collections.addAll(list, arr);
            }
        }
        return list.toArray(new File[0]);
    }

    public static File[] findByRegex(File dir, String pattern) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return new File[0]; // ���ؿ������ʾû���ļ�
        }
        ArrayList<File> list = new ArrayList<File>();
        for (File f : files) {
            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    list.add(f); // ƥ����ļ����뼯��
                }
            } else {
                // �ݹ���Ŀ¼f����,����Ŀ¼�ҵ��������ļ����뼯��
                File[] arr = findByRegex(f, pattern);
                Collections.addAll(list, arr);
            }
        }
        return list.toArray(new File[0]);
    }

    public static boolean dirDelete(File dir) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return dir.delete(); // ��Ŀ¼ֱ��ɾ
        }
        for (File f : files) {
            if (f.isFile()) {
                if (!f.delete()) { // ĳ���ļ�ɾ����
                    return false;
                }
            } else {
                // �ݹ�ɾ��Ŀ¼f
                if (!dirDelete(f)) { // ĳ��Ŀ¼ɾ����
                    return false;
                }
            }
        }
        // dir����������ɾ���ɾ��dir
        return dir.delete();
    }

    public static long dirLength(File dir) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return 0;
        }
        long size = 0;
        for (File f : files) {
            if (f.isFile()) {
                size += f.length();
            } else {
                // ��Ŀ¼f�Ĵ�С
                size += dirLength(f); // �ݹ�
            }
        }
        return size;
    }

    public static void main(String[] args) {
        // long size =
        // IOUtil.dirLength(new File("D:/spring"));
        // System.out.println(size);

        // boolean b =
        // IOUtil.dirDelete(new File("D:/ce"));
        // System.out.println(b);

        // File[] arr = IOUtil.findByRegex(
        // new File("c:/windows"),
        // ".*\\.[bB][mM][pP]$"); // .*\.[bB][mM][pP]$ // Bmp BMP bMp
        // for (File f : arr) {
        // System.out.println(f.getName());
        // }

        /**
         * 
         class SizeFilter implements FileFilter { public boolean accept(File
         * f) { if (f.length() >= 1024 * 1024) { return true; } else { return
         * false; } } } File[] arr = IOUtil.findByFilter(new File("c:/windows"),
         * new SizeFilter()); for (File f : arr) {
         * System.out.println(f.getName() + " - " + f.length()); }
         */
        System.out.println(getSrcPath());
    }

    // 项目文件夹
    public static String getSrcPath() {
        String src = System.getProperty("user.dir");
        src += "/src";
        return src;
    }

    // 类文件夹
    public static String getThisClassPath(String className) {
        String src = getSrcPath();
        String classPath = className.substring(0, className.lastIndexOf('.'));
        classPath = classPath.replace('.', '/');
        String path = src + "/" + classPath;
        return path;
    }
}
