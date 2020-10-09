package com.example.safeex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList  title, email ,  username , password;
    private static ArrayList id ;
    int position;

    Animation translate_anim ;

    CustomAdapter(Activity activity,Context context , ArrayList id,  ArrayList  title ,ArrayList email,ArrayList username,ArrayList password){
        this.activity=activity;
        this.context=context;
        this.id=id;
        this.title=title;
        this.email=email;
        this.username=username;
        this.password=password;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.MyViewHolder holder, final int position) {
        this.position=position;

        holder.id_text.setText(String.valueOf(id.get(position)));
        holder.title_text.setText(String.valueOf(title.get(position)));
        holder.email_text.setText(String.valueOf(email.get(position)));
        holder.username_text.setText(String.valueOf(username.get(position)));
        holder.password_text.setText(String.valueOf(password.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("title",String.valueOf(title.get(position)));
                intent.putExtra("email",String.valueOf(email.get(position)));
                intent.putExtra("username",String.valueOf(username.get(position)));
                intent.putExtra("password",String.valueOf(password.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_text,title_text,email_text,username_text,password_text;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            id_text = itemView.findViewById(R.id.id_text);
            title_text = itemView.findViewById(R.id.title_text);
            email_text = itemView.findViewById(R.id.email_text);
            username_text = itemView.findViewById(R.id.username_text);
            password_text = itemView.findViewById(R.id.password_text);
            mainLayout=itemView.findViewById(R.id.mainLayout);
            // Animate RecycleView
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim );
        }
    }
}


