package com.mall.forum.admin;


import com.mall.common.util.ResponseMessage;
import com.mall.forum.core.helpers.Domain;
import com.mall.forum.modle.Category;
import com.mall.forum.service.CategoryService;
import com.mall.forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mall.common.util.ResponseMessage.ok;

@Controller
@RequestMapping(Domain.CATEGORIES_ADMIN)
public class CategoryAdminAction {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/list")
    public ResponseMessage<Map<String, Object>> listAll() {
        return this.list();
    }

    @RequestMapping("/init")
    public String init() {
        return "admin/index";
    }

    @PostMapping("/addSave")
    public ResponseMessage addSave(Category category) {
        this.categoryService.add(category);
        return ok();
    }

    public ResponseMessage<Map<String, Object>> editSave(Category category) {
        this.categoryService.update(category);
        return ok();
    }

    public ResponseMessage delete(int... categoriesId) {
        this.categoryService.delete(categoriesId);
        return ok();
    }

    private ResponseMessage<Map<String, Object>> list() {
        HashMap map = new HashMap();
        map.put("categories",this.categoryService.select());
        return ok(map);

        //this.result.include("categories", this.categoryRepository.getAllCategories());
    }

    public ResponseMessage<Map<String, Object>> up(int categoryId) {
        this.categoryService.upCategoryOrder(categoryId);
        return this.list();
    }

    /**
     * Changes the order of the specified category, adding it one level down.
     *
     * @param categoryId
     *            the id of the category to change
     */
    public ResponseMessage<Map<String, Object>> down(int categoryId) {
        this.categoryService.downCategoryOrder(categoryId);
        return this.list();
    }

}
