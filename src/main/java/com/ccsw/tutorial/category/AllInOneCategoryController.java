package com.ccsw.tutorial.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ccsw.tutorial.category.model.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//http://localhost:8080/swagger-ui/index.html
//https://ccsw-csd.github.io/tutorial/site/dev/develop/basic/springboot/

@Tag(name = "Category", description = "API of Category")
//@RequestMapping("/category")
//@RestController
//@CrossOrigin(origins = "*")
public class AllInOneCategoryController {

    private long SEQUENCE = 1;
    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();

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

        return new ArrayList<CategoryDto>(this.categories.values());
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
        return this.categories.get(id);
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
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody CategoryDto dto) {

        CategoryDto category;

        if (id == null) {
            category = new CategoryDto();
            category.setId(this.SEQUENCE++);
            this.categories.put(category.getId(), category);
        } else {
            category = this.categories.get(id);
        }
        if (category != null)
            category.setName(dto.getName());
    }

    /**
     * Método para borrar una categoria
     * 
     * @param id PK de la entidad a borrar
     */
    @Operation(summary = "Delete", description = "Method that deletes a Category")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        this.categories.remove(id);
    }

}
