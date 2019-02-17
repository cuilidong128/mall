package com.mall.forum.service.impl;

import com.mall.common.exception.BusinessException;
import com.mall.forum.dao.CategoryDao;
import com.mall.forum.modle.Category;
import com.mall.forum.service.CategoryService;
import com.mall.forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/8.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    public CategoryDao categoryDao;

    public List<Category> select(){
        return this.categoryDao.selectAll();
    }

    public void add(Category category) {
        this.applyCommonConstraints(category);

        if (category.getId() > 0) {
            throw new BusinessException("This appears to be an existing category (id > 0). Please use update() instead");
        }
        category.setDisplayOrder(this.getMaxDisplayOrder());
        this.categoryDao.addNew(category);
    }

    public void delete(int... ids) {
        if (ids != null) {
            for (int id : ids) {
                Category c = this.categoryDao.selectById(id);
                this.categoryDao.delete(c);
            }
        }
    }

    public void update(Category category) {
        this.applyCommonConstraints(category);

        if (category.getId() == 0) {
            throw new BusinessException("update() expects a category with an existing id");
        }

        this.categoryDao.updateCategory(category);
    }



    /**
     * Changes the category order one level up
     * @param categoryId
     */
    public void upCategoryOrder(int categoryId) {
        this.processOrdering(true, categoryId);
    }

    /**
     * Changes the category order one level down
     * @param categoryId
     */
    public void downCategoryOrder(int categoryId) {
        this.processOrdering(false, categoryId);
    }


    private void processOrdering(boolean up, int categoryId) {
        Category toChange = this.categoryDao.selectById(categoryId);
        List<Category> categories = this.categoryDao.selectAll();

        int index = categories.indexOf(toChange);

        if (index > -1 && (up && index > 0) || (!up && index + 1 < categories.size())) {
            Category otherCategory = up ? categories.get(index - 1) : categories.get(index + 1);

            int oldOrder = toChange.getDisplayOrder();

            toChange.setDisplayOrder(otherCategory.getDisplayOrder());
            otherCategory.setDisplayOrder(oldOrder);

            this.categoryDao.updateCategory(toChange);
            this.categoryDao.updateCategory(otherCategory);
        }
    }

    private void applyCommonConstraints(Category c) {
        if (c == null) {
            throw new NullPointerException("Cannot save a null category");
        }

        if (StringUtils.isEmpty(c.getName())) {
            throw new BusinessException("Category name cannot be blank or null");
        }
    }

    private int getMaxDisplayOrder() {
        Integer displayOrder = this.categoryDao.getMaxOrder();
        return displayOrder == 0 ? 1 : displayOrder + 1;
    }
}
