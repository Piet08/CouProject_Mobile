package com.example.cou_project_tm;

import com.example.cou_project_tm.models.User;
import com.example.cou_project_tm.services.AuthentificationService;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        User currentUser = AuthentificationService.getCurrentUser();

        Request originalRequest = chain.request();
        Headers headers = new Headers.Builder()
                .add("Authorization", "Bearer " + currentUser.getToken())
                .add("User-Agent", "Cou")
                .build();

        Request newRequest = originalRequest.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE) // Sets this request's Cache-Control header, replacing any cache control headers already present.
                .headers(headers) //Removes all headers on this builder and adds headers.
                .method(originalRequest.method(), null) // Adds request method and request body
                .build();
        /*
        chain.proceed(request) is the call which will initiate the HTTP work. This call invokes the
        request and returns the response as per the request.
        */
        Response response = chain.proceed(newRequest);
        return response;
    }

}
