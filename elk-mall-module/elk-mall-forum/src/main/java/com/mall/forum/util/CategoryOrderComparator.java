package com.mall.forum.util;

import java.util.Comparator;

import com.mall.forum.modle.Category;

public class CategoryOrderComparator implements Comparator<Category>{

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Category c1, Category c2)
    {
        if (c1.getDisplayOrder() > c2.getDisplayOrder()) {
            return 1;
        }
        else if (c1.getDisplayOrder() < c2.getDisplayOrder() ) {
            return -1;
        }
        else {
            return c1.getName().compareTo(c2.getName());
        }
    }
}
