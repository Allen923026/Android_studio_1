package com.example.finalproject3up;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LevelViewHolder extends RecyclerView.ViewHolder {
    public Button levelButton;

    public LevelViewHolder(@NonNull View itemView) {
        super(itemView);
        levelButton = itemView.findViewById(R.id.buttonLevel);
    }

    public void bindData(LevelModel levelModel) {
        levelButton.setText(levelModel.getLevelName());
    }
}
