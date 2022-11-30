package com.example.demoapi;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitCallbacksController {
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private static RetrofitCallbacksController retrofitCallbacks;
    private static Retrofit retrofit = null;

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiCollection.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetrofitCallbacksController getInstace() {
        if (retrofitCallbacks == null) {
            retrofitCallbacks = new RetrofitCallbacksController();
        }
        return retrofitCallbacks;
    }

    public void fillcontext(Context context) {
        this.context = context;
    }

    public boolean checkNetwork() {
        boolean wifiAvailable = false;
        boolean mobileAvailable = false;
        ConnectivityManager conManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = conManager.getAllNetworkInfo();
        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    wifiAvailable = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    mobileAvailable = true;
        }
        return wifiAvailable || mobileAvailable;
    }
    public void ApiCallbacksForAllPosts(Context context, String NotifyMsg, String EndUrl, JsonObject jsonobj, String accessToken) {
        this.context = context;
        ApiCollection apiCollection = getClient().create(ApiCollection.class);
        Call<ResponseBody> call = apiCollection.apisCalling(jsonobj, EndUrl);
        Log.e("post", "" + accessToken);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                String bodyString = null;
                Log.e("NotifyMsg", " " + response.body());
                if (response.body() != null) {
                    try {
                        bodyString = new String(response.body().bytes());
                        Log.e("NotifyMsg", " " + bodyString);
                        EventBus.getDefault().post(new MessageEvent(bodyString,NotifyMsg));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    EventBus.getDefault().post(new MessageEvent("RetrofitApiCallbacksPostnull", bodyString));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                EventBus.getDefault().post(new MessageEvent("onFailure", null));
                Toast.makeText(context, ":" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    public static class MessageEvent {
        public String message, body;

        public MessageEvent(String body,String msg) {
            this.body = body;
            this.message=msg;
        }
    }

}
