package com.example.hopefoundation.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hopefoundation.R;
import com.example.hopefoundation.activities.StudentDetailsActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by I324201 on 10/30/2015.
 */
public class StudentListAdapterv2 extends RecyclerView.Adapter<StudentListAdapterv2.CustomViewHolder> {
    ArrayList<String> names;

    public StudentListAdapterv2(ArrayList<String> names) {
        this.names = names;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_single_item_v2, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        holder.tvStudentName.setText(names.get(position));
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentDetailsActivity.class);
                intent.putExtra("username", userNames.get(position));
                context.startActivity(intent);
            }
        }); */
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentName;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tvStudentName = (TextView) itemView.findViewById(R.id.tv_student_name);
        }
    }
}

