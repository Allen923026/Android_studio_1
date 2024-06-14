package com.tqc.gdd01;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

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
    private View mCoordinatorLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

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

        init();
        initBanner();
        initDataset();
        initFloatingActionButton();
        initRecycleView(savedInstanceState);
    }

    private void init()
    {
        mCoordinatorLayout = findViewById(R.id.main_CoordinatorLayout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle(getTitle());
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent, getTheme()));
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

    private void initDataset()
    {
        mDataset = new ArrayList<Movie>();
        for (int i = 0; i < DATASET_COUNT; i++)
        {
            Movie movie = new Movie();
            movie.setName("電影編號 #" + i);
            //  修正 RecyclerView 裡的電影租片清單為單數項目為milkyway.jpg，奇數項目為peacock.jpg
            // TO DO
            if(i%2==0){
                movie.setThumbnail(R.drawable.peacock);
            }else{
                movie.setThumbnail(R.drawable.milkyway);
            }

            mDataset.add(movie);
        }
    }

    private void initRecycleView(Bundle savedInstanceState)
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(GDD01.this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if (savedInstanceState != null)
        {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(Constants.EXTRA_KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mAdapter = new HippoCustomRecyclerViewAdapter(GDD01.this, mDataset);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
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

    private void initBanner()
    {
        //  當使用者點到廣告圖片，以 Snackbar 在應用程式最下方顯示短暫訊息：「你點擊了上方廣告Banner」
        // TO DO
        mCoordinatorLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout,
                        "你點擊了上方廣告Banner",Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;
            }
        });
    }

    private void initFloatingActionButton()
    {
        //  當按下FloatingActionButton時，以 Snackbar 在應用程式最下方顯示短暫訊息：「你點擊了浮動的+按鈕」
        // TO DO
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout,
                        "你點擊了浮動的+按鈕",Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;
            }
        });
    }

}
