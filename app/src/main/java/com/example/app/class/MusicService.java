//package com.example.app;
//
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.res.AssetFileDescriptor;
//import android.content.res.AssetManager;
//import android.media.MediaPlayer;
//import android.os.IBinder;
//import android.util.Log;
//import java.io.IOException;
//
//public class MusicService extends Service {
//
//    MyReceiver serviceReceiver;
//    AssetManager am;
//    Thread processThread;
//    static final String[] musics = {"与我无关.mp3", "专情.mp3", "天外来物.mp3", "所谓的过程.mp3"};
//    MediaPlayer mPlayer;
//    int status = 0x11;
//    int current = 0;
//
//    @Override
//    public IBinder onBind(Intent intent) {
//
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        am = getAssets();
//        serviceReceiver = new MyReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(MusicMain.CTL_ACTION);
//        registerReceiver(serviceReceiver, filter);
//        mPlayer = new MediaPlayer();
//        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                Log.d("MusicService", "歌曲播放完毕");
//                current++;
//                if (current >= 4) {
//                    current = 0;
//                }
//                Intent sendIntent = new Intent(MusicMain.UPDATE_ACTION);
//                sendIntent.putExtra("current", current);
//                sendBroadcast(sendIntent);
//                prepareAndPlay(musics[current]);
//            }
//        });
//        processThread = new Thread(() -> {
//            while (true) {
//                if (status == 0x12) {
//                    try {
//                        Thread.sleep(1000);
//                        Intent sendIntent1 = new Intent(MusicMain.UPDATE_ACTION);
//                        sendIntent1.putExtra("currentTime", mPlayer.getCurrentPosition());
//                        sendIntent1.putExtra("totalTime", mPlayer.getDuration());
//                        sendBroadcast(sendIntent1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        processThread.start();
//
//
//    }
//
//    public class MyReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(final Context context, Intent intent) {
//            int control = intent.getIntExtra("control", -1);
//            switch (control) {
//                case 1:
//                    if (status == 0x11) {
//                        prepareAndPlay(musics[current]);
//                        status = 0x12;
//                    }
//                    else if (status == 0x12) {
//
//                        mPlayer.pause();
//
//                        status = 0x13;
//                    }
//                    else if (status == 0x13) {
//                        mPlayer.start();
//                        status = 0x12;
//                    }
//                    break;
//                case 2:
//                    if (status == 0x12 || status == 0x13) {
//                        mPlayer.stop();
//                        status = 0x11;
//                    }
//                    break;
//                case 3:
//                    if (status == 0x12 || status == 0x13) {
//                        mPlayer.stop();
//                        if (current - 1 < 0) {
//                            current = musics.length - 1;
//                        } else {
//                            current--;
//                        }
//                        prepareAndPlay(musics[current]);
//                        status = 0x12;
//                    }
//                    break;
//                case 4:
//                    if (status == 0x12 || status == 0x13) {
//                        mPlayer.stop();
//                        if (current + 1 >= musics.length) {
//                            current = 0;
//                        } else {
//                            current++;
//                        }
//                        prepareAndPlay(musics[current]);
//                        status = 0x12;
//                    }
//                    break;
//            }
//            Intent sendIntent = new Intent(MusicMain.UPDATE_ACTION);
//            sendIntent.putExtra("update", status);
//            sendIntent.putExtra("current", current);
//            sendBroadcast(sendIntent);
//        }
//    }
//
//    private void prepareAndPlay(String music) {
//        try {
//            AssetFileDescriptor afd = am.openFd(music);
//            mPlayer.reset();
//            mPlayer.setDataSource(afd.getFileDescriptor(),
//                    afd.getStartOffset(), afd.getLength());
//            mPlayer.prepare();
//
//            mPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}