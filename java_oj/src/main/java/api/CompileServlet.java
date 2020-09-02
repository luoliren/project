package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import complie.Answer;
import complie.Question;
import complie.Task;
import problem.Problem;
import problem.ProblemDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class CompileServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    //创建两个辅助的类用来完成请求解析和相应的构建
    //这个类用于辅助解析body中的数据请求
    static class CompileRequest{
        private int id;
        private String code;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
    //这个类用于辅助构建最终的响应的body数据

    static class CompileResponse{
        private int ok;
        private String reason;
        private String stdout;

        public int getOk() {
            return ok;
        }

        public void setOk(int ok) {
            this.ok = ok;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getStdout() {
            return stdout;
        }

        public void setStdout(String stdout) {
            this.stdout = stdout;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.读取请求的body的所有数据
        String body = readBody(req);
        //2.按照API约定的格式来解析JSON数据，得到CompileRequest对象
        CompileRequest compileRequest = gson.fromJson(body,CompileRequest.class);
        //3.按照id从数据库读取对应的测试用例代码
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(compileRequest.getId());
        //得到该题目的测试代码
        String testCode = problem.getTestCode();
        //得到该题目的用户输入的代码
        String requestCode = compileRequest.getCode();
        //4.把用户输入的代码和测试用例代码进行组装，组装成一个完整的可以编译运行的代码
        String finalCode = mergeCode(requestCode,testCode);
        //5.创建Task对象对刚才组装好的代码进行编译运行
        Question question = new Question();
        question.setCode(finalCode);
        question.setStdin("");
        Task task = new Task();
        Answer answer = null;
        try {
             answer = task.compileAndRun(question);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //6.把运行结果构造成响应数据，并写回给客户端
        CompileResponse compileResponse = new CompileResponse();
        compileResponse.setOk(answer.getError());
        compileResponse.setReason(answer.getReason());
        compileResponse.setStdout(answer.getStdout());
        String jsonString = gson.toJson(compileResponse);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    private String mergeCode(String requestCode, String testCode) {
        //合并之前需要先考虑清楚这两个部分的代码都是啥样的
        //把testCode中的main方法内容嵌入到requestCode中
        //1.先找到requestCode中的最后一个}
        //2.把最后一个}干掉之后，和testCode进行字符产拼接
        //3.拼接完毕之后，再最后补上一个}
        int pos = requestCode.lastIndexOf("}");
        if (pos == -1){
            //此时requestCode
            return null;
        }
        //此处取得子串不包含pos位置元素
        return requestCode.substring(0,pos) + testCode + "\n}";
    }

    private String readBody(HttpServletRequest req) {
        //body的长度在header中的一个Content-length字段中
        //contentLength 单位就是字节
        int contentLength = req.getContentLength();
        byte [] buf = new byte[contentLength];
        try( InputStream inputStream = req.getInputStream();) {
            inputStream.read(buf,0,contentLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buf);
    }
}
