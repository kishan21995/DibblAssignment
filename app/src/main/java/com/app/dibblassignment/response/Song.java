
package com.app.dibblassignment.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("album_id")
    @Expose
    private Integer albumId;
    @SerializedName("artist_id")
    @Expose
    private Integer artistId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("song_image")
    @Expose
    private String songImage;
    @SerializedName("additional_tags")
    @Expose
    private String additionalTags;
    @SerializedName("is_explicit_content")
    @Expose
    private Integer isExplicitContent;
    @SerializedName("is_cover_song")
    @Expose
    private Integer isCoverSong;
    @SerializedName("artist_name")
    @Expose
    private String artistName;
    @SerializedName("featuring_artist")
    @Expose
    private String featuringArtist;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("isrc")
    @Expose
    private Object isrc;
    @SerializedName("composer")
    @Expose
    private String composer;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getAdditionalTags() {
        return additionalTags;
    }

    public void setAdditionalTags(String additionalTags) {
        this.additionalTags = additionalTags;
    }

    public Integer getIsExplicitContent() {
        return isExplicitContent;
    }

    public void setIsExplicitContent(Integer isExplicitContent) {
        this.isExplicitContent = isExplicitContent;
    }

    public Integer getIsCoverSong() {
        return isCoverSong;
    }

    public void setIsCoverSong(Integer isCoverSong) {
        this.isCoverSong = isCoverSong;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getFeaturingArtist() {
        return featuringArtist;
    }

    public void setFeaturingArtist(String featuringArtist) {
        this.featuringArtist = featuringArtist;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Object getIsrc() {
        return isrc;
    }

    public void setIsrc(Object isrc) {
        this.isrc = isrc;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
