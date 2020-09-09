package cn.itcast.service;

import cn.itcast.domain.User;

public interface UserService {
    /**
     * 查询所有
     * @return
     */
    public boolean findByOne(User user);

    /**
     * 根据姓名查找
     */
    public boolean findByname(String username);

    /**
     * 保存用户
     * @param user
     */
    public void saveUser(User user);
}
