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
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by byhisson on 2017. 12. 8..
 */

public interface DunkirkHub {

    //public static String SERVER_ADRESS = "http://210.121.160.82:10002/";
    public static String SERVER_ADRESS = "http://100.114.50.66:8080/";

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVER_ADRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //GET, POST, DELETE, PUT 메소드를 인터페이스에 구현하여 사용할 수 있다

    /* Person Control */

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

    @GET("person/{name}")
    Call<Person> detailPerson(
            @Path("name") String name);

    @DELETE("person/{name}")
    Call<Void> delPerson(
            @Path("name") String name);

     /* Person Control */

    @GET("{group}")
    Call<ArrayList<Group>> getGroupList(
            @Path("group") String group);

    @FormUrlEncoded
    @POST("group")
    Call<Boolean> addGroup(
            @Field("name") String name,
            @Field("organization") String organization);

    @PUT("group/{name}")
    Call<Void> addMemberToGroup(
            @Field("name") String name,
            @Field("member") String member);

    @GET("group/{name}")
    Call<ArrayList<String>> openGroupMemberList(
            @Path("name") String name);
}

