package com.example.hopefoundation.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.hopefoundation.rest.GetRestClient;
import com.example.hopefoundation.rest.ResponseStatusMessage;

import org.json.JSONArray;

/**
 * Created by Ravi sanker on 10/30/2015.
 */
public class StudentDetailsService extends IntentService {
    public static final String DEBUG_TAG = StudentDetailsService.class.getSimpleName();
    public static final String KEY_SUCCESS = "success";

    public StudentDetailsService() {
        super(DEBUG_TAG);
    }

    public static void startService(Activity context) {
        Intent intent = new Intent(context, FetchStudentsService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String username = intent.getStringExtra("username");
        String url = "http://10.207.114.12:3000/student/" + username;
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
        //sharedPreferences.edit().putString("all_data", responseMessage.toString()).commit();
        Log.d(DEBUG_TAG, "success");
        getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit().putString("student_details", responseMessage).commit();
        Intent success = new Intent(KEY_SUCCESS);
        sendBroadcast(success);

    }
}
