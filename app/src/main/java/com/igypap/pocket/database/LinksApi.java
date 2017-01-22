package com.igypap.pocket.database;

import com.igypap.pocket.model.Link;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface LinksApi {
    //http://www.akademiakodu.pl/api/links.json
    @GET("api/links.json")
    Call<List<Link>> getLinks();

}
