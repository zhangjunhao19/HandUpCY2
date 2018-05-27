package com.example.handupcy.Fragment.question_Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.handupcy.Fragment.find;
import com.example.handupcy.Fragment.kebiao;
import com.example.handupcy.Fragment.my;
import com.example.handupcy.Fragment.question;

import java.util.HashMap;

/**
 * Created by 67698 on 2018/5/26.
 */

public class questionPaperAdapter extends FragmentPagerAdapter {
    private HashMap<Integer, Fragment> fragmentHashMap = new HashMap<>();

    public questionPaperAdapter(FragmentManager fm) {
        super(fm);
        
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("getItem", "得到碎片 ");
        return creatFragment(position);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    private Fragment creatFragment(int position) {
        String TAG = "Fragment";
      //  Log.d(TAG, "creatFragment: 开始创建碎片");
        Fragment fragment = fragmentHashMap.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new all();
                    Log.d(TAG, "creatFragment: 全部");
                    break;

                case 1:
                    fragment = new learn();
                    Log.d(TAG, "creatFragment: 学习");
                    break;

                case 2:
                    fragment = new life();
                    Log.d(TAG, "creatFragment: 生活");
                    break;
                case 3:
                    fragment = new emotion();
                    Log.d(TAG, "creatFragment: 情感");
                    break;
                case 4:
                    fragment= new rest();
                    Log.d(TAG, "creatFragment: 其他");
                    break;
            }
            fragmentHashMap.put(position, fragment);
        }
        return fragment;
    }
}