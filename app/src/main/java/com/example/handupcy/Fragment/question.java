package com.example.handupcy.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handupcy.AskQuestionActivity;
import com.example.handupcy.Fragment.question_Fragment.questionPaperAdapter;
import com.example.handupcy.QuestionActivity;
import com.example.handupcy.QuestionType;
import com.example.handupcy.R;

/**
 * Created by 67698 on 2018/5/25.
 */

public class question extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_question,container,false);
        initView(view);
        return view;
    }
    private void initView(View view)
    {
        TabLayout tabLayout=(TabLayout)view.findViewById(R.id.question_tab);
        tabLayout.addTab(tabLayout.newTab().setText("全部"));
        tabLayout.addTab(tabLayout.newTab().setText("学习"));
        tabLayout.addTab(tabLayout.newTab().setText("生活"));
        tabLayout.addTab(tabLayout.newTab().setText("情感"));
        tabLayout.addTab(tabLayout.newTab().setText("其他"));
        ViewPager viewPager=(ViewPager)view.findViewById(R.id.question_viewpager);
        viewPager.setAdapter(new questionPaperAdapter(getActivity().getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        FloatingActionButton floatingActionButton=view.findViewById(R.id.ask);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), QuestionType.class);
                startActivity(intent);
            }
        });
    }

}
