package com.example.handupcy;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 67698 on 2018/5/25.
 */

public  class Http{
    private  String url1;//输入你自己的url
    private String key;
   public interface Callback{
        void finish(String respone);
    }
    public Http(String url1,String key)
    {
        this.url1=url1;
        this.key=key;
    }

    public void sendRequestWithHttpURLConnection(final Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Http", "run:进行网络连接 ");
                HttpURLConnection connection=null;
                BufferedReader reader;
                try {
                    URL url=new URL(url1);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(6000);
                    connection.setReadTimeout(6000);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    OutputStream is = connection.getOutputStream();
                    is.write(key.getBytes());
                    InputStream inputStream=connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null)
                    {
                        response.append(line);
                    }
                    //  Log.d("response", "内容为 "+response.toString());
                    if(callback!=null){
                        Log.d("Callback", "执行了finish");
                        callback.finish(response.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null)connection.disconnect();
                }
            }
        }).start();
    }

}
