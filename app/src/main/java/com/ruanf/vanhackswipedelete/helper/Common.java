package com.ruanf.vanhackswipedelete.helper;

import com.ruanf.vanhackswipedelete.remote.IMenuRequest;
import com.ruanf.vanhackswipedelete.remote.RecoverURL;

import retrofit2.Retrofit;

public class Common {
    public static IMenuRequest getMenuRequest()
    {
        return RecoverURL.getClient( "https://restcountries.eu" ).create( IMenuRequest.class );
    }
}
