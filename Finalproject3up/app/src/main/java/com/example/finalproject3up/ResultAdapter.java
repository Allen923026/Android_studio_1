package com.example.finalproject3up;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject3up.R;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<String> questionsList;

    // Constructor to initialize the adapter with a list of questions
    public ResultAdapter(List<String> questionsList) {
        this.questionsList = questionsList;
    }

    // ViewHolder class to hold references to the views for each item in the RecyclerView
    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestion;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
        }
    }

    // onCreateViewHolder is called when the ViewHolder is created.
    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout and create a new ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ResultViewHolder(view);
    }

    // onBindViewHolder is called to set the data for a specific position.
    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        // Get the question at the current position
        String question = questionsList.get(position);

        // Set the question to the TextView in the ViewHolder
        holder.textViewQuestion.setText(question);
    }

    // getItemCount is called to get the number of items in the data set
    @Override
    public int getItemCount() {
        return questionsList.size();
    }
}
