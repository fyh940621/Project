package com.example.bingimagdemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class BingImagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
   ArrayList<String> ilist;


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
    static class footViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView textView;
       public footViewHolder(View footview){
          super(footview);
         /* progressBar=(ProgressBar)footview.findViewById(R.id.progress_bar);*/
           textView=(TextView)footview.findViewById(R.id.text_view);
      }

    }
    public BingImagAdapter(ArrayList<String> listimag, Context context) {
        ilist = listimag;
        mcontext = context;
    }
     public void updatalist(ArrayList<String> list){
       ilist.addAll(list);
     }
    /*@Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bingimag_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //图片点击之后的操作
                    int position = viewHolder.getAdapterPosition();
                    String url = ilist.get(position);
                    LargeBingImag.actionStart(mcontext, url);
                }
            });

            return viewHolder;
        }


    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {

            String url = ilist.get(position);
            Glide.with(mcontext).load(url).into(((BingImagAdapter.ViewHolder) holder).imageView);
        }*/



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bingimag_item, parent, false);
            final ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //图片点击之后的操作
                    int position = viewHolder.getAdapterPosition();
                    String url = ilist.get(position);
                    LargeBingImag.actionStart(mcontext, ilist,position);

                }
            });

            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bingimag_footview, parent, false);
            footViewHolder footview=new footViewHolder(view);
            return footview;

        }
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            String url = ilist.get(position);
            Glide.with(mcontext).load(url).into(((BingImagAdapter.ViewHolder) holder).imageView);
            ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(((ViewHolder) holder).imageView,"alpha",0f,1f);
            objectAnimator.setDuration(3000);
            objectAnimator.start();
        }else if(holder instanceof footViewHolder){
           /* ((footViewHolder)holder).progressBar.setVisibility(View.VISIBLE);*/
            ((footViewHolder) holder).textView.setText("正在加载....");

        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewtype;
        if(position==ilist.size()){
            viewtype=0;
        }else{
            viewtype=1;
        }
        return viewtype;
    }

    @Override
    public int getItemCount() {
        return ilist.size()+1;
    }


}
