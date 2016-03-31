package com.lyricaloriginal.photoframe;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by LyricalMaestro on 2016/03/19.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private Uri[] mPhotoUris;

    public ViewPagerAdapter(Context context, Uri[] photoUris) {
        mContext = context;
        mPhotoUris = photoUris.clone();
    }

    @Override
    public int getCount() {
        return mPhotoUris.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ImageView) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageURI(mPhotoUris[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
