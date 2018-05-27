package com.example.handupcy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AskQuestionActivity extends AppCompatActivity {
    String is_anonymous="0";
    String title;
    String description;
    String Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        final Intent intent=getIntent();
        Type=intent.getStringExtra("Type");
        final EditText titleed=findViewById(R.id.ask_title);
        title=titleed.getText().toString();
        final EditText miaos=findViewById(R.id.ask_miaos);
        description=miaos.getText().toString();
        final CheckBox is_lim=findViewById(R.id.is_lim);
        ImageView last=findViewById(R.id.last);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(AskQuestionActivity.this,QuestionType.class);
                startActivity(intent2);
                finish();
            }
        });
        TextView next=findViewById(R.id.next2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=titleed.getText().toString();
                description=miaos.getText().toString();
                Intent intent1=new Intent(AskQuestionActivity.this,AskFinishActivity.class);
                intent1.putExtra("Type",Type);
                intent1.putExtra("title",title);
                Log.d("Askquetion", "title为 "+title);
                if(is_lim.isChecked())is_anonymous="1";
                intent1.putExtra("is_anonymous",is_anonymous);
                intent1.putExtra("description",description);
                startActivity(intent1);
                Log.d("Askquestionactivity", "跳转成功");
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
