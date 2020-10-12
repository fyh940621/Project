package com.example.bingimagdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.content.Intent;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class LargeBingImag extends AppCompatActivity {
     ArrayList<String> list=new ArrayList<String>();
    public static void actionStart(Context context, ArrayList<String>list,int position){
        Intent intent=new Intent(context,LargeBingImag.class);
        int n=list.size();
        for(int i=0;i<n;i++){
            intent.putExtra("url"+i,list.get(i));
        }
       intent.putExtra("size",n);
        intent.putExtra("position",position);
        context.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_bing_imag);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager=(ViewPager)findViewById(R.id.view_pager);
        int n=getIntent().getIntExtra("size",0);
        for(int i=0;i<n;i++){
            list.add(getIntent().getStringExtra("url"+i));

        }
        int position=getIntent().getIntExtra("position",  0);
        PagerAdapter pagerAdapter=new MypagerAdapter(list,this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
      /*  pagerAdapter.updatelist(list);
        pagerAdapter.notifyDataSetChanged();*/
        /*ImageView imageView=findViewById(R.id.large_imagview);
        Glide.with(this).load(url).into(imageView);
        ObjectAnimator alphaani=ObjectAnimator.ofFloat(imageView,"alpha",0f,1f);
        ObjectAnimator scaleyani=ObjectAnimator.ofFloat(imageView,"scaleY",1f,1.8f);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(alphaani).with(scaleyani);
        animatorSet.setDuration(3000);
        animatorSet.start();*/
    }


}