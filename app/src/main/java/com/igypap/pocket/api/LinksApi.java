package com.igypap.pocket.api;

import com.igypap.pocket.model.Link;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface LinksApi {
    //http://www.akademiakodu.pl/api/links.json
    @GET("api/links.json")
    Call<List<Link>> getLinks();

    //http://www.akademiakodu.pl/api/links.json?type=1&name=Google&reference=www.google.pl
    @POST("api/links.json")
    Call<Void> createLink(@Query("name") String name,
                          @Query("type") int type,
                          @Query("reference") String reference);

    @PUT("api/links/{id}.json")
    Call<Void> updateLink(@Path("id") int id,
                          @Query("name") String name,
                          @Query("type") int type,
                          @Query("reference") String reference);

    @DELETE("api/links/{id}.json")
    Call<Void> deleteLink(@Path("id") int id);
}
