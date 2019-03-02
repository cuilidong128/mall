package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Post;
import com.mall.forum.modle.PostReport;
import com.mall.forum.modle.PostReportStatus;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/24.
 */
public interface PostReporDao extends BaseDao<PostReport> {

    public List<PostReport> getAll(PostReportStatus status, int... forumIds);

    public int countPendingReports(int... forumIds);
}
