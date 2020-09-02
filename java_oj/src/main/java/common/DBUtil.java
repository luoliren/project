package common;



import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//借助这个类和数据建立连接，进一步操作数据库
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java10_oj?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "990305";
    private static volatile DataSource dataSource = null;

    //单例的懒汉模式
    private DBUtil(){

    }
    public static  DataSource getDataSource(){
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();                   //2  2.3.4.5.6
                    ((MysqlDataSource) dataSource).setURL(URL);           //3  目前这个单例懒汉模式是线程不安全的
                    ((MysqlDataSource) dataSource).setUser(USERNAME);     //4  所以做了加synchronized锁，保证线程安全
                    ((MysqlDataSource) dataSource).setPassword(PASSWORD); //5  由于是static静态方法所以针对类对象进行加锁.class
                }                                                         //6  这样的加锁就会导致锁的竞争很大，任何一次的getDataSource都会导致锁的等待开销
                                                                          //7  所以在这个基础之上外面在加一层if判断，毕竟只有dataSource==null才可能涉及到线程安全问题，
                                                                          //8  这里通过了双重if判断保证了线程安全，同时又保证了效率没有很低
                                                                          //9  private static volatile DataSource dataSource = null; 加volatile保证了当前dataSource内存的可见性
            }
        }
        return dataSource;
    }
    public static Connection getConnection(){
        try {
            //内置了数据库连接池
            return  getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection connection , PreparedStatement statement, ResultSet resultSet){

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

