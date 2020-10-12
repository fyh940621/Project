package com.example.bingimagdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.cjj.MaterialRefreshLayout;

public class MainActivity extends AppCompatActivity {
    static BingImagAdapter adapter;
    static ArrayList<String> list = new ArrayList<String>();
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    HandlerThread handlerThread;
    static int i = 0;
    final ArrayList<String> blist = new ArrayList<String>();
    final ArrayList<String> mlist = new ArrayList<String>();
    Handler uihandler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            for(int i=0;i<8;i++){
                String url=msg.getData().getString("url"+i);
                mlist.add(url);

            }

            super.handleMessage(msg);
            Log.d("0000000000000000000", mlist.size()+"");
            adapter.updatalist(mlist);
            adapter.notifyDataSetChanged();
            mlist.clear();


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new BingImagAdapter(list, this);
        recyclerView.setAdapter(adapter);
        initThread();
        /*initThread();*/
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(adapter.getItemViewType(position)){
                    case 0:
                        return 2;
                    case 1:
                        return 1;
                    default:
                        return -1;
                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    int visibleitemcount=   gridLayoutManager.getChildCount();
                    int totalitemcount=gridLayoutManager.getItemCount();
                    int firstvisibleitem=gridLayoutManager.findFirstVisibleItemPosition();
                    if(visibleitemcount+firstvisibleitem>=totalitemcount){
                        initThread();
                    }
                }



            }
        });
        swipeRefreshLayout=findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               initThread();
               swipeRefreshLayout.setRefreshing(false);
            }
        });
       /* MaterialRefreshLayout materialRefreshLayout=(MaterialRefreshLayout)findViewById(R.id.refresh);*/
       /* materialRefreshLayout.setLoadMore(true);*/
       /* int a[]={16711680};
        materialRefreshLayout.setProgressColors(a);
      materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
          @Override
          public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout){
              initThread();
              materialRefreshLayout.finishRefreshLoadMore();
          }
          @Override
          public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
              initThread();
              materialRefreshLayout.finishRefresh();

          }

      });

*/



    }

    public void initThread() {
        handlerThread = new HandlerThread("BingPicChildThread");
        handlerThread.start();

        Handler childhandler = new Handler(handlerThread.getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                Log.d("111111111111111", Thread.currentThread().getName());
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=8&mkt=zh-CN").build();
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
                Bundle bundle = new Bundle();
                for (int i = 0; i < 8; i++) {
                    Images images = imagesArrayList.get(i);
                    String url = images.getUrl();
                    String tag = "url" + i;
                    bundle.putString(tag, "https://cn.bing.com" + url);


                    blist.add("https://cn.bing.com" + url);

                }

                Message message = new Message();
                message.setData(bundle);

                uihandler.sendMessage(message);

            }
        };
        Message message = new Message();
        message.arg1 = 1;
        childhandler.sendMessage(message);
     /*  uihandler.post(new Runnable() {
            @Override
            public void run() {
                adapter.updatalist(blist);
                adapter.notifyDataSetChanged();
                blist.clear();
            }
        });*/

    }

}


/*public void   initList() {
        new BingpicDownload(adapter).execute(i);
       i=i+1;
        new BingpicDownload(adapter).execute(i);
        i=i+1;

    }
    public void updata(){

        new BingpicDownload(adapter).execute(i);
        i=i+1;
        swipeRefreshLayout.setRefreshing(false);
    }*//*

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        handlerThread.quit();
    }
}


/*
public class MainActivity extends AppCompatActivity {
    static BingImagAdapter adapter;
    static ArrayList<String> list = new ArrayList<String>();
    TextView textView;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    HandlerThread handlerThread;
    Handler uihandler=new Handler();
    static int i = 0;
    ArrayList<String> blist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.text_view);
        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new BingImagAdapter(list, this);
        recyclerView.setAdapter(adapter);
        Log.d("1111111111111111", Thread.currentThread().getName()+blist.isEmpty());
        initThread();
        Log.d("2222222222222", Thread.currentThread().getName()+blist.isEmpty());


    }

    public void initThread() {
        handlerThread = new HandlerThread("BingPicChildThread");
        handlerThread.start();

        Handler childhandler = new Handler(handlerThread.getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                Log.d("111111111111111", Thread.currentThread().getName());
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=8&mkt=zh-CN").build();
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
                Bundle bundle = new Bundle();
                for (int i = 0; i < 8; i++) {
                    Images images = imagesArrayList.get(i);
                    String url = images.getUrl();
                    String tag = "url" + i;
                    bundle.putString(tag, "https://cn.bing.com" + url);
                    blist.add("https://cn.bing.com" + url);


                }


                Log.d("333333333333333", Thread.currentThread().getName() + blist.isEmpty());
                Log.d("333333333333333", blist.get(4));

            }
        };
        Log.d("4444444444444", Thread.currentThread().getName() + blist.isEmpty());
        Message message = new Message();
        message.arg1 = 1;
        childhandler.sendMessage(message);
        Log.d("5555555555555", Thread.currentThread().getName() + blist.isEmpty());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("99999999999999999999", Thread.currentThread().getName() + blist.isEmpty());
                */
/* textView.setText(blist.get(0));*//*

               */
/* textView.setText(blist.get(0));
                adapter.updatalist(blist);
                adapter.notifyDataSetChanged();*//*

            }
        });
    }
}
*/
