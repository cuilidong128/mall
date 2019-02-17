package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Group;
import com.mall.forum.modle.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface UserDao  extends BaseDao<User> {

    public User findByEmail(@Param("email") String email);

    public boolean isUsernameAvailable(@Param("username") String username, @Param("email") String email);

    public Integer getTotalUnreadPrivateMessages(@Param("userId") Integer userId);

    public List<User> findByUserName(@Param("username") String username);

    public User validateLogin(@Param("username") String username, @Param("password") String password);

}
