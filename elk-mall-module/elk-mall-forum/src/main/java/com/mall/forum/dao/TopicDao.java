package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Post;
import com.mall.forum.modle.Smilie;
import com.mall.forum.modle.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/24.
 */
public interface TopicDao extends BaseDao<Topic> {

    public void delete(@Param("topicId") int topicId);

    public Post getLastPost(Topic topic);

    public int getTotalPosts(Topic topic) ;

    public Post getFirstPost(Topic topic);

    public List<Post> getPosts(@Param("topicId") int topicId);

}
