package com.geekbrains.backend.test.miniMarket.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
    // т.к. работаем во внешнем АПИ используем ссылочные типы
    private Long id;
    private String title;
    private Integer price;
    private String categoryTitle;

// Для наилучшего прохождения процедуры серрилизации используем конструктор без параметров
// Пустой конструктор необходим для восстановления объекта.
// Это позволяет восстанавливать сериализованный объект из потока, т.е. начать не дожидаясь получения всего объекта.
// Сериализованный объект начинается с описания класса. Читается описание и создается пустой объект. Для этого класс
// и должен иметь пустой конструктор. Потом идут параметры, если есть. Они последовательно читаются и вставляются в
// созданный объект.
// Вторая причина - при сериализации пустые параметры (параметр = null) просто не пишутся в поток,
// т.е. минимизируется размер сериализованного обьекта.
//    public Product() {
//
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }
}

