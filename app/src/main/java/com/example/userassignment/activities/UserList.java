package com.example.userassignment.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.userassignment.R;
import com.example.userassignment.adapter.UserAdapter;
import com.example.userassignment.model.User;
import com.example.userassignment.network.GetUserDataSerivce;
import com.example.userassignment.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserList extends AppCompatActivity {

    private UserAdapter userAdapter;
    private RecyclerView recyclerView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(UserList.this, AddUser.class);
                startActivity(intent);
            }
        });

       GetUserDataSerivce serivce = RetrofitInstance.getRetrofitInstance().create(GetUserDataSerivce.class);

        Call<com.example.userassignment.model.UserList> call = serivce.getUserData();

        call.enqueue(new Callback<com.example.userassignment.model.UserList>() {
            @Override
            public void onResponse(Call<com.example.userassignment.model.UserList> call, Response<com.example.userassignment.model.UserList> response) {
                generateUserList(response.body().getUserList());
            }

            @Override
            public void onFailure(Call<com.example.userassignment.model.UserList> call, Throwable t) {

            }
        });
    }

    private void generateUserList(ArrayList<User> userList){
        recyclerView = findViewById(R.id.recycler_view_user_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        userAdapter = new UserAdapter(userList);

        recyclerView.setAdapter(userAdapter);

    }
}
