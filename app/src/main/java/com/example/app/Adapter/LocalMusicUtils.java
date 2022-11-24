//package com.example.app.Adapter;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.provider.MediaStore;
//
//
//import com.example.app.lie.Music;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LocalMusicUtils {
//    //音乐工具类
//        // 扫描系统里面的音频文件，返回一个list集合
//        public  static List<Music>getMusicData(Context context) {
//            List<Music> list = new ArrayList<>();
//            //加载本地存储当中的音乐mp3文件到集合当中
//            // 获取ContentResolver对象
//            // ContentResolver resolver = getContentResolver();
//            // 获取本地音乐存储的Uri地址
//            // Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//            // 开始查询地址
//            //Cursor cursor = resolver.query(uri, null, null, null, null);
//            // 遍历Cursor
//            // 查询曲库
//            //Cursor 提供遍历查询结果的方法//getcontentresolver访问数据//
//            Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
//                    //Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, null, null,
//                    null, MediaStore.Audio.AudioColumns.IS_MUSIC);
//        /*query参数：
//        uri	要检索的内容
//        projection	要返回的列的列表  传递null将返回所有列
//        selection	声明要返回的行 格式为SQL WHERE子句（不包括WHERE本身） ，传递null将返回给定URI的所有行
//        selectionArgs	可能包含在selection中，将被selectionArgs的值按其在所选内容中出现的顺序替换，这些值将被绑定为字符串。
//        sortOrder	如何对行排序，格式为SQL order BY子句（不包括order BY本身） ，传递null将使用默认顺序，可能无序
//        */
//            int id = -1;
//            if (cursor != null) {//查询结果非空 {
//                while (cursor.moveToNext()) {//循环查询数据库
//                    Music music = new Music();
//                    music.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
//                    music.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
//                    if (music.duration > 30 * 1000 && music.size > 1000 * 800) {
//                        music.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
//                        //获取列，getColumnIndexOrThrow如果没有找到该列名,会抛出IllegalArgumentException异常
//                        music.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
//                        music.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
//                        music.album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));//获取相应的的属性值
//                        if (music.title.contains(".mp3")) {  //分离后缀
//                            String[] str = music.title.split(".mp3");
//                            music.title = str[0];
//                        }
//                        if (music.title.contains(".ogg")) {
//                            String[] str = music.title.split(".ogg");
//                            music.title = str[0];
//                        }
//                        if (music.title.contains(".flav")) {
//                            String[] str = music.title.split(".flav");
//                            music.title = str[0];
//                        }  //分离后缀
//                        if (music.title.contains("-")) {//用-分离歌名和歌手
//                            String[] str = music.title.split("-");
//                            music.singer = str[0];
//                            music.title = str[1];
//                        } // if(album.equals("Sounds")) continue;
//                        ++id;
//                        String sid = String.valueOf(id + 1);
//                        music.id = sid;
//                        list.add(music);//添加到表中
//                    }
//                }
//                cursor.close();// 释放资源
//            }
//            return list;
//        }
//        // 定义一个方法用来格式化获取到的时间
//        public  String formatTime ( long time){//ms
//            if (time / 1000 % 60 < 10) {
//                return time / 1000 / 60 + ":0" + time / 1000 % 60;
//            } else {
//                return time / 1000 / 60 + ":" + time / 1000 % 60;
//            }
//        }
//    }
//
