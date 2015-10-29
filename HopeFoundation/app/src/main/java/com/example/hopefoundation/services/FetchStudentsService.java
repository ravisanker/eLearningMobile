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
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        //String url = "10.207.115.110:3000/api/studentsByFaculty" + username;
        String url = "http://10.207.115.110:3000/user";
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
        /** response format
         *
         * {[
         * "tag": "my_plan",
         * "title": "My Plan",
         * "url": "http://" + settings.ROOT_URL + "/my-diet-chart",
         * "icon": {"ic_my_plan"/"http://healthifyme.com/myplan.jpg"},
         * "icon_type": {"drawable"/"url"}
         * ],  }
         *
         */
        // DynamicMenuEntryPref.getInstance().setMenuEntriesJSON(responseMessage).commit();
        sharedPreferences.edit().putString("all_data", responseMessage.toString()).commit();
        Log.d(DEBUG_TAG, "success");
        Intent success = new Intent(PLANS);
        sendBroadcast(success);

    }
}
