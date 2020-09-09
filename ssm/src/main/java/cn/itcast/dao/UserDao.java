package cn.itcast.dao;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户DAo接口
 */
@Repository
public interface UserDao {
    /**
     * 查询用户的身份信息
     * @return
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    public List<User> findByOne(User user);

    @Select("select * from user where username=#{username}")
    public List<User> findByname(String username);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    public void saveUser(User user);




}
