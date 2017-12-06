package com.example.byhisson.fragmentex;

/**
 * Created by byhisson on 2017. 11. 28..
 */


import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface GitHubService {

   // public static String SERVER_ADRESS = "http://192.168.44.139:8080/";
   public static String SERVER_ADRESS = "http://210.121.160.82:10002/";
   // public static String SERVER_ADRESS = "http://192.168.0.5:8080/";

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVER_ADRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //GET, POST, DELETE, PUT 메소드를 인터페이스에 구현하여 사용할 수 있다
/*
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> repoContributors(
            @Path("owner") String owner, @Path("repo") String repo);

*/
    @FormUrlEncoded
    @POST("persons")
    Call<Boolean> addPerson(
            @Field("name") String name,
            @Field("address") String address,
            @Field("hobby") String hobby,
            @Field("nationality") String nationality);


    @GET("{persons}")
    Call<ArrayList<Person>> repoContributors2(
            @Path("persons") String persons);

    @GET("{persons}/{name}")
    Call<Person> detailPerson(
            @Path("persons") String persons,
            @Path("name") String name);

    @DELETE("persons/{name}")
    Call<Void> delPerson(@Path("name") String name);


}