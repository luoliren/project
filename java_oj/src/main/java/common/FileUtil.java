package common;

import java.io.*;

//这是一个工具类，帮助我们更加方便的读写文件
public class FileUtil {
    //读文件： 一下把整个文件内容都读到String中
    public static String readFile(String filePath) {
        //当前涉及到编译错误，标准输出结果等文件内容都是文本文件，此处使用字符流的方式来实现

        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {//表示可以自动关闭的对象
             StringBuilder stringBuilder = new StringBuilder();
            //按行读取内容
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //写文件：一把整个String的内容都写进指定的文件中
    public static void writeFile(String filePath,String content){
        try(FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
