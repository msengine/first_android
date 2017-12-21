package com.example.byhisson.fragmentex;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by byhisson on 2017. 12. 8..
 */

public interface DunkirkHub {

    //public static String SERVER_ADRESS = "http://210.121.160.82:10002/";
    public static String SERVER_ADRESS = "http://172.16.1.68:8080/";

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
    Call<ArrayList<PersonVO>> repoContributors2(
            @Path("persons") String persons);

    @GET("person/{name}")
    Call<PersonVO> detailPerson(
            @Path("name") String name);

    @DELETE("person/{name}")
    Call<Void> delPerson(
            @Path("name") String name);
}
