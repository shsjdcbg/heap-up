package pers.dyx.tool;

import java.io.File;

/**
 * Description：Maven本地仓库越用越大，删除无效版本文件
 * 从各个maven库下载下的jar越来越多，同时由于网络或其他问题导致jar没下载下来，徒留一堆无用非jar文件。另外还有许多自己本地打包的无效版本等等。
 * <p>
 * 现使用如下代码直接将不包含jar包的版本下文件统统删掉。
 *
 * @author dyx
 * @date 2019/7/16 18:53
 */
public class DeleteUselessRepository {

    private static final String MAVEN_PATH = "E:\\java\\repository";

    /**
     * 判断是否存在jar
     *
     * @param file
     * @return
     * @author xfcyzq
     * @version 1.0
     */
    private static boolean judge(File file) {
        boolean isHaveJar = false;
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File efile : files) {
                if (efile.getName().endsWith(".jar")) {
                    isHaveJar = true;
                }
                if (efile.isDirectory()) {
                    boolean isNextHaveJar = judge(efile);
                    if (isNextHaveJar) {
                        isHaveJar = true;
                    }
                }
            }
        }
        if (!isHaveJar) {
            delete(file);
        }
        return isHaveJar;
    }

    /**
     * 删除操作
     *
     * @param file
     * @author xfcyzq
     * @version 1.0
     */
    public static void delete(File file) {
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    delete(f);
                }
                f.delete();
                System.out.println("已删除：" + f.getAbsolutePath());
            }
        } else {
            file.delete();
            System.out.println("已删除：" + file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        File mavenRoot = new File(MAVEN_PATH);
        if (mavenRoot.exists()) {
            File[] files = mavenRoot.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    judge(file);
                }
            }
        }
    }
}
