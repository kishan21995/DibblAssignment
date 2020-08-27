package com.app.dibblassignment.adapter;

import android.content.Context;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.app.dibblassignment.R;
import com.app.dibblassignment.models.Notification;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private List<Notification> notificationList;
    private Context context;



    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.MyViewHolder holder, int position) {

        Notification notification = notificationList.get(position);
        holder.titleSong.setText(notification.getNotificationMsg());


        if(notification.getNotificationType().equalsIgnoreCase("song-suggest")){
            Picasso.with(context).load("http://13.127.198.116/storage/"+notification.getSong().getSongImage()).error(R.drawable.music).into(holder.songIMG);

            holder.profileIMG.setVisibility(View.GONE);
            holder.songIMG.setVisibility(View.VISIBLE);

        }else{
           // .setVisibility(View.VISIBLE);
            holder.titleSong.setText(notification.getNotificationMsg());
       Picasso.with(context).load("http://13.127.198.116/storage/"+notification.getUser().getImage()).error(R.drawable.music).into(holder.profileIMG);

            /*holder.profileIMG.setVisibility(View.VISIBLE);
            holder.songIMG.setVisibility(View.GONE);*/

            holder.songIMG.setVisibility(View.GONE);





        }
    /*    //holder.titleSong.setText(artist.get);

        //Picasso.with(context).load(artist.get).error(R.drawable.pops).into(holder.profileIMG);
        // Picasso.with(context).load(artist.getImage()).error(R.drawable.music).into(holder.imageView);*/




    }


    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public NotificationAdapter(List<Notification> notificationList, Context context) {

        this.notificationList = notificationList;
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleSong,hours,textViewNotification;
        CardView songItem;

        ImageView profileIMG,likeIMG,notification,songIMG ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileIMG = itemView.findViewById(R.id.profileIMG);
           //songItem = itemView.findViewById(R.id.songItem);
            songIMG = itemView.findViewById(R.id.songIMG);
            titleSong = itemView.findViewById(R.id.title);
            likeIMG = itemView.findViewById(R.id.likeIMG);
            hours = itemView.findViewById(R.id.hour);








        }
    }

}
