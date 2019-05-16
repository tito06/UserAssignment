package com.example.userassignment.adapter;

import android.service.autofill.UserData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userassignment.R;
import com.example.userassignment.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ArrayList<User> userData;

    public UserAdapter(ArrayList<User> userData) {
        this.userData = userData;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_user, viewGroup, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

        userViewHolder.firstName.setText(userData.get(i).getFirst_name());
        userViewHolder.lastName.setText(userData.get(i).getLast_name());
        userViewHolder.email.setText(userData.get(i).getEmail());

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, email;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.user_name);
            lastName = itemView.findViewById(R.id.user_last_name);
            email = itemView.findViewById(R.id.user_email);
        }
    }
}
