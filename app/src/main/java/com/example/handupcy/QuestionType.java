package com.example.handupcy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionType extends AppCompatActivity {

    private String Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_type);
        final ImageView learn=findViewById(R.id.learn_im);
        final ImageView life=findViewById(R.id.life_im);
        final ImageView emotion=findViewById(R.id.emotion_im);
        final ImageView rest=findViewById(R.id.rest_im);
        TextView cancel=findViewById(R.id.cancel);
        TextView next=findViewById(R.id.next);
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learn.setImageResource(R.drawable.learns);
                life.setImageResource(R.drawable.lifek);
                emotion.setImageResource(R.drawable.emotionk);
                rest.setImageResource(R.drawable.restk);
                Type="学习";
            }
        });
        life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learn.setImageResource(R.drawable.learnk);
                life.setImageResource(R.drawable.lifes);
                emotion.setImageResource(R.drawable.emotionk);
                rest.setImageResource(R.drawable.restk);
                Type="生活";
            }
        });
        emotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learn.setImageResource(R.drawable.learnk);
                life.setImageResource(R.drawable.lifek);
                emotion.setImageResource(R.drawable.emotions);
                rest.setImageResource(R.drawable.restk);
               Type="情感";
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learn.setImageResource(R.drawable.learnk);
                life.setImageResource(R.drawable.lifek);
                emotion.setImageResource(R.drawable.emotionk);
                rest.setImageResource(R.drawable.rests);
                Type="其他";
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuestionType.this,AskQuestionActivity.class);
                intent.putExtra("Type",Type);
                startActivity(intent);
                finish();
            }
        });
    }

}
