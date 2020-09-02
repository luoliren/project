package complie;

//描述了一次编译运行中都产生了哪些数据
public class Answer {
    //通过 error表示当前的错误类型
    //约定 error 为0 表示没错误，error 为1 表示编译出错 error为 2 表示运行出错

    private int error;
    //表示具体原因的出错，可能时便也出错，也可能是运行出错（异常信息）
    private String reason;
    //执行时标准输出对应的内容
    private String stdout;
    //执行时标准错误对应的内容
    private String stderr;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
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

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "error=" + error +
                ", reason='" + reason + '\'' +
                ", stdout='" + stdout + '\'' +
                ", stderr='" + stderr + '\'' +
                '}';
    }
}
