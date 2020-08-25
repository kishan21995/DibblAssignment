package com.app.dibblassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.app.dibblassignment.R;
import com.app.dibblassignment.response.Artist;
import com.app.dibblassignment.response.Notification;
import com.app.dibblassignment.response.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.MyViewHolder> {
    private List<Notification> artistList;
    private Context context;



    @NonNull
    @Override
    public ArtistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArtistAdapter.MyViewHolder holder, int position) {

        Notification artist = artistList.get(position);
        holder.titleSong.setText(artist.getNotificationType());

        if(artist.getNotificationType().equalsIgnoreCase("song-suggest")){
            Picasso.with(context).load("http://13.127.198.116/storage/"+artist.getSong().getSongImage()).error(R.drawable.music).into(holder.profileIMG);


        }else{
            holder.titleSong.setText(artist.getNotificationMsg());
            Picasso.with(context).load("http://13.127.198.116/storage/"+artist.getUser().getImage()).error(R.drawable.music).into(holder.profileIMG);

        }
        //holder.titleSong.setText(artist.get);

        //Picasso.with(context).load(artist.get).error(R.drawable.pops).into(holder.profileIMG);
        // Picasso.with(context).load(artist.getImage()).error(R.drawable.music).into(holder.imageView);




    }


    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public ArtistAdapter(List<Notification> artistList, Context context) {

        this.artistList = artistList;
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleSong,hours,textViewNotification;

        ImageView profileIMG,likeIMG,notification ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileIMG = itemView.findViewById(R.id.profileIMG);
            titleSong = itemView.findViewById(R.id.title);
            likeIMG = itemView.findViewById(R.id.likeIMG);
            hours = itemView.findViewById(R.id.hour);

           




        }
    }

}
