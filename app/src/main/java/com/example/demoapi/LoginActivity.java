package com.example.demoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.demoapi.ServerResponseModels.LoginResponseModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    EditText userid, password;
    Button btnLogin;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading....");
        actions();
        RetrofitCallbacksController.getInstace().fillcontext(LoginActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(LoginActivity.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(LoginActivity.this);
    }

@Subscribe(threadMode=ThreadMode.MAIN)
public void onEventMainThread(RetrofitCallbacksController.MessageEvent messageEvent){
        progressDialog.dismiss();
    Log.e("response","call"+messageEvent.body);
    if(messageEvent.message.contains("loginApi")) {
        if (messageEvent.body != null) {
            try {
                JSONObject jObj = new JSONObject(messageEvent.body);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            Gson gson = new Gson();
            LoginResponseModel loginResponseModel = gson.fromJson(messageEvent.body, LoginResponseModel.class);
            Log.e("loginrespoise", "call" + loginResponseModel.getResponse());
            if(loginResponseModel.getResponse().equals(3)){
                Toast.makeText(getApplicationContext(),loginResponseModel.getMessage(),Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),loginResponseModel.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }else{

    }
}


    private void actions() {

        userid = findViewById(R.id.userid);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userid.getText().toString().isEmpty()|| password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username or Password Required", Toast.LENGTH_LONG).show();
                } else {
                    if(RetrofitCallbacksController.getInstace().checkNetwork()){
                        progressDialog.show();
                          loadLoginApiParams();
                    }else {
                        Toast.makeText(LoginActivity.this, "No internet connection.", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
    public void loadLoginApiParams(){
        JsonObject CheckUserObj = new JsonObject();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userID", userid.getText().toString());
            jsonObject.put("Password", password.getText().toString());
            JsonParser jsonParser = new JsonParser();
            CheckUserObj = (JsonObject) jsonParser.parse(jsonObject.toString());
            Log.e("checkBuyProject:", " " + CheckUserObj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RetrofitCallbacksController.getInstace().ApiCallbacksForAllPosts(LoginActivity.this,"loginApi","seller/Sellerlogin",CheckUserObj,"");
    }
}