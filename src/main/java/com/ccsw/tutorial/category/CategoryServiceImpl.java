package com.ccsw.tutorial.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
//    private long SEQUENCE = 1;
//    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Category get(Long id) {

        return this.categoryRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    public List<Category> findAll() {

        // return new ArrayList<CategoryDto>(this.categories.values());
        return (List<Category>) categoryRepository.findAll();

    }

    /**
     * {@inheritDoc}
     * 
     * @throws Exception
     */
    public void save(Long id, CategoryDto dto) throws Exception {
        Category category;

        if (id == null) {
            category = new Category();
        } else {
            // category = categoryRepository.findById(id).get();// throws
            // NoSuchElementException if not present
            category = categoryRepository.findById(id).orElse(null);
            if (category == null)
                throw new Exception("Not found id " + id);
        }
        category.setName(dto.getName());
        categoryRepository.save(category);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) throws Exception {
        // this.categories.remove(id);
        if (categoryRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not found id " + id);
        }
        categoryRepository.deleteById(id);
    }
}
