package com.example.finalproject3up;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelViewHolder> {
    private List<LevelModel> levelList;
    private Context context;

    public LevelAdapter(Context context, List<LevelModel> levelList) {
        this.context = context;
        this.levelList = levelList;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_level, parent, false);
        return new LevelViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        LevelModel levelModel = levelList.get(position);
        holder.bindData(levelModel);

        // 在這裡為每個按鈕設置監聽器
        holder.levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 獲取點擊的關卡數字
                int selectedLevel = holder.getAdapterPosition() + 1;
                String levelName = levelModel.getLevelName();

                // 使用 Intent 啟動新的 Activity
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("levelNumber", selectedLevel); // 傳遞選中的關卡數字
                intent.putExtra("levelName", levelName);
                intent.putExtra("source", "MainActivity3");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }
}

