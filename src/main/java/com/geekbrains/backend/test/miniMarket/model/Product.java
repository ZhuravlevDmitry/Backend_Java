package com.geekbrains.backend.test.miniMarket.model;



//import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {

    private Long id;
    private Integer price;
    private String title;
    private String categoryTitle;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
                ", price=" + price +
                ", title='" + title + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }
}

// Для наилучшего прохождения процедуры серрилизации используем конструктор без параметров
// Пустой конструктор необходим для восстановления объекта.
// Это позволяет восстанавливать сериализованный объект из потока, т.е. начать не дожидаясь получения всего объекта.
// Сериализованный объект начинается с описания класса. Читается описание и создается пустой объект. Для этого класс
// и должен иметь пустой конструктор. Потом идут параметры, если есть. Они последовательно читаются и вставляются в
// созданный объект.
// Вторая причина - при сериализации пустые параметры (параметр = null) просто не пишутся в поток,
// т.е. минимизируется размер сериализованного обьекта.


