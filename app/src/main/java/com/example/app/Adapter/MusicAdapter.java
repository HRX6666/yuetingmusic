package com.example.app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.lie.Music;
import com.example.app.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private Context mContext; // 声明一个上下文对象
    private List<Music> list; // 声明一个音频信息列表
    OnItemClickListener onItemClickListener;//声明接口对象

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;//传递接口
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int positon);//设置接口
    }

    public MusicAdapter(Context context, List<Music> mp3audio_list) {
        mContext = context;
        list = mp3audio_list;
    }

    public MusicAdapter(List<Music> musicList) {
        list = musicList;
    }

    // 创建列表项的视图持有者
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//负责承载每个子项布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_music, parent, false);
        MusicViewHolder musicViewHolder = new MusicViewHolder(view);
        int pos = musicViewHolder.getAbsoluteAdapterPosition();//获取绝对位置

        return musicViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, @SuppressLint("RecyclerView") final int position) {//负责每个子项绑定数据
        Music music = list.get(position);
        holder.mTitle.setText(music.getTitle());
        holder.mSinger.setText(music.getSinger());
        holder.mDuration.setText(music.getDuration());
        holder.mid.setText(music.getId());
        //            int pos=holder.getAbsoluteAdapterPosition();//获取绝对位置
        holder.itemView.setOnClickListener(v -> {
            onItemClickListener.OnItemClick(v, position);//接口回调
        });

    }

    class MusicViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mSinger, mDuration, mid;

        public MusicViewHolder(@NonNull View view) {
            super(view);
            mDuration = (TextView) view.findViewById(R.id.item_duration);
            mSinger = (TextView) view.findViewById(R.id.item_singer);
            mTitle = (TextView) view.findViewById(R.id.item_song);
            mid = (TextView) view.findViewById(R.id.item_local_music);
        }
    }

    public int getItemCount() {//返回条目
        return list.size();
    }
}
