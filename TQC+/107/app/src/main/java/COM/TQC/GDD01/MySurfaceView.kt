package com.lndata.hellosurfaceview

import android.content.Context
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.text.SimpleDateFormat
import java.util.Calendar

import android.graphics.*
import android.text.TextUtils
import android.util.Log

class MySurfaceView(private val mContext: Context) : SurfaceView(mContext), SurfaceHolder.Callback, Runnable
{
    private val now = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
    private var surfaceHolder: SurfaceHolder? = null
    private var mPaint: Paint? = null
    private var thread: Thread? = null
    private var threadRunning = false
    private var mCanvas: Canvas? = null
    private var mMargin = 0
    private var textX = 0f
    private var textY = 0f
    public var text = ""
    public var screenWidth = 0
    public var screenHeight = 0
    
    init
    {
        isFocusable = true
        surfaceHolder = this.holder
        surfaceHolder!!.addCallback(this)
        mPaint = Paint()
        mPaint!!.textSize = 100f
        mPaint!!.color = Color.BLUE
        setZOrderOnTop(true)
    }
    
    override fun surfaceCreated(surfaceHolder: SurfaceHolder)
    {
        // TO DO
    }
    
    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int)
    {
    }
    
    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder)
    {
        threadRunning = false
    }

    override fun run()
    {
        while (threadRunning)
        {
            // 當一開始時沒有設定文字，預設為目前的時間
            // TO DO

            val startTime = System.currentTimeMillis()

            textY = 500f
            // TO DO

            drawText(text)

            val endTime = System.currentTimeMillis()
            val deltaTime = endTime - startTime

            if (deltaTime < 200)
            {
                try
                {
                    Thread.sleep(200 - deltaTime)
                }
                catch (e: Exception)
                {
                    e.printStackTrace()
                    Log.e(TAG, e.message)
                }

            }
        }
    }

    private fun drawText(text: String)
    {
        // TO DO
    }

    companion object
    {
        private val TAG = "HIPPO_DEBUG"
    }
}
