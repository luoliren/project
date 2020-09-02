package complie;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//借助这个类，让我们的java代码能够执行一个具体的指令
//例如 javac Test.java
public class CommandUtil {
    //cmd 表示要职的命令
    //stdoutFile 表示标准输出结果重定向到那个文件中 ，如果为null 表示不需要重定向
    //stderrFile 表示标准错误结果重定向到那个文件
    public static int run(String cmd,String stdoutFile,String stderrFile) throws IOException, InterruptedException {
        //1.获取runtime对象,runtime 对象是个单例模式
        Runtime runtime = Runtime.getRuntime();

        //2.通过runtime对象中的exec方法来之一个指令
        //相当于在命令行 中输入cmd命令并执行
        Process process = runtime.exec(cmd);//runtime.exec(cmd)子进程
        //针对表针输出进行重定向
        if (stdoutFile != null){
            //进程的标准输出中的结果就可以通过这个InputStream获取到
            InputStream stdoutFrom= process.getInputStream();
            OutputStream stdoutTo = new FileOutputStream(stdoutFile);
            int ch = -1;
            while ((ch = stdoutFrom.read()) != -1){
                stdoutTo.write(ch);
            }
            stdoutFrom.close();
            stdoutTo.close();
        }
        //4.针对标准错误也进行重定向
        if (stderrFile != null) {
            InputStream stderrFrom = process.getErrorStream();
            OutputStream stderrTo = new FileOutputStream(stderrFile);

            int ch = -1;
            while ((ch = stderrFrom.read()) != -1){
                stderrTo.write(ch);
            }
            stderrFrom.close();
            stderrTo.close();
        }
        //5.为了确保 子进程 先执行完 ，需要加上进程等待
        // 父进程会在waitFor 阻塞等待，知道子进程执行完成，在继续执行下去
        int exitCode = process.waitFor(); //java1号进程获取到的java2号的进程的退出码
        return exitCode;


    }

    public static void main(String[] args) throws IOException, InterruptedException {
        run("javac","d:/stdout.txt","d:/stderr.txt");
    }
}
