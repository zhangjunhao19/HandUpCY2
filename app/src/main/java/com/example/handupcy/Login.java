package com.example.handupcy;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
        private Button login;
        private EditText id;
        private EditText password;
        private int status=0;
        private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        login=(Button)findViewById(R.id.button2_1);
        id=(EditText)findViewById(R.id.zhanghao);
        password=(EditText)findViewById(R.id.mima);
        mcontext=this;
        if(!isGrantExternalRW(this))Toast.makeText(this,"客官没有权限不好办呀",Toast.LENGTH_SHORT).show();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id=id.getText().toString();
                String mima=password.getText().toString();
                String key="stuNum="+Id+"&"+"idNum="+mima;

                getDate(mcontext,"https://wx.idsbllp.cn/api/verify",key);

                if(status==200)
                {
                    SharedPreferences.Editor  editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                    editor.putString("id",Id);
                    editor.putString("password",mima);
                    editor.apply();
                    Toast.makeText(mcontext,"登陆成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    intent.putExtra("id",Id);
                    intent.putExtra("password",mima);
                    startActivity(intent);
                    finish();
                }
                else Toast.makeText(mcontext,"多试几下",Toast.LENGTH_SHORT).show();
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
        if(isNetworkConnected(context)){
            Http http=new Http(url,key);
            http.sendRequestWithHttpURLConnection(new Http.Callback() {
                @Override
                public void finish(String respone) {
                    parseJSON(respone);
                }
            });
        }
        else Toast.makeText(context,"请连接网络",Toast.LENGTH_SHORT).show();
    }
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
            return false;
        }
        return true;
    }
    private void parseJSON(String respone) {
        try {
            JSONObject jsonObject=new JSONObject(respone);
            status=jsonObject.getInt("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
