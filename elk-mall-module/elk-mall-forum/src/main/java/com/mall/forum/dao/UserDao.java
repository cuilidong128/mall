package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Group;
import com.mall.forum.modle.Post;
import com.mall.forum.modle.Topic;
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

    public int getTotalPosts(User user);

    public User getByUsername(String username);

    public void changeAllowAvatarState(boolean allowAvatar, Group group);

    public List<User> findByUserName(String username, List<Group> filterGroups);

    public List<User> getAllUsers(int start, int count);

    public List<User> getAllUsers(int start, int count, List<Group> filterGroups);

    public User getLastRegisteredUser();

    public int getTotalUsers();

    public User validateLostPasswordHash(String username, String hash);

    public int getTotalTopics(int userId);

    public List<Post> getPosts(User user, int start, int recordsPerPage);

    public List<Topic> getTopics(User user, int start, int recordsPerPage);
}
