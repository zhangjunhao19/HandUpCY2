package com.example.handupcy.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by 67698 on 2018/5/25.
 */

public class PaperAdapter extends FragmentPagerAdapter {
    private HashMap<Integer,Fragment> fragmentHashMap=new HashMap<>();
    public PaperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return creatFragment(position);
    }

    @Override
    public int getCount() {
        return 4;
    }

    private Fragment creatFragment(int position)
    {
        String TAG="Fragment";
        Fragment fragment=fragmentHashMap.get(position);
        if(fragment==null)
        {
            switch (position)
            {
                case 0:fragment=new kebiao();
                    Log.d(TAG, "creatFragment: 课表");
                    break;

                case 1:fragment=new question();
                    Log.d(TAG, "creatFragment: 邮问");
                    break;

                case 2:fragment=new find();
                    Log.d(TAG, "creatFragment: 发现");
                    break;
                case 3:fragment=new my();
                    Log.d(TAG, "creatFragment: 我的");
                    break;
            }
            fragmentHashMap.put(position,fragment);
        }
        return fragment;
    }

}
