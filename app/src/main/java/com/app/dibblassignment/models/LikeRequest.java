package com.app.dibblassignment.models;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeRequest {

@SerializedName("notification_id")
@Expose
private String notificationId;
@SerializedName("is_liked")
@Expose
private String isLiked;

public String getNotificationId() {
return notificationId;
}

public void setNotificationId(String notificationId) {
this.notificationId = notificationId;
}

public String getIsLiked() {
return isLiked;
}

public void setIsLiked(String isLiked) {
this.isLiked = isLiked;
}

    public String getToken() {
        return isLiked;
    }

    public void setToken(String isLiked) {
        this.isLiked = isLiked;
    }

}