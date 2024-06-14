package com.tqc.gdd01;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hippo on 2018.
 */
public class HippoCustomRecyclerViewAdapter extends RecyclerView.Adapter<HippoCustomRecyclerViewAdapter.ViewHolder>
{
  public static boolean bIfDebug = true;
  public static String TAG = "HIPPO_DEBUG";
  private static Context mContext;
  private ArrayList<Movie> mDataSet = new ArrayList<Movie>();

  public static class ViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView textView;
    private final ImageView imageView;

    public ViewHolder(View view)
    {
      super(view);
      //  設計RecyclerView中點選RecyclerView.ViewHolder的項目，以Toast顯示訊息：「你點選的是第 xx 部電影」
      // TO DO
      textView = (TextView) view.findViewById(R.id.text_row_item_textView1);
      imageView = (ImageView) view.findViewById(R.id.text_row_item_imageView1);

      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          String pos = view.getTag().toString(); //位置
          String msg = String.format("你點選的是第 %s 部電影",pos); //訊息
          Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();//以Toast顯示訊息

        }
      });
    }

    public TextView getTextView()
    {
      return textView;
    }

    public ImageView getImageView()
    {
      return imageView;
    }
  }

  // Constructor
  public HippoCustomRecyclerViewAdapter(Context context, ArrayList<Movie> mDataSet)
  {
    this.mContext = context;
    this.mDataSet = mDataSet;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
  {
    View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int position)
  {
    // 指派ViewHolder物件，重複使用，動態載入電影名稱(TextView)及圖片Resource ID(ImageView)
    // TO DO
    viewHolder.itemView.setTag(""+position);
    Movie movie = mDataSet.get(position);
    viewHolder.textView.setText(movie.getName());
    viewHolder.imageView.setImageResource(movie.getThumbnail());
  }

  @Override
  public int getItemCount()
  {
    return mDataSet.size();
  }
}
