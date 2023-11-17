package com.ccsw.tutorial.category;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//http://localhost:8080/swagger-ui/index.html
//https://ccsw-csd.github.io/tutorial/site/dev/develop/basic/springboot/

@Tag(name = "Category", description = "API of Category")
@RequestMapping("/category")
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    DozerBeanMapper mapper;
//    @GetMapping
//    public String test() {
//        return "Hello from Category controller.";
//    }

    /**
     * Método para recuperar todas las categorias
     *
     * @return {@link List} de {@link CategoryDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Categories")
    @GetMapping(path = "")
    public List<CategoryDto> findAll() {

        List<Category> categories = categoryService.findAll();

        // testear mapper
        // CategoryDto dto = mapper.map(categories.get(0), CategoryDto.class);
        // Category cat = mapper.map(dto, Category.class);

        return categories.stream().map(c -> mapper.map(c, CategoryDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para recuperar todas las categorias
     * 
     * @param id PK de la entidad
     * @return {@link CategoryDto}
     */
    @Operation(summary = "Find", description = "Method that return a Category for the provided id")
    @GetMapping(path = "/{id}")
    public CategoryDto findById(@PathVariable("id") Long id) {

        return mapper.map(categoryService.get(id), CategoryDto.class);

    }

    /**
     * Método para crear o actualizar una categoria (/category y /category/1)
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Category")
    // @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    @PutMapping(path = { "", "/{id}" })
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CategoryDto dto)
            throws Exception {

        categoryService.save(id, dto);
    }

    /**
     * Método para borrar una categoria
     * 
     * @param id PK de la entidad a borrar
     * @throws Exception
     */
    @Operation(summary = "Delete", description = "Method that deletes a Category")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) throws Exception {
        categoryService.delete(id);
    }

}
