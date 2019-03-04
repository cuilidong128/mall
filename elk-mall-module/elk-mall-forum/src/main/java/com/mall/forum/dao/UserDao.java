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


    public User getByEmail(@Param("email") String email);

    public boolean isUsernameAvailable(@Param("username") String username, @Param("email") String email);

    /**
     * 获取用户未读信息数量
     * @param userId
     * @return
     */
    public Integer getTotalUnreadPrivateMessages(@Param("userId") Integer userId);

    /**
     * 获取用户总贴数
     * @param userId
     * @return
     */
    public Integer getTotalPosts(@Param("userId") Integer userId);

    public List<User> getByUsername(@Param("username") String username);

    public void changeAllowAvatarState(@Param("allowAvatar") boolean allowAvatar, Group group);

    public User validateLogin(@Param("username") String username, @Param("password") String password);


}
