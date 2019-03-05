package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Attachment;
import com.mall.forum.modle.Category;
import com.mall.forum.modle.Forum;
import com.mall.forum.modle.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/7.
 */
public interface CategoryDao extends BaseDao<Category> {


    public void addNew(Category category);

    public void updateCategory(Category category);

    public List<Category> selectAll();

    public Category selectById(@Param("categoryId") int categoryId);

    public boolean canDelete(@Param("categoryId") int categoryId);

    public void delete(@Param("categoryId") int categoryId);

    public int getMaxOrder();


    /**
     * 待开发
     * @return
     */
    public  List<Forum> getForums(@Param("categoryId") int categoryId) ;

}
