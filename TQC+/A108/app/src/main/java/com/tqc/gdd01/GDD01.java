package com.tqc.gdd01;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class GDD01 extends Activity
{
  public static boolean bIfDebug = false;
  public static String TAG = "HIPPO_DEBUG";

  private static final int SPAN_COUNT = 2;
  private static final int DATASET_COUNT = 60;
  protected RecyclerView mRecyclerView;
  protected HippoCustomRecyclerViewAdapter mAdapter;
  protected RecyclerView.LayoutManager mLayoutManager;
  protected ArrayList<Movie> mDataset = new ArrayList<>();
  protected LayoutManagerType mCurrentLayoutManagerType;

  private enum LayoutManagerType
  {
    GRID_LAYOUT_MANAGER,
    LINEAR_LAYOUT_MANAGER
  }

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    initDataset();
    initRecycleView(savedInstanceState);
  }

  private void initDataset()
  {
    mDataset = new ArrayList<Movie>();
    for (int i = 0; i < DATASET_COUNT; i++)
      {
        Movie movie = new Movie();
         // TO DO 交替產生60筆電影清單
        movie.setName("電影編號#"+i); //設定電影名稱
        if(i%2==0){
          movie.setThumbnail(R.drawable.artificialtrees); //設定圖片
        }else{
          movie.setThumbnail(R.drawable.mountains);
        }
        mDataset.add(movie); //movie資料加入mDataset
      }
  }

  private void initRecycleView(Bundle savedInstanceState)
  {
    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView); //實體化
    mLayoutManager = new LinearLayoutManager(GDD01.this);
    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    if (savedInstanceState != null)
    {
      // Restore saved layout manager type.
      mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(Constants.EXTRA_KEY_LAYOUT_MANAGER);
    }
    setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

    //  動態載入自定義之 HippoCustomRecyclerViewAdapter 物件mAdapter，包含自訂UI text_row_item.xml。
    mAdapter = new HippoCustomRecyclerViewAdapter(GDD01.this,mDataset); //設定Adapter
    mRecyclerView.setAdapter(mAdapter); //設定橋接器
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings)
    {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState)
  {
    outState.putSerializable(Constants.EXTRA_KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
    super.onSaveInstanceState(outState);
  }

  public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType)
  {
    int scrollPosition = 0;
    // If a layout manager has already been set, get current scroll position.
    if (mRecyclerView.getLayoutManager() != null)
    {
      scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    switch (layoutManagerType)
    {
      case GRID_LAYOUT_MANAGER:
        mLayoutManager = new GridLayoutManager(GDD01.this, SPAN_COUNT);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        break;
      case LINEAR_LAYOUT_MANAGER:
        mLayoutManager = new LinearLayoutManager(GDD01.this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        break;
      default:
        mLayoutManager = new LinearLayoutManager(GDD01.this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
    }
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.scrollToPosition(scrollPosition);
  }
}
