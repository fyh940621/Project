package com.example.bingimagdemo;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BingpicDownload extends AsyncTask<Integer, Void, ArrayList<String>> {
   ArrayList<String> blist=new ArrayList<String>() ;
   BingImagAdapter adapter;

    public BingpicDownload(BingImagAdapter adapters) {
        adapter = adapters;
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected ArrayList<String> doInBackground(Integer... integers) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://cn.bing.com/HPImageArchive.aspx?format=js&idx="+integers.toString()+"&n=8&mkt=zh-CN").build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responsedata = null;
        try {
            responsedata = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Bingpic bingpics = gson.fromJson(responsedata, Bingpic.class);
        ArrayList<Images> imagesArrayList = bingpics.getImages();
        for (int i = 0; i < 8; i++) {
            Images images = imagesArrayList.get(i);
            String url = images.getUrl();
            blist.add("https://cn.bing.com" + url);

        }

        return blist;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        adapter.updatalist(strings);
        adapter.notifyDataSetChanged();

    }

}
