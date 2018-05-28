package com.example.handupcy.Fragment.question_Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.handupcy.Http;
import com.example.handupcy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 67698 on 2018/5/26.
 */

public class learn extends Fragment {
    Context context;
    int page=0;
    int listsize=0;
    List<EveryQuestion> questionList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_question_fragment,container,false);
        getDate(getActivity(),"https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList","page=0&size=10&kind=学习",view);
        return view;
    }
    private void initview(final View view)
    {
        RecyclerView recyclerView=view.findViewById(R.id.recycler);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        final questionRecyclerAdapter questionRecyclerAdapter1=new questionRecyclerAdapter(getActivity(),questionList);
        recyclerView.setAdapter(questionRecyclerAdapter1);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0)
                {
                    // Log.d("上拉刷新", "onScrolled开始: ");
                    int pingItem=linearLayoutManager.getChildCount();
                    int totalItem=linearLayoutManager.getItemCount();
                    int pingItemPosition=linearLayoutManager.findFirstVisibleItemPosition();
                    if((pingItem+pingItemPosition)>=totalItem)
                    {page=page+1;
                        Http http=new Http("https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList","page="+page+"&size=10&kind=全部");
                        http.sendRequestWithHttpURLConnection(new Http.Callback() {
                            @Override
                            public void finish(String respone) {
                                parseJSON(respone);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        questionRecyclerAdapter1.setDataList(questionList);
                                        questionRecyclerAdapter1.notifyDataSetChanged();
                                    }
                                });

                            }
                        });                        if(listsize==questionList.size()){
                            Toast.makeText(getActivity(),"垃圾图片加载库带不动了",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        final SwipeRefreshLayout swipeRefreshLayout=view.findViewById(R.id.swif);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questionList.clear();
                        getDate(getActivity(),"https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList","page=0&size=10&kind=学习",view);
                        questionRecyclerAdapter1.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
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
    private void getDate(final Context context, String url, String key, final View view)
    {
        if(isNetworkConnected(context)){
            Http http=new Http(url,key);
            http.sendRequestWithHttpURLConnection(new Http.Callback() {
                @Override
                public void finish(String respone) {
                    parseJSON(respone);
                    if(listsize==questionList.size())return;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initview(view);
                        }
                    });
                }
            });
        }
    }
    private void parseJSON(String respone) {
        try {
            JSONObject jsonObject=new JSONObject(respone);
            String result=jsonObject.getString("data");
            JSONArray jsonArray=new JSONArray(result);

            for(int i = 0; i < jsonArray.length(); i++)
            {
                EveryQuestion everyQuestion=new EveryQuestion();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                everyQuestion.setDescription(jsonObject1.getString("description"));
                everyQuestion.setGender(jsonObject1.getString("gender"));
                everyQuestion.setId(jsonObject1.getString("id"));
                everyQuestion.setKind(jsonObject1.getString("kind"));
                everyQuestion.setNickname(jsonObject1.getString("nickname"));
                everyQuestion.setReward(jsonObject1.getString("reward"));
                everyQuestion.setTitle(jsonObject1.getString("title"));
                everyQuestion.setTouUrl(jsonObject1.getString("photo_thumbnail_src"));
                questionList.add(everyQuestion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
