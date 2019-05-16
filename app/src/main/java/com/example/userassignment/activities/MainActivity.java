package com.example.userassignment.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userassignment.R;
import com.example.userassignment.model.Registor;
import com.example.userassignment.network.GetUserDataSerivce;
import com.example.userassignment.network.RetrofitInstance;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    EditText et_email, et_password;
    Button btn_submit;
    //String s = "abc";
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email = findViewById(R.id.et_email);
        et_password =findViewById(R.id.et_password);
        btn_submit = findViewById(R.id.btn_submit);
        textView = findViewById(R.id.tv_login);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });



       btn_submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
               progressDialog.setMessage("Signing Up...");
               progressDialog.show();

               String email = et_email.getText().toString();
               String password = et_password.getText().toString();

               String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


               if (email == emailPattern) {


                   GetUserDataSerivce service = RetrofitInstance.getRetrofitInstance().create(GetUserDataSerivce.class);

                   Call<Registor> call = service.registorUser(email, password);

                   call.enqueue(new Callback<Registor>() {
                       @Override
                       public void onResponse(Call<Registor> call, Response<Registor> response) {
                           progressDialog.dismiss();
                           //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                           Intent intent = new Intent(MainActivity.this, UserList.class);
                           startActivity(intent);
                       }

                       @Override
                       public void onFailure(Call<Registor> call, Throwable t) {

                       }
                   });


               } else{
                   Toast.makeText(MainActivity.this, "email.invalid", Toast.LENGTH_LONG).show();
               }
           }
       });
    }
}