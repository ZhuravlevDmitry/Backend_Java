package com.geekbrains.backend.test.miniMarket;


import java.util.List;
import java.util.concurrent.Executor;

import com.geekbrains.backend.test.miniMarket.model.Category;
import com.geekbrains.backend.test.miniMarket.model.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MiniMarketApi {

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Long id);

    @POST("products") // Аннотация @ Body к параметру метода говорит Retrofit использовать объект
        // в качестве тела запроса для вызова.
    Call<Object> createProduct(@Body Product product);

    @PUT("products")
    Call<Object> updateProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<Object> deleteProduct(@Path("id") Long id);

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") Long id);
}

