package com.example.bingimagdemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MypagerAdapter extends PagerAdapter {
   ArrayList<String> mlist=new ArrayList<String>();
   Context mcontext;
    public MypagerAdapter(ArrayList<String> list, Context context) {
        mlist = list;
        this.mcontext = context;

    }

    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        View view=View.inflate(mcontext,R.layout.large_bing_imag_item,null);
        ImageView imageView=view.findViewById(R.id.large_imageview);
        ObjectAnimator alphaani=ObjectAnimator.ofFloat(imageView,"alpha",0f,1f);
        alphaani.setDuration(2000);
        alphaani.start();
        Glide.with(mcontext).load(mlist.get(position)).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);

    }



    @Override
    public int getCount() {
        return mlist.size() ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
