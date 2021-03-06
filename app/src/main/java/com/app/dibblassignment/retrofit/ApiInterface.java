package com.app.dibblassignment.retrofit;




import com.app.dibblassignment.models.LikeRequest;
import com.app.dibblassignment.models.LikeResponse;
import com.app.dibblassignment.models.NotificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {


    @GET("http://13.127.198.116/api/notifications")
    Call<NotificationResponse>notificationList(@Header("Authorization") String token);

    @POST("http://13.127.198.116/api/like-notification")
    Call<LikeResponse>likeList(@Body LikeRequest likeRequest);

}