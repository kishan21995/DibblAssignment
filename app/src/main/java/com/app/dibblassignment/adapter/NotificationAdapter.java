package com.app.dibblassignment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.text.format.DateUtils;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        final Notification notification = notificationList.get(position);
        holder.titleSong.setText(notification.getNotificationMsg());



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Long time = null;
        try {
            time = sdf.parse(notificationList.get(position).getCreatedAt()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long now = System.currentTimeMillis();
        String ago = (DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS).toString());
        holder.hours.setText(ago);



        if (notification.getNotificationType().equalsIgnoreCase("song-suggest")) {
            Picasso.with(context).load("http://13.127.198.116/storage/" + notification.getSong().getSongImage()).error(R.drawable.music).into(holder.songIMG);
            holder.profileIMG.setVisibility(View.GONE);
            holder.songIMG.setVisibility(View.VISIBLE);

            holder.playBTN.setVisibility(View.VISIBLE);
            holder.likeIMG.setVisibility(View.VISIBLE);



        /*    public void onLikeClick(View view) {
                if (clicked) {
                    imageView.setImageResource(R.drawable.likeimg);
                    clicked = false;
                } else {
                    clicked = true;
                    imageView.setImageResource(R.drawable.unlikeimg);
                }
            }*/
        holder.likeIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (notification.getNotificationValue().equalsIgnoreCase("0")) {
                   holder.likeIMG.setImageResource(R.drawable.likeimg);

                } else {

                    holder.likeIMG.setImageResource(R.drawable.unlikeimg);
                }

            }
        });








        } else {

            holder.titleSong.setText(notification.getNotificationMsg());
            Picasso.with(context).load("http://13.127.198.116/storage/" + notification.getUser().getImage()).error(R.drawable.music).into(holder.profileIMG);


            holder.songIMG.setVisibility(View.GONE);

            holder.playBTN.setVisibility(View.GONE);
            holder.playBTN.setVisibility(View.GONE);


        }


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
        TextView titleSong, hours, textViewNotification;
        CardView songItem;

        ImageView profileIMG, likeIMG, notification, songIMG, playBTN, unlikeIMG;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileIMG = itemView.findViewById(R.id.profileIMG);
            songIMG = itemView.findViewById(R.id.songIMG);
            playBTN = itemView.findViewById(R.id.playBTN);
            titleSong = itemView.findViewById(R.id.title);
            likeIMG = itemView.findViewById(R.id.likeIMG);
            //unlikeIMG = itemView.findViewById(R.id.unlikeIMG);
            hours = itemView.findViewById(R.id.hour);



            /*songItem = itemView.findViewById(R.id.songItem);*/

        }
    }

}
