package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/3.
 */
public interface ForumDao extends BaseDao<Forum> {

    public List<Forum> selectAll();

    public Forum selectById(@Param("forumId") int forumId);

    public void addNew(Forum forum);

    /**
     * 以下两个调整一样
     * @param forumId
     * @return
     */
    Integer getTotalPosts(@Param("forumId") Integer forumId);
    public int getTotalPosts(Forum forum);
    public int getTotalMessages();

    /**
     * 待开发
     */


    /**
     * 移动论坛主题，讲主题移动到其它论坛
     * @param toForum
     * @param topicIds
     */
    public void moveTopics(Forum toForum, int... topicIds);


    /**
     * 论坛的版主
     * @param forum
     * @return
     */
    public List<Group> getModerators(Forum forum);

    /**
     * 待审核的主题
     * @param forum
     * @return
     */
    public List<TopicDao> getTopicsPendingModeration(Forum forum);

    /**
     * 末尾帖
     * @param forum
     * @return
     */

    public Post getLastPost(Forum forum);


    public int getTotalTopics(Forum forum);

    public List<TopicDao> getTopics(Forum forum, int startFrom, int count);

    //public PaginatedResult<Topic> getNewMessages(Date from, int start, int recordsPerPage)

    /**
     * 论坛统计
     * @return
     */
    public ForumStats getForumStats();

    public ForumStats getForumStats(List<Group> groups);
}
