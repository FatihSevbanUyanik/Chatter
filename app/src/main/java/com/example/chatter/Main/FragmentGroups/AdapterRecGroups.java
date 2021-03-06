package com.example.chatter.Main.FragmentGroups;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chatter.Modals.Group;
import com.example.chatter.R;
import com.example.chatter.Utils.UniversalImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecGroups extends RecyclerView.Adapter<AdapterRecGroups.MyViewHolder> {

    // properties
    private ArrayList<Group> groups;
    private LayoutInflater inflater;
    private Context context;


    // constructor
    AdapterRecGroups(Context context, ArrayList<Group> groups) {
        inflater = LayoutInflater.from(context);
        this.groups = groups;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder( inflater.inflate(R.layout.list_item_group, viewGroup, false) );
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.tvGroupName.setText( groups.get(i).group_name );

        //if (!groups.get(i).image_URL.equals(""))
          //  Picasso.get().load( groups.get(i).image_URL ).into( myViewHolder.imgGroup );

        UniversalImageLoader.setImage(groups.get(i).image_URL,
                myViewHolder.imgGroup, myViewHolder.progressBar);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivityChat(myViewHolder.getAdapterPosition());
            }
        });
    }


    private void goToActivityChat(int position) {
        Intent intent = new Intent(context, ActivityGroupChat.class);
        intent.putExtra("groupId", groups.get(position).group_id);
        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return groups.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        // properties
        private TextView tvGroupName;
        private ImageView imgGroup;
        private View itemView;
        private ProgressBar progressBar;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGroupName = itemView.findViewById(R.id.tvGroupName);
            imgGroup = itemView.findViewById(R.id.imgGroup);
            this.itemView = itemView;
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
