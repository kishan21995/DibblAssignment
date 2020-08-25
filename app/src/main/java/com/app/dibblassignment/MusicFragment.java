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
import com.app.dibblassignment.adapter.ArtistAdapter;
import com.app.dibblassignment.databinding.FragmentMusicBinding;
import com.app.dibblassignment.response.Artist;
import com.app.dibblassignment.response.Notification;
import com.app.dibblassignment.response.NotificationResponse;
import com.app.dibblassignment.response.Song;
import com.app.dibblassignment.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends Fragment {


    ArtistAdapter artistAdapter;
    RecyclerView recyclerView;
    MainActivity mainActivity;
    FragmentMusicBinding fragmentMusicBinding;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9kaWJibC5pblwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU5Nzk5MDk1NCwiZXhwIjoxNTk4NTk1NzU0LCJuYmYiOjE1OTc5OTA5NTQsImp0aSI6IjZ6VmJUUjB2eWkzZW9XWjUiLCJzdWIiOjc1LCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.4rzNIIuzy1XtyqCuL73zChYm0FTzMw_3LMZPrLq5gzo";


    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_music, container, false);


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
        Call<NotificationResponse> notificationResponseCall = client.artistList("Bearer "+"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9kaWJibC5pblwvYXBpXC9hdXRoXC9sb2dpbiIsImlhdCI6MTU5Nzk5MDk1NCwiZXhwIjoxNTk4NTk1NzU0LCJuYmYiOjE1OTc5OTA5NTQsImp0aSI6IjZ6VmJUUjB2eWkzZW9XWjUiLCJzdWIiOjc1LCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.4rzNIIuzy1XtyqCuL73zChYm0FTzMw_3LMZPrLq5gzo");
        Util.showProgressDialog(getContext());
        notificationResponseCall.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, retrofit2.Response<NotificationResponse> response) {
                Util.dismissProgressDialog();
                if (response.body() != null) {
                   List<Notification> artistList = response.body().getNotifications();
                    artistAdapter = new ArtistAdapter(artistList,getActivity());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(artistAdapter);
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

}