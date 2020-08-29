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
    LikeInterface likeInterface;
    int likevalue;

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

        //Count hrs
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

            if (notification.getIsLiked()==0) {
                holder.unlikeIMG.setVisibility(View.VISIBLE);
                holder.likeIMG.setVisibility(View.GONE);
                likevalue = 1;

            } else if(notification.getIsLiked()==1) {
                holder.unlikeIMG.setVisibility(View.GONE);
                holder.likeIMG.setVisibility(View.VISIBLE);
                likevalue=0;
            }

            holder.unlikeIMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.unlikeIMG.setVisibility(View.GONE);
                    holder.likeIMG.setVisibility(View.VISIBLE);
                    likevalue = 1;
                    if(likeInterface != null){
                        likeInterface.likeItem(likevalue,notification.getId());
                    }
                }
            });

            holder.likeIMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (notification.getIsLiked()==0) {
                        holder.unlikeIMG.setVisibility(View.VISIBLE);
                        holder.likeIMG.setVisibility(View.GONE);
                        likevalue = 0;

                    }
                    if(likeInterface != null){
                        likeInterface.likeItem(likevalue,notification.getId());
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
         TextView titleSong, hours;
         ImageView profileIMG, likeIMG, notification, songIMG, playBTN, unlikeIMG;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileIMG = itemView.findViewById(R.id.profileIMG);
            songIMG = itemView.findViewById(R.id.songIMG);
            playBTN = itemView.findViewById(R.id.playBTN);
            titleSong = itemView.findViewById(R.id.title);
            likeIMG = itemView.findViewById(R.id.likeimg);
            unlikeIMG = itemView.findViewById(R.id.unlikeImage);
            hours = itemView.findViewById(R.id.hour);

         }
    }

    public interface LikeInterface {
        public void likeItem(int likeValue,int notificationid);

    }

   public void likeItem(LikeInterface likeInterface) {
        this.likeInterface = likeInterface;

    }

}
