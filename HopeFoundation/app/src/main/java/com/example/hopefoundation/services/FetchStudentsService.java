package com.example.hopefoundation.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.hopefoundation.rest.GetRestClient;
import com.example.hopefoundation.rest.ResponseStatusMessage;

/**
 * Created by Ravi sanker on 10/30/2015.
 */
public class FetchStudentsService extends IntentService {
    public static final String PLANS = "plans";
    public static String DEBUG_TAG = FetchStudentsService.class.getSimpleName();
    private SharedPreferences sharedPreferences;

    public FetchStudentsService() {
        super(DEBUG_TAG);
    }

    public static void startService(Activity context) {
        Intent intent = new Intent(context, FetchStudentsService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        String url = "http://10.207.114.12:3000/api/studentsByFaculty?username=" + username;
        Log.d(DEBUG_TAG, "Url: " + url);
        GetRestClient getRestClient = new GetRestClient();
        ResponseStatusMessage resp = getRestClient.getResponse(url);
        if (resp.isFailure()) {
            return;
        }
        String responseMessage = resp.getResponseMessage();
        Log.d(DEBUG_TAG, "Response: " + responseMessage);
        handleResponse(responseMessage);
    }

    public void handleResponse(String responseMessage) {
        sharedPreferences.edit().putString("all_data", responseMessage.toString()).commit();
        Log.d(DEBUG_TAG, "success");
        Intent success = new Intent(PLANS);
        sendBroadcast(success);

    }
}
