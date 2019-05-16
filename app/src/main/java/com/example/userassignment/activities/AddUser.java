package com.example.userassignment.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userassignment.R;
import com.example.userassignment.network.GetUserDataSerivce;
import com.example.userassignment.network.RetrofitInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUser extends AppCompatActivity {

    EditText et_name, et_job;
    Button btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        et_name = findViewById(R.id.et_name);
        et_job = findViewById(R.id.et_job);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                String job  = et_job.getText().toString().trim();

                GetUserDataSerivce service = RetrofitInstance.getRetrofitInstance().create(GetUserDataSerivce.class);

                Call<ResponseBody> call = service.createUser(name, job);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String s = response.body().toString();
                        Toast.makeText(AddUser.this, "success", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }
}
