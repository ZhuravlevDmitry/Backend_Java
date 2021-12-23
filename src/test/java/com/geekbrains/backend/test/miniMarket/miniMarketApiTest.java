package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Category;
import com.geekbrains.backend.test.miniMarket.model.Product;
import com.geekbrains.backend.test.miniMarket.model.ProductResponse;
import com.google.gson.Gson;
import lombok.val;
import org.junit.jupiter.api.*;


import java.io.IOException;
import java.util.List;

public class miniMarketApiTest {
    private static MiniMarketService apiService;
    private static Gson gson;
    private static Long id = 2L;

    @BeforeAll
    static void beforeAll() {
        apiService = new MiniMarketService();
        gson = new Gson();
    }

    @DisplayName("Тест на получение категории по id")
    @Test
    void testGetCategoryById() throws IOException {
        Category category = apiService.getCategory(2);

        Assertions.assertEquals(2L, category.getId());
        Assertions.assertEquals(4L, category.getProducts().get(0).getId());
        Assertions.assertEquals("Samsung Watch X1000", category.getProducts().get(0).getTitle());
        Assertions.assertEquals(20700, category.getProducts().get(0).getPrice());
        Assertions.assertEquals("Electronic", category.getProducts().get(0).getCategoryTitle());
        Assertions.assertEquals(115L, category.getProducts().get(1).getId());
        Assertions.assertEquals("Vacuum cleaner Bosh MX30", category.getProducts().get(1).getTitle());
        Assertions.assertEquals(23000, category.getProducts().get(1).getPrice());
        Assertions.assertEquals("Electronic", category.getProducts().get(1).getCategoryTitle());
        Assertions.assertEquals("Electronic", category.getTitle());
    }

    @DisplayName("Тест на получение категории по недействительному id")
    @Test
    void testGetCategoryByNonExistentId() throws IOException {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Category category = apiService.getCategory(5);
        });
    }

    @DisplayName("Тест на получение списка продуктов")
    @Test
    @Order(1)
    void testGetProducts() throws IOException {

        List<Product> products = apiService.getProducts();
    }

    @DisplayName("Тест на создание нового продукта")
    @Test
    @Order(2)
    void testCreateProduct() throws IOException {
        val product = Product.builder()
                .id(null)
                .title("Gingerbread")
                .price(2134)
                .categoryTitle("Food")
                .build();
        id = apiService.createProduct(product);
        ProductResponse expected = apiService.getProduct(id);
        Assertions.assertEquals(id, expected.getId());
    }

}

