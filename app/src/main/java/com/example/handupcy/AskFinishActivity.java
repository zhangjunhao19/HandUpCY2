package com.example.handupcy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class AskFinishActivity extends AppCompatActivity {
    String stuname;
    String idnum;
    String is_anonymous;
    String title;
    String description;
    String Type;
    String year="2018";
    String month="09";
    String day="01";
    String hour="01";
    String min="01";
    String second="01";
    String reward="1";
    String key;

    String status;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_finish);
        context=this;
        Intent intent=getIntent();
        is_anonymous=intent.getStringExtra("is_anonymous");
        title= intent.getStringExtra("title");
        description=intent.getStringExtra("description");
        Type=intent.getStringExtra("Type");
        Log.d("sdsds", "onCreate:title ");
        ImageView last=findViewById(R.id.last1);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(AskFinishActivity.this,AskQuestionActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        final TextView finish=findViewById(R.id.next3);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
               stuname=sharedPreferences.getString("id","22");
               idnum=sharedPreferences.getString("password","22");
                EditText editText=findViewById(R.id.year);
                year=editText.getText().toString();
                editText=findViewById(R.id.month);
                month=editText.getText().toString();
                editText=findViewById(R.id.day);
                day=editText.getText().toString();
                editText=findViewById(R.id.hour);
                hour=editText.getText().toString();
                editText=findViewById(R.id.min);
                min=editText.getText().toString();
                editText=findViewById(R.id.second);
                second=editText.getText().toString();
                editText=findViewById(R.id.ask_reward);
                reward=editText.getText().toString();
                key="stuNum="+stuname+"&idNum="+idnum+"&title="+title+"&description="+description+"&is_anonymous="+is_anonymous+"&kind="+Type+"&tags=PHP"+"&reward="+reward+"&disappear_time="+year+"-"+month+"-"+day+" "+hour+":"+min+":"+second;
                getDate(context,"https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/add",key);
                finish();
            }
        });
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
        Log.d("AskFinishActivity", "getDate:  key"+key);
        if(isNetworkConnected(context)){
            Http http=new Http(url,key);
            http.sendRequestWithHttpURLConnection(new Http.Callback() {
                @Override
                public void finish(String respone) {
                    parseJSON(respone);
                }
            });
        }
    }
    private void parseJSON(String respone) {
        try {
            JSONObject jsonObject=new JSONObject(respone);
          //  Log.d(TAG, "parseJSON: json数据为"+jsonObject.toString());
            status=jsonObject.getString("status");
            if(status=="200") Toast.makeText(this,"提问成功",Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
