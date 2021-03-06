package com.example.chatter.Main.FragmentAllUsers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chatter.Main.MainActivity;
import com.example.chatter.Modals.User;
import com.example.chatter.R;
import com.example.chatter.Utils.UniversalImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecAllUsers extends RecyclerView.Adapter<AdapterRecAllUsers.MyViewHolder> {

    // properties
    private ArrayList<User> contacts;
    private LayoutInflater inflater;
    private Context context;


    // constructor
    AdapterRecAllUsers(Context context, ArrayList<User> contacts) {
        inflater = LayoutInflater.from(context);
        this.contacts = contacts;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder( inflater.inflate(R.layout.list_item_user, viewGroup, false) );
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        UniversalImageLoader.setImage(contacts.get(i).image_URL,
                myViewHolder.imgProfile, myViewHolder.progressBar);

        myViewHolder.tvUserName.setText( contacts.get(i).username );

        if (!contacts.get(i).status.equals(""))
            myViewHolder.tvStatus.setText( contacts.get(i).status );

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).goToProfileFragment(
                        contacts.get( myViewHolder.getAdapterPosition() ));
            }
        });
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        // properties
        private View itemView;
        private ImageView imgProfile;
        private TextView tvUserName;
        private TextView tvStatus;
        private ProgressBar progressBar;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            imgProfile = itemView.findViewById(R.id.imgProfile);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}