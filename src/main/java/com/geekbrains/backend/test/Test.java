package com.geekbrains.backend.test;

import com.geekbrains.backend.test.miniMarket.MiniMarketService;
import com.geekbrains.backend.test.miniMarket.model.Category;
import com.geekbrains.backend.test.miniMarket.model.Product;
import com.geekbrains.backend.test.miniMarket.model.ProductResponse;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        MiniMarketService service = new MiniMarketService();
        List<Product> products = service.getProducts();
        System.out.println(products);
        ProductResponse product = service.getProduct(3L);
        System.out.println(product);
        Category category = service.getCategory(2L);
        System.out.println(category);
        //  service.deleteProduct(101L);
        service.updateProduct
                (Product.builder()
                        .id(4L)
                        .title("Samsung Watch X1000")
                        .price(20700)
                        .categoryTitle("Electronic")
                        .build());

        Category categoryForUpdate = service.getCategory(2L);
        System.out.println(categoryForUpdate);

        service.createProduct
                (Product.builder()
                        .id(null)
                        .title("Vacuum cleaner Bosh ZAS3000")
                        .price(12500)
                        .categoryTitle("Electronic")
                        .build());

        Category categoryForUpdate1 = service.getCategory(2L);
        System.out.println(categoryForUpdate1);
    }
}
