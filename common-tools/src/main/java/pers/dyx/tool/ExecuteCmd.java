package pers.dyx.tool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;

/**
 * 执行cmd外部程序，并获取标准输出
 *
 * @author dyx
 * @date 2020/9/310:14
 */
public class ExecuteCmd {

    /**
     * 执行外部程序,并获取标准输出
     */
    public static String execute(String[] cmd, String... encoding) {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        try {
            Process p = Runtime.getRuntime().exec(cmd);

//            //为"错误输出流"单独开一个线程读取之,否则会造成标准输出流的阻塞
//            Thread t = new Thread(new InputStreamRunnable(p.getErrorStream(), "ErrorStream"));
//            t.start();

            // "标准输出流"就在当前方法中读取
            BufferedInputStream bis = new BufferedInputStream(p.getInputStream());

            if (encoding != null && encoding.length != 0) {
                // 设置编码方式
                inputStreamReader = new InputStreamReader(bis, encoding[0]);
            } else {
                inputStreamReader = new InputStreamReader(bis, StandardCharsets.UTF_8);
            }
            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            bufferedReader.close();
            inputStreamReader.close();
            p.destroy();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(execute(new String[]{"jinfo", "-flags", getPid()}));
    }

    /**
     * 获取当前应用进程id
     *
     * @return 进程id
     */
    public static String getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        return name.split("@")[0];
    }


}
