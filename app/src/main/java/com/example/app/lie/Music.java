package com.example.app.lie;

import android.net.Uri;

import java.io.Serializable;

public class Music implements Serializable {
    public String title;
    public String id;
    public String singer;
    public static long size;
    public long album_id;
    public String album;
    private int isMusic;
    public Uri uri;
    public String duration;

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getIsMusic() {
        return isMusic;
    }

    public void setIsMusic(int isMusic) {
        this.isMusic = isMusic;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", singer='" + singer + '\'' +
                ", album_id=" + album_id +
                ", album='" + album + '\'' +
                ", isMusic=" + isMusic +
                ", uri=" + uri +
                ", duration='" + duration + '\'' +
                '}';
    }

    public Music(String id, String title, Uri uri, String singer, String album, String duration) {
        this.id = id;
        this.album = album;
        this.title = title;
        this.uri = uri;
        this.singer = singer;
        this.duration = duration;
    }

}
