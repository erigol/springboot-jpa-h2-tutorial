package com.ccsw.tutorial.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.category.model.Category;

@ExtendWith(MockitoExtension.class)
//Esta anotación le indica a Spring que no debe inicializar el contexto, 
//ya que se trata de test estáticos que no lo requieren.
public class CategoryTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryService;

    // Listado
    @Test
    public void findAllShouldReturnAllCategories() {
        // mock repository
        List<Category> expected = new ArrayList<>();
        expected.add(mock(Category.class));

        when(categoryRepository.findAll()).thenReturn(expected);

        // service see if actual = expected
        List<Category> actual = categoryService.findAll();
        assertNotNull(actual);

        assertEquals(1, actual.size());
        assertNotNull(actual.get(0));

    }
}
