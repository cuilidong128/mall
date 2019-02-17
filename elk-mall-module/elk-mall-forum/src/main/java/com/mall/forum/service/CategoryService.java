package com.mall.forum.service;

import com.mall.forum.modle.Category;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/8.
 */
public interface CategoryService {

    public List<Category> select();

    public void add(Category category);

    public void delete(int... ids);

    public void update(Category category);

    public void upCategoryOrder(int categoryId);

    public void downCategoryOrder(int categoryId);
}
