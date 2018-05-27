package com.example.handupcy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handupcy.ImageLoad.ImageLoader;

public class QuestionParticularsActivity extends AppCompatActivity {
    String id;
    String touxiang;
    String title;
    String miaos;
    String reward;
    String name;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_particulars);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        title=intent.getStringExtra("title");
        touxiang=intent.getStringExtra("touxiang");
        miaos=intent.getStringExtra("miaos");
        reward=intent.getStringExtra("reward");
        name=intent.getStringExtra("name");
        gender=intent.getStringExtra("gender");
        TextView title1=findViewById(R.id.particular_title);
        title1.setText(title);
        CircleImage circleImage=findViewById(R.id.touxiang);
        ImageLoader imageLoader=ImageLoader.build(this);
        imageLoader.bindBitmap(touxiang,circleImage,circleImage.getWidth(),circleImage.getHeight());
        TextView textView=findViewById(R.id.describe);
        textView.setText(miaos);
        TextView textView1=findViewById(R.id.particular_reward);
        textView1.setText(reward+"积分");
        TextView textView2=findViewById(R.id.name2);
        textView2.setText(name);
        ImageView imageView=findViewById(R.id.gender2);
        if(gender=="女")imageView.setImageResource(R.drawable.girl);
        ImageView imageView1=findViewById(R.id.back);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
