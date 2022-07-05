package com.blogspot.codesgram.merkletest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginToken extends AppCompatActivity {
    private EditText edt_username, edt_password;
    private String username,password;
    private TextView logem;

    //SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_token);

        //Customized action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_login);

        //initializing Username and Password Edit Texts
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

        logem = findViewById(R.id.logem);
        logem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginToken.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void login(View view) throws ExecutionException, InterruptedException {
        //get Username and Password
        username = edt_username.getText().toString();
        password = edt_password.getText().toString();

        LoginRequest loginRequest = new LoginRequest();
        String authenticationResponse = loginRequest.execute(username,password).get();

        if(!authenticationResponse.equals("error")){
            try {
                //try to parse JSON response
                JSONObject responseJson = new JSONObject(authenticationResponse);
                if(responseJson.get("token")!=null){
                    Toast.makeText(getApplicationContext(),"Login successful!",Toast.LENGTH_LONG).show();

                    //move to Main Activity

                    //SharedPreferences.Editor editor= sharedPreferences.edit();
                    //editor.apply();
                    Intent intent = new Intent (this, MainActivity.class);
                    intent.putExtra("token",responseJson.get("token").toString()); //pass the authentication token
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                //response is not JSON
                e.printStackTrace();
                //try to parse response
                switch (authenticationResponse){
                    case "username or password is incorrect":
                        Toast.makeText(getApplicationContext(),"Invalid credentials!",Toast.LENGTH_LONG).show();
                        break;
                    case "403 Forbidden":
                        Toast.makeText(getApplicationContext(),"An error occurred. Check the User-Agent",Toast.LENGTH_LONG).show();
                    default:
                        Toast.makeText(getApplicationContext(),"An error occurred when trying to authenticate",Toast.LENGTH_LONG).show();
                }
            }
        }
        else
        {
            //an unspecified error happened
            Toast.makeText(getApplicationContext(),"An error occurred when trying to authenticate",Toast.LENGTH_LONG).show();
        }


    }
}