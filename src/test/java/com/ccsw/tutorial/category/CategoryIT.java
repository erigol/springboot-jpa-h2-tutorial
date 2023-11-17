package com.ccsw.tutorial.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.tutorial.category.model.CategoryDto;

import lombok.extern.java.Log;

/**
 * Integration tests
 * 
 * @author erigolpo
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

@Log
public class CategoryIT {
    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/category";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ParameterizedTypeReference<List<CategoryDto>> responseType = new ParameterizedTypeReference<List<CategoryDto>>() {
    };

    @Test
    public void findAllShouldReturnAllCategories() {

        ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH,
                HttpMethod.GET, null, responseType);
        assertNotNull(response);
        log.info(LOCALHOST + port + SERVICE_PATH + " response body: " + response.getBody());

        assertEquals(3, response.getBody().size());
    }
}

//@SpringBootTest. 
//Esta anotación lo que hace es inicializar el contexto de Spring cada vez que se inician los test de jUnit. Aunque el contexto tarda unos segundos en arrancar, lo bueno que tiene es que solo se inicializa una vez y se lanzan todos los jUnits en batería con el mismo contexto.
//@DirtiesContext.
//Esta anotación le indica a Spring que los test van a ser transaccionales, y por tanto cuando termine la ejecución de cada uno de los test, automáticamente por la anotación de arriba, Spring hará un rearranque parcial del contexto y dejará el estado de la BBDD como estaba inicialmente.
