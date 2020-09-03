package com.app.dibblassignment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.app.dibblassignment.R;
import com.app.dibblassignment.models.Notification;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.text.format.DateFormat.format;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.MyViewHolder holder, int position) {

        final Notification notification = notificationList.get(position);
        holder.titleSong.setText(Html.fromHtml(notification.getNotificationMsg()));

       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        Date time = null;
        try {
            time = sdf.parse(notificationList.get(position).getCreatedAt());
            String time1 = sdf1.format(time);

            Date d1=sdf1.parse(time1);
            Date d2=(Calendar.getInstance().getTime());

            Log.d("Current","======°°"+printDifference(d1,d2));
            holder.hours.setText(printDifference(d1,d2));

         } catch (ParseException e) {
            e.printStackTrace();
        }
        Long now = System.currentTimeMillis();

        if (notification.getNotificationType().equalsIgnoreCase("song-suggest")) {
            Picasso.with(context).load("http://13.127.198.116/storage/" + notification.getSong().getSongImage()).error(R.drawable.music).into(holder.songIMG);
            holder.profileIMG.setVisibility(View.GONE);
            holder.songIMG.setVisibility(View.VISIBLE);

            holder.playBTN.setVisibility(View.VISIBLE);
            holder.likeIMG.setVisibility(View.VISIBLE);

               holder.cardView.setVisibility(View.VISIBLE);

               if (notification.getIsLiked()==0) {
                holder.unlikeIMG.setVisibility(View.VISIBLE);
                holder.likeIMG.setVisibility(View.GONE);
               // likevalue = 1;

            } else if(notification.getIsLiked()==1) {
                holder.unlikeIMG.setVisibility(View.GONE);
                holder.likeIMG.setVisibility(View.VISIBLE);
               // likevalue=0;
            }

   holder.unlikeIMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    likevalue =1;
                    holder.unlikeIMG.setVisibility(View.GONE);
                    holder.likeIMG.setVisibility(View.VISIBLE);

                    if(likeInterface != null){
                        likeInterface.likeItem(likevalue,notification.getId());
                    }
                }
            });

            holder.likeIMG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.unlikeIMG.setVisibility(View.VISIBLE);
                    holder.likeIMG.setVisibility(View.GONE);
                    likevalue = 0;

                      if(likeInterface != null){
                        likeInterface.likeItem(likevalue,notification.getId());
                    }

                }
            });

         } else {
            holder.titleSong.setText(Html.fromHtml(notification.getNotificationMsg()));
            Picasso.with(context).load("http://13.127.198.116/storage/" + notification.getUser().getImage()).error(R.drawable.music).into(holder.profileIMG);

            holder.songIMG.setVisibility(View.GONE);

            holder.playBTN.setVisibility(View.GONE);
            holder.playBTN.setVisibility(View.GONE);

               holder.cardView.setVisibility(View.GONE);

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
         CardView cardView;
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
            cardView=itemView.findViewById(R.id.cardView);

        }
    }

    public interface LikeInterface {
        public void likeItem(int likeValue,int notificationid);

    }

   public void likeItem(LikeInterface likeInterface) {
        this.likeInterface = likeInterface;

    }

private String printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String remainingTime="";
        if(elapsedDays==0 && elapsedHours==0){

            remainingTime=elapsedMinutes+"Minute";

        }else if (elapsedDays==0 && elapsedHours<24){

            remainingTime=elapsedHours+"h";
        }else if(elapsedDays<30 ){

            remainingTime=elapsedDays+"d";

        }else {
            remainingTime=elapsedDays%30+"M";
        }

        return remainingTime;
    }

    private Date getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        return stringToDate(formattedDate);
    }

   private Date stringToDate(String aDate) {

        String dtStart = aDate;
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

}
