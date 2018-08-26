package com.ruanf.vanhackswipedelete.remote;

import com.ruanf.vanhackswipedelete.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IMenuRequest {

    @GET
    Call<List<Item>> getMenuList(@Url String url);
}
