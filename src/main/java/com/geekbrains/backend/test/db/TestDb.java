package com.geekbrains.backend.test.db;

import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Categories;
import com.geekbrains.db.model.CategoriesExample;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;

import java.util.List;

public class TestDb {
    public static void main(String[] args) {
        DbService dbService = new DbService();
        ProductsMapper productsMapper = dbService.getProductsMapper(); // положили в productsMapper полученный продукты
        Products product = productsMapper.selectByPrimaryKey(1L);// Получить продукт из productsMapper по ключу - ИД
        System.out.println(product);

        Products forCreate = new Products(); // Инициализировали  forCreate куда передадим параметры
        forCreate.setTitle("Coca cola");
        forCreate.setPrice(50);
        forCreate.setCategoryId(1L);
       // productsMapper.insert(forCreate); // Положим новую сущность в базу при этом ИД генерится сам

        ProductsExample filter = new ProductsExample();
        List<Products> products = productsMapper.selectByExample(filter);
        System.out.println(productsMapper.selectByExample(filter));

        filter.createCriteria()
                .andCategoryIdEqualTo(2L);

        System.out.println(products);

        filter.clear();
        filter.createCriteria()
                .andPriceBetween(51,1000);
        System.out.println(productsMapper.selectByExample(filter));

        Products productUpdateCheese = productsMapper.selectByPrimaryKey(3L);
        productUpdateCheese.setPrice(420);
        productsMapper.updateByPrimaryKey(productUpdateCheese);
        System.out.println(productsMapper.selectByPrimaryKey(3L));


        CategoriesMapper categoryMapper = dbService.getCategoriesMapper();
//        Categories category = categoryMapper.selectByPrimaryKey(3L);
//        System.out.println(category);


        // Создать 4 категории

        String[] categoryName = {"Cars", "Books", "Pizza", "Mebel"};
        Categories createCategories = new Categories();
        for (int i = 0; i <= 3; i++) {
            createCategories.setTitle(categoryName[i]);
            categoryMapper.insert(createCategories);
        }

        //Создать 3 продукта в каждой категории

        String[] carsType = {"BMW", "Audi", "Mercedes"};
        int[] priceCarsType = {6000000, 7000000, 8000000};

        for (int i = 0; i < 3; i++) {
            forCreate.setTitle(carsType[i]);
            forCreate.setPrice(priceCarsType[i]);
            forCreate.setCategoryId(3L);
            productsMapper.insert(forCreate);
        }

        String[] booksGenre = {"Fantastic", "Detectives", "Internet"};
        int[] priceBooksGenre = {1500, 1200, 1800};

        for (int i = 0; i < 3; i++) {
            forCreate.setTitle(booksGenre[i]);
            forCreate.setPrice(priceBooksGenre[i]);
            forCreate.setCategoryId(4L);
            productsMapper.insert(forCreate);
        }

        String[] pizzaType = {"Carbonara", "Pepperoni", "Margaret"};
        int[] pricePizzaType = {700, 600, 640};

        for (int i = 0; i < 3; i++) {
            forCreate.setTitle(pizzaType[i]);
            forCreate.setPrice(pricePizzaType[i]);
            forCreate.setCategoryId(5L);
            productsMapper.insert(forCreate);
        }

        String[] mebelType = {"Sofa", "Armchair", "Сhiffonier"};
        int[] priceMebelType = {22000, 11000, 32000};

        for (int i = 0; i < 3; i++) {
            forCreate.setTitle(mebelType[i]);
            forCreate.setPrice(priceMebelType[i]);
            forCreate.setCategoryId(6L);
            productsMapper.insert(forCreate);
        }

        // Вывести продукты в каждой из категорий

        for (long i = 1L; i <= 6L; i++) {
            filter.clear();
            filter.createCriteria()
                    .andCategoryIdEqualTo(i);
            System.out.println("Продукты из категории " + i + " :");
            System.out.println(productsMapper.selectByExample(filter));
            System.out.println("\n\n\n");

        }

    }

}


