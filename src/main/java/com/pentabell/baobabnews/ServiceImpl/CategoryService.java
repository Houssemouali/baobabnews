package com.pentabell.baobabnews.ServiceImpl;

import com.pentabell.baobabnews.Repositories.CategoryRepository;
import com.pentabell.baobabnews.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService {

    private final List<Category> categoryList = new ArrayList<>();


    @Autowired
    private CategoryRepository catrep;

    public Category getCatbyId(Long catID) {
        return categoryList.stream().filter(
                (category) -> category.getId() == catID
        ).findFirst().get();
    }

}
