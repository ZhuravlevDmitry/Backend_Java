package com.geekbrains.backend.test.miniMarket;


import java.io.IOException;
import java.util.List;

import com.geekbrains.backend.test.miniMarket.model.Category;
import com.geekbrains.backend.test.miniMarket.model.Product;
import com.geekbrains.backend.test.miniMarket.model.ProductResponse;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiniMarketService {

    private final MiniMarketApi api;

    public MiniMarketService() {
        Retrofit retrofit = new Retrofit.Builder()  // создаём реализацию внешнего сервиса
                .baseUrl("http://minimarket1.herokuapp.com/market/api/v1/")  // указываем url к сервису
                .addConverterFactory(GsonConverterFactory.create())  // конвертируем json
                .build();
        api = retrofit.create(MiniMarketApi.class);
    }

    public ProductResponse getProduct(Long id) throws IOException {
        Response<Product> response = api.getProduct(id).execute();
        ProductResponse productResponse = new ProductResponse();
        if (response.isSuccessful()) {
            productResponse.setProduct(response.body());
        } else {
            productResponse.setError(response.errorBody().string());
        }
        return productResponse;
    }

    public Category getCategory(long id) throws IOException {
        Response<Category> response = api.getCategory(id).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            assert response.errorBody() != null;
            throw new RuntimeException(response.errorBody().string());
        }
    }

    public List<Product> getProducts() throws IOException {
        return api.getProducts()
                .execute()
                .body();
    }

    public Long createProduct(Product product) throws IOException {
        api.createProduct(product)
                .execute();

        return null;
    }

    public void updateProduct(Product product) throws IOException {
        api.updateProduct(product)
                .execute();
    }

    public void deleteProduct(long id) throws IOException {
        api.deleteProduct(id)
                .execute();
    }

}