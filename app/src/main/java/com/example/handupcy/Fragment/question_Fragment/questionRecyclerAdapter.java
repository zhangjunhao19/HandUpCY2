package com.example.handupcy.Fragment.question_Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handupcy.CircleImage;
import com.example.handupcy.ImageLoad.ImageLoader;
import com.example.handupcy.QuestionParticularsActivity;
import com.example.handupcy.R;

import java.util.List;

/**
 * Created by 67698 on 2018/5/26.
 */

public class questionRecyclerAdapter extends RecyclerView.Adapter<questionRecyclerAdapter.ViewHolder> {
    private List<EveryQuestion>questionList;
    private ImageLoader imageLoader;
    private Context mcontext;
    public questionRecyclerAdapter(Context mcontext, List<EveryQuestion>questionList)
    {
        this.questionList=questionList;
        this.mcontext=mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mcontext==null)mcontext=parent.getContext();
        View view= LayoutInflater.from(mcontext).inflate(R.layout.frag_question_recyclerview,parent,false);;
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        imageLoader=ImageLoader.build(mcontext);
        final EveryQuestion everyQuestion=questionList.get(position);
        if(everyQuestion.getGender()=="女")holder.gender.setImageResource(R.drawable.girl);
       holder.miaos.setText(everyQuestion.getDescription());
        holder.title.setText(everyQuestion.getTitle());
        holder.reward.setText(everyQuestion.getReward()+"积分");
        holder.name.setText(everyQuestion.getNickname());
        final String id=everyQuestion.getId();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext, QuestionParticularsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("title",everyQuestion.getTitle());
                bundle.putString("touxiang",everyQuestion.getTouUrl());
                bundle.putString("miaos",everyQuestion.getDescription());
                bundle.putString("reward",everyQuestion.getReward());
                bundle.putString("name",everyQuestion.getNickname());
                bundle.putString("gender",everyQuestion.getGender());
                intent.putExtras(bundle);
                mcontext.startActivity(intent);
            }
        });
        String url=everyQuestion.getTouUrl();
       String url1= url.replace("http","https");
        Log.d("url", "为 "+url1);
        imageLoader.bindBitmap(url1,holder.circleImage,holder.circleImage.getWidth(),holder.circleImage.getHeight());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void setDataList(List<EveryQuestion> array) {
        this.questionList=array;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name;
        TextView reward;
        TextView title;
        TextView miaos;
        CircleImage circleImage;
        ImageView gender;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            name=(TextView)itemView.findViewById(R.id.name);
            reward=(TextView)itemView.findViewById(R.id.reward);
            title=(TextView)itemView.findViewById(R.id.title);
            miaos=(TextView)itemView.findViewById(R.id.miaos);
            circleImage=(CircleImage)itemView.findViewById(R.id.circleImage);
            gender=(ImageView)itemView.findViewById(R.id.gender);
        }
    }

}
