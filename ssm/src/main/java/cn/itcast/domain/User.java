package cn.itcast.domain;


import javax.servlet.http.HttpSessionBindingEvent;
import java.io.Serializable;
import cn.itcast.domain.Constants;


public class User implements Serializable {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //当User对象放进session(一个用户登录)自动进入该方法
    public void valueBound(HttpSessionBindingEvent arg0) {
        // TODO Auto-generated method stub
        Constants.USER_COUNT++;
    }
    //session失效（某个用户注销）  超时  手动的session中把某个属性移除
    public void valueUnbound(HttpSessionBindingEvent arg0) {
        Constants.USER_COUNT--;

    }

}
