package com.lyricaloriginal.photoframe;

import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;
    private boolean mStartScroll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager vFlipper = (ViewPager)findViewById(R.id.my_view_pager);
        vFlipper.setAdapter(new ViewPagerAdapter(this, createPhotoUris()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler = new Handler();
        mHandler.postDelayed(new ScrollAction(), 5000);
        mStartScroll = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStartScroll = false;
    }

    private Uri[] createPhotoUris(){
        File targetDir = new File(Environment.getExternalStorageDirectory(), "Pictures/Screenshots");
        File[] files = targetDir.listFiles();
        if(files == null){
            return new Uri[0];
        }
        Uri[] uris = new Uri[files.length];
        for(int i = 0; i < files.length; i++){
            uris[i] = Uri.fromFile(files[i]);
        }
        return uris;
    }

    private class ScrollAction implements Runnable{
        @Override
        public void run() {
            if(!mStartScroll){
                return;
            }

            ViewPager vFlipper = (ViewPager)findViewById(R.id.my_view_pager);
            int i = vFlipper.getCurrentItem();
            vFlipper.setCurrentItem(i + 1, true);

            mHandler.postDelayed(this, 5000);
        }
    }
}
