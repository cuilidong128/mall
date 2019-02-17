package com.mall.forum.service.impl;

import org.apache.commons.lang.StringUtils;
import com.mall.forum.dao.ForumDao;
import com.mall.forum.dao.GroupDao;
import com.mall.forum.modle.Forum;
import com.mall.forum.modle.Group;
import com.mall.forum.service.ForumService;
import com.mall.forum.service.GroupService;
import com.mall.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/3.
 */
@Service("forumService")
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumDao forumDao;

    @Transactional(readOnly = true)
    public List<Forum> selectAll(){
        return forumDao.selectAll();
    }

    /**
     * 添加论坛
     * @param forum
     */
    public void add(Forum forum) {
        this.applyCommonConstraints(forum);

        if (forum.getId() > 0) {
            throw new BusinessException("This appears to be an existing forum (id > 0). Please use update() instead.");
        }

        this.forumDao.addNew(forum);
    }

    /**
     * Updates the information of an existing forum
     * @param forum
     */
    public void update(Forum forum) {
        this.applyCommonConstraints(forum);

        if (forum.getId() == 0) {
            throw new BusinessException("update() expects a forum with an existing id");
        }

        Forum currentForum = this.forumDao.selectById(forum.getId());

        currentForum.setName(forum.getName());
        currentForum.setDescription(forum.getDescription());
        currentForum.setModerated(forum.isModerated());
        currentForum.setAllowAnonymousPosts(forum.isAllowAnonymousPosts());
        currentForum.setCategory(forum.getCategory());

        this.forumDao.update(currentForum);
    }

    /**
     * Changes the forum order one level up
     * @param forumId
     */
    public void upForumOrder(int forumId) {
        this.processOrdering(true, forumId);
    }

    /**
     * Changes the forum order one level down
     * @param forumId
     */
    public void downForumOrder(int forumId) {
        this.processOrdering(false, forumId);
    }

    public void delete(int... ids) {
        if (ids != null) {
            for (int id : ids) {
                Forum forum = this.forumDao.selectById(id);

                this.forumDao.delete(forum);
            }
        }
    }


    private void processOrdering(boolean up, int forumId) {
        Forum toChange = this.forumDao.selectById(forumId);
        List<Forum> forums = toChange.getCategory().getForums();

        int index = forums.indexOf(toChange);

        if (index > -1 && (up && index > 0) || (!up && index + 1 < forums.size())) {
            Forum otherForum = up ? forums.get(index - 1) : forums.get(index + 1);

            int oldOrder = toChange.getDisplayOrder();

            toChange.setDisplayOrder(otherForum.getDisplayOrder());
            otherForum.setDisplayOrder(oldOrder);

            this.forumDao.update(toChange);
            this.forumDao.update(otherForum);
        }
    }

    private void applyCommonConstraints(Forum forum) {
        if (forum == null) {
            throw new NullPointerException("Cannot save a null forum");
        }

        if (forum.getCategory() == null || forum.getCategory().getId() == 0) {
            throw new BusinessException("A forum must be associated to a category");
        }

        if (StringUtils.isEmpty(forum.getName())) {
            throw new BusinessException("A forum must have a name");
        }
    }

}
