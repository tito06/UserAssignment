package com.example.userassignment.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userassignment.R;
import com.example.userassignment.model.LoginResponse;
import com.example.userassignment.model.User;
import com.example.userassignment.network.GetUserDataSerivce;
import com.example.userassignment.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText login_email, login_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.et_login_email);
        login_password =findViewById(R.id.et_login_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if (email == emailPattern) {

                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Signing Up...");
                    progressDialog.show();


                    GetUserDataSerivce serivce = RetrofitInstance.getRetrofitInstance().create(GetUserDataSerivce.class);

                    Call<LoginResponse> call = serivce.userLogin(email, password);

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body();
                            progressDialog.dismiss();
                            Intent intent = new Intent(Login.this, UserList.class);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }  else {
                    Toast.makeText(Login.this, "email.invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
