package com.mall.forum.security;

import com.mall.forum.modle.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuilidong on 2019/3/3.
 */
public class TopicFilter {
    public List<Topic> filter(List<Topic> topics, RoleManager roleManager) {
        List<Topic> result = new ArrayList<Topic>();

        if (roleManager != null) {
            for (Topic topic : topics) {
                if (roleManager.isForumAllowed(topic.getForumId())) {
                    result.add(topic);
                }
            }
        }

        return result;
    }
}
