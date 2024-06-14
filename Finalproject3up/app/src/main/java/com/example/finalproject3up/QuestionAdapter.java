package com.example.finalproject3up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private final Context context;
    private String[] questionData;
    private String[] chineseTranslations;  // 新增中文翻譯數組
    private Map<String, String> loadedDictionary;

    public QuestionAdapter(String[] questionData, String[] chineseTranslations, Context context, Map<String, String> loadedDictionary) {
        this.questionData = questionData;
        this.chineseTranslations = chineseTranslations;
        this.context = context;
        this.loadedDictionary = loadedDictionary;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 創建 View，並返回相應的 ViewHolder
        // 這裡需要實現，根據你的需求選擇合適的 Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 在這裡設置 RecyclerView 中每一項的內容
        String englishWord = questionData[position];
        holder.textViewQuestion.setText(englishWord);

        // 取得中文翻譯
        String chineseTranslation = chineseTranslations[position];

        // 將中文翻譯設置到 TextView 中
        holder.textViewChineseTranslation.setText("中文：" + (chineseTranslation != null ? chineseTranslation : "未找到翻譯"));
    }

    @Override
    public int getItemCount() {
        // 返回數據集的大小
        return questionData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestion;
        TextView textViewChineseTranslation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 初始化 ViewHolder 中的元素
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
            textViewChineseTranslation = itemView.findViewById(R.id.textViewChineseTranslation);
        }
    }
}
