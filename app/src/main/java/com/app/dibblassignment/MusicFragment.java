package com.app.dibblassignment;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.dibblassignment.Util.Util;
import com.app.dibblassignment.adapter.NotificationAdapter;
import com.app.dibblassignment.databinding.FragmentMusicBinding;
import com.app.dibblassignment.models.LikeRequest;
import com.app.dibblassignment.models.LikeResponse;
import com.app.dibblassignment.models.Notification;
import com.app.dibblassignment.models.NotificationResponse;
import com.app.dibblassignment.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MusicFragment extends Fragment {

    NotificationAdapter notificationAdapter;
    RecyclerView recyclerView;
    MainActivity mainActivity;
    FragmentMusicBinding fragmentMusicBinding;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMy4xMjcuMTk4LjExNlwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU5ODM0NzI0MCwiZXhwIjoxNTk4OTUyMDQwLCJuYmYiOjE1OTgzNDcyNDAsImp0aSI6Im1GQ0VweXFoSXY5Y281OTgiLCJzdWIiOjIyLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.mweIYGT562dicreKgLf7EpQKJp3BPMKDAGR0NE8-M9c";

      public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       fragmentMusicBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);
        View view = fragmentMusicBinding.getRoot();
        recyclerView = fragmentMusicBinding.recyclerViewdata;
        ApiCall();

       return view;

    }

    public void ApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.127.198.116/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<NotificationResponse> notificationResponseCall = client.notificationList("Bearer "+"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMy4xMjcuMTk4LjExNlwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU5ODM0NzI0MCwiZXhwIjoxNTk4OTUyMDQwLCJuYmYiOjE1OTgzNDcyNDAsImp0aSI6Im1GQ0VweXFoSXY5Y281OTgiLCJzdWIiOjIyLCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.mweIYGT562dicreKgLf7EpQKJp3BPMKDAGR0NE8-M9c");
        Util.showProgressDialog(getContext());
        notificationResponseCall.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, retrofit2.Response<NotificationResponse> response) {
                Util.dismissProgressDialog();
                if (response.body() != null) {
                   List<Notification> notificationList = response.body().getNotifications();
                    notificationAdapter = new NotificationAdapter(notificationList,getActivity());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(notificationAdapter);

                    notificationAdapter.likeItem(new NotificationAdapter.LikeInterface() {
                        @Override
                        public void likeItem(int likeValue,int notificationid) {
                            likeApi(likeValue,notificationid);
                         }
                    });

                }else {
                    Toast.makeText(mainActivity, "Api failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Toast.makeText(mainActivity, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
     }

    public void likeApi(int like_Id,int notId) {

        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setIsLiked(String.valueOf(like_Id));
        likeRequest.setNotificationId(String.valueOf(notId));
        likeRequest.setToken(token);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.127.198.116/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface client = retrofit.create(ApiInterface.class);
        Call<LikeResponse> notificationResponseCall = client.likeList(likeRequest);
       // Util.showProgressDialog(getContext());
        notificationResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, retrofit2.Response<LikeResponse> response) {
                Util.dismissProgressDialog();
                if (response.body() != null) {
                    Toast.makeText(mainActivity, "success", Toast.LENGTH_SHORT).show();
             }
            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                Toast.makeText(mainActivity, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
 }
}