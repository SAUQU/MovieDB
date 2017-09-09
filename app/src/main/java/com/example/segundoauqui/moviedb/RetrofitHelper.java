package com.example.segundoauqui.moviedb;

import com.example.segundoauqui.moviedb.model.Example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by segundoauqui on 8/18/17.
 */

//https://api.themoviedb.org/3/movie/550?api_key=9aadefa9d5ee69b9ab17f5ea9d3f524a

public class RetrofitHelper {

    public static final String BASE_URL_TWO = "https://api.themoviedb.org/";
    public static final String PATH = "3/movie/550";
    public static final String QUERY_API_KEY = "9aadefa9d5ee69b9ab17f5ea9d3f524a";
    public static final String QUERY_JACK = "Jack";

    public static Retrofit create() {
        //creata a logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // create a custom client to add the interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL_TWO)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }


    public static Call<Example> getExampleCall() {
        Retrofit retrofit = create();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        return retrofitService.getRetrofitServiceData(QUERY_API_KEY, QUERY_JACK);
    }

    public interface RetrofitService{

        @GET(PATH)
        Call<Example> getRetrofitServiceData(@Query("api_key") String tag, @Query("jack") String query);
    }


}
