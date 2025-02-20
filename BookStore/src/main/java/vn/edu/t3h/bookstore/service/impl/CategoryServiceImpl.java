package vn.edu.t3h.bookstore.service.impl;

import vn.edu.t3h.bookstore.dao.CategoryDao;
import vn.edu.t3h.bookstore.model.Category;
import vn.edu.t3h.bookstore.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategory();
    }
}
