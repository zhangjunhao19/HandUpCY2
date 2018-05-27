package com.example.handupcy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.handupcy.Fragment.PaperAdapter;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String status=null;
    private static final String TAG="MainActivity";
    private String key;
    public String id;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        password=intent.getStringExtra("password");
        key="stu_num="+id+"&forceFetch=false";
       // Log.d(TAG, "onCreate: id为"+sharedPreferences.getString("id","22"));
        getDate(this,"https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList","page=0&size=9&kind=学习");

    }

    private void initview()
    {
        final Context context=this;
        TabLayout tabLayout=(TabLayout)findViewById(R.id.Tab);
        tabLayout.addTab(tabLayout.newTab().setText("课表").setIcon(R.drawable.kebiaol));
        tabLayout.addTab(tabLayout.newTab().setText("邮问").setIcon(R.drawable.youwenl));
        tabLayout.addTab(tabLayout.newTab().setText("发现").setIcon(R.drawable.findl));
        tabLayout.addTab(tabLayout.newTab().setText("我的").setIcon(R.drawable.myl));
        NoScrollViewPaper viewPaper=(NoScrollViewPaper)findViewById(R.id.viewpager);
        viewPaper.setAdapter(new PaperAdapter(getSupportFragmentManager()));
        viewPaper.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPaper));
    }
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    private void getDate(final Context context, String url, String key)
    {
        if(isNetworkConnected(context)){
            Http http=new Http(url,key);
            http.sendRequestWithHttpURLConnection(new Http.Callback() {
                @Override
                public void finish(String respone) {
                    parseJSON(respone);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initview();
                        }
                    });
                }
            });
        }
    }
    private void parseJSON(String respone) {
        try {
            JSONObject jsonObject=new JSONObject(respone);
            Log.d(TAG, "parseJSON: json数据为"+jsonObject.toString());
            status=jsonObject.getString("status");
            if(status=="200") Log.d(TAG, "parseJSON: 请求成功");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
