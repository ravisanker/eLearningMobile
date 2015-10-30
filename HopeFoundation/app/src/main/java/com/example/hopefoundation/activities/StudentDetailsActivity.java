package com.example.hopefoundation.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hopefoundation.R;
import com.example.hopefoundation.services.FetchStudentsService;
import com.example.hopefoundation.services.StudentDetailsService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ravi sanker on 10/30/2015.
 */
public class StudentDetailsActivity extends AppCompatActivity {
    BroadcastReceiver mBroadcastReceiver;
    private TextView tvAadhaar;
    private TextView tvAddress;
    private TextView tvCentre;
    private TextView tvDoB;
    private TextView tvEnrollDate;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvLevel;
    private TextView tvHours;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details);
        tvAadhaar = (TextView) findViewById(R.id.tv_aadhaar);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvCentre = (TextView) findViewById(R.id.tv_centre);
        tvDoB = (TextView) findViewById(R.id.tv_dob);
        tvEnrollDate = (TextView) findViewById(R.id.tv_enroll_date);
        tvFirstName = (TextView) findViewById(R.id.tv_first_name);
        tvLastName = (TextView) findViewById(R.id.tv_last_name);
        tvLevel = (TextView) findViewById(R.id.tv_level);
        tvHours = (TextView) findViewById(R.id.tv_hours);
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, StudentDetailsService.class);
        intent.putExtra("username", extras.getString("username"));
        startService(intent);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    JSONObject jsonObject = new JSONObject(getSharedPreferences("MyPrefs",
                            Context.MODE_PRIVATE).getString("student_details", null));
                    tvLevel.setText(jsonObject.getString("level"));
                    tvHours.setText(jsonObject.getString("hours_completed"));
                    JSONObject obj = jsonObject.getJSONObject("user");
                    tvFirstName.setText(obj.getString("fname"));
                    tvLastName.setText(obj.getString("lname"));
                    tvAddress.setText(obj.getString("address"));
                    tvAadhaar.setText(obj.getString("aadharCard"));
                    tvDoB.setText(obj.getString("dob"));
                    tvEnrollDate.setText(obj.getString("enrollDate"));
                    tvCentre.setText(obj.getString("centre"));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }


            }
        };
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver,
                new IntentFilter(StudentDetailsService.KEY_SUCCESS));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }
}
