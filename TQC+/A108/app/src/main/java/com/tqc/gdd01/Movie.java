package com.tqc.gdd01;

/**
 * Created by Hippo on 2015/8/18.
 */
public class Movie
{
  private String mName;
  private int mThumbnail;

  public String getName()
  {
    return mName;
  }

  public void setName(String name)
  {
    this.mName = name;
  }

  public int getThumbnail()
  {
    return mThumbnail;
  }

  public void setThumbnail(int thumbnail)
  {
    this.mThumbnail = thumbnail;
  }
}
