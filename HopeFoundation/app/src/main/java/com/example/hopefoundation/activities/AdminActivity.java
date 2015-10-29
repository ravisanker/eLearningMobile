package com.example.hopefoundation.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.hopefoundation.R;
import com.example.hopefoundation.adapters.StudentListAdapter;
import com.example.hopefoundation.views.DividerItemDecoration;

public class AdminActivity extends AppCompatActivity {

    RecyclerView rvStudentList;
    ProgressBar pbLoading;
    StudentListAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        FetchStudentsService.startService();
        rvStudentList = (RecyclerView) findViewById(R.id.rv_centre_data);
        rvStudentList.setLayoutManager(new LinearLayoutManager(this));
        rvStudentList.setAdapter(rvAdapter);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        rvStudentList.addItemDecoration(itemDecoration);
    }
}
