package com.example.app.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.Adapter.MusicAdapter;
import com.example.app.lie.Music;
import com.example.app.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MusicMain extends AppCompatActivity implements View.OnClickListener {
    int id;
    private int position;
    MusicAdapter adapter;
    int currnentPlayPosition = -1;
    int currentPausePositionSong = 0;//记录暂停时进度条的位置
    MediaPlayer mediaPlayer = new MediaPlayer();
    ImageButton playTV, nextTV;
    ImageButton previousTV;
    TextView singerTv, songTV, durationTV;
    RecyclerView Rv_audio;//数据源
    List<Music> list;//歌单数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);
        initview();
        mediaPlayer = new MediaPlayer();
        list = new ArrayList<>();
        adapter = new MusicAdapter(this, list);//创建适配器对象
        Rv_audio.setAdapter(adapter);
        //设置布局管理器
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);//选择形式,第二个选择滑动方式，第三个选择是否反转。
        Rv_audio.setLayoutManager(LayoutManager);
        localBate();//加载本地数据源
        Log.d(TAG, "onCreate: "+list.toString());
        setEventListener();
    }

    private void setEventListener() {//设置每一项的监听事件
        adapter.setOnItemClickListener((view, position) -> {//position->代表当前播放的位置
            currnentPlayPosition = position;
            Music music = list.get(position);
            Log.d(TAG, "setEventListener: " + music);
            playMusicInMusicList(music);
        });
    }

    private void playMusicInMusicList(Music music) {
        //根据传入对象播放音乐
        //设置底部显示的歌手歌曲名称
        singerTv.setText(music.getSinger());
        songTV.setText(music.getTitle());
        stopMusic();//重置地址
        mediaPlayer.reset();//设置新的播放路径
        try {
            mediaPlayer.setDataSource(this,music.getUri());
            Log.d(TAG, music.getUri().toString());
            playMusic();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            if (currentPausePositionSong == 0) {
                try {
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                //从暂停到播放
                mediaPlayer.seekTo(currentPausePositionSong);
                mediaPlayer.start();
            }
            playTV.setImageResource(R.drawable.stop);
        }

    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            currentPausePositionSong = 0;
            mediaPlayer.seekTo(0);//进度条回到最初
            mediaPlayer.stop();
            playTV.setImageResource(R.drawable.play);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMusic();
    }

    private void initview() {
        //初始化函数
        previousTV = findViewById(R.id.previous);
        playTV = findViewById(R.id.play);
        nextTV = findViewById(R.id.Next);
        songTV = findViewById(R.id.Song);
        singerTv = findViewById(R.id.Singer);
        durationTV = findViewById(R.id.item_duration);
        Rv_audio = findViewById(R.id.rv_audio);
        playTV.setOnClickListener(this);
        previousTV.setOnClickListener(this);
        nextTV.setOnClickListener(this);
    }

    private void localBate() {
        //加载本地数据源到集合list中
        ContentResolver resolver = getContentResolver();
        //获取本地存储的地址
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = resolver.query(uri, null, null,//读取外部存储
                //Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, null, null,//读取内部存储
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        //遍历cursor;
        id = -1;
        if (cursor != null && cursor.moveToFirst()) {//查询结果非空 {
            while (cursor.moveToNext()) {//循环查询数据库
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                Log.d(title, title);
                //获取列，getColumnIndexOrThrow如果没有找到该列名,会抛出IllegalArgumentException异常
                String singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                id++;
                String sid = String.valueOf(id + 1);
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));//获取相应的的属性值
                String duration = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
//                String time=sdf.format(new Date(duration));
                Music music = new Music(sid, title, Uri.parse(path), singer,
                        album, duration);
                list.add(music);//添加到表中
                if (title.contains(".mp3")) {  //分离后缀
                    String[] str = title.split(".mp3");
                    title = str[0];
                }
                if (title.contains(".ogg")) {
                    String[] str = title.split(".ogg");
                    title = str[0];
                }
                if (title.contains(".flav")) {
                    String[] str = title.split(".flav");
                    title = str[0];
                }  //分离后缀
                if (title.contains("-")) {//用-分离歌名和歌手
                    String[] str = title.split("-");
                    singer = str[0];
                    title = str[1];
                } // if(album.equals("Sounds")) continue;
                adapter.notifyDataSetChanged();

            }

        }
        cursor.close();// 释放资源
    }

    private static final String TAG = "MusicMain";


    //    private void initMediaPlayer() {
//        //扫描本地音乐
////        list = new ArrayList<>();
////        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_audio);//获取Recycalview实例
////        MusicAdapter adapter = new MusicAdapter(this,list);//创建适配器对象
////        recyclerView.setAdapter(adapter);//设置布局管理器
////        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
////        recyclerView.setLayoutManager(LayoutManager);
////        adapter.notifyDataSetChanged();//更新适配器
//        try {
//            List<Music>musicList=LocalMusicUtils.getMusicData(getApplicationContext());//获取上下文
//            mediaPlayer.setDataSource(musicList.get(3).getUrl());
//            Log.d("222222222","333333333");
//            mediaPlayer.prepare();
//        } catch (Exception e) {
//            e.printStackTrace();//语句异常时，实例化数据
//        }
//    }
//    public void onRequestPermissionsResult( int requestCode, String[] permissions,
//                                            int[] grantResults){
//        switch (requestCode){
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                {
//                    initMediaPlayer();
//                } else {
//                    Toast.makeText(this, "拒绝", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                break;
//            default:
//        }
//    }
    public void onClick(View v) {//监听事件器
        switch (v.getId()) {
            case R.id.play:
                if (currnentPlayPosition == -1)//没有选中音乐
                {
                    Toast.makeText(this, "请选择您要播放的音乐", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mediaPlayer.isPlaying()) {//此时处于播放音乐需要暂停
                    pauseMusic();
                } else {//没有播放音乐需要播放
                    playMusic();
                }
                break;
            case R.id.Next:
                if (currnentPlayPosition == list.size() - 1) {
                    Toast.makeText(this, "已经是最后一首啦，没下一曲啊", Toast.LENGTH_SHORT).show();
                    return;
                }
                currnentPlayPosition = currnentPlayPosition + 1;
                Music NextList = list.get(currnentPlayPosition);
                playMusicInMusicList(NextList);
                break;
            case R.id.previous:
                if (currnentPlayPosition == 0) {
                    Toast.makeText(this, "已经是第一首啦，没上一曲啊", Toast.LENGTH_SHORT).show();
                    return;
                }
                currnentPlayPosition = currnentPlayPosition - 1;
                Music LastList = list.get(currnentPlayPosition);
                playMusicInMusicList(LastList);
                break;
            default:
                break;
        }

    }

    private void pauseMusic() {
        //暂停音乐的函数
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            currentPausePositionSong = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
            playTV.setImageResource(R.drawable.play);
        }
    }


}