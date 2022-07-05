package com.blogspot.codesgram.merkletest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.codesgram.merkletest.ConfigAPI.Client;
import com.blogspot.codesgram.merkletest.ConfigAPI.ProductAPI;
import com.blogspot.codesgram.merkletest.Models.UserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    TextView logtok;
    Button btnLogin;
    ProductAPI productAPI;
    ArrayList<UserModel> arrayList=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SplashScreen splashScreen=SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_login);

        //Customized action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_login);

        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserData();

                if (email.getText().toString().equals("") && password.getText().toString().equals(""))
                {
                    email.setError("Enter Your Email");
                    password.setError("Enter Your Password");

                }
                else
                {
                    for (int i=0;i<arrayList.size();i++)
                    {
                        if (arrayList.get(i).getEmail().equals(email.getText().toString()) &&
                                arrayList.get(i).getPassword().equals(password.getText().toString())) {
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(),"Login Sucessful", Toast.LENGTH_SHORT).show();
                            return;

                        } else {
                            Toast.makeText(getApplicationContext(),"Inavlid Credentials",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }

            public void getUserData(){

                productAPI= Client.getRetrofitInstance().create(ProductAPI.class);
                productAPI.getUsers().enqueue(new Callback<ArrayList<UserModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {

                        if (response.body().size()>0) {
                            arrayList=response.body();
                        }

                        else {
                            Toast.makeText(getApplicationContext(),"Error in Fetching data",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        logtok = findViewById(R.id.logtok);
        logtok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LoginToken.class);
                startActivity(intent);
            }
        });

    }

}