package com.agarwal.arpit.robochat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agarwal.arpit.robochat.database.UserEntity;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.RHSViewHolder> implements View.OnClickListener{

    private final List<UserEntity> mObjectList ;
    private final RecyclerClickInterface mClickInterface;

    public UserRecyclerAdapter(List<UserEntity> objectList, RecyclerClickInterface interfaceImplementaion) {
        mObjectList = objectList;
        mClickInterface = interfaceImplementaion;
    }

    @NonNull
    @Override
    public RHSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
            return new RHSViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RHSViewHolder holder, int position) {
        String item = mObjectList.get(position).getName();

        holder.name.setText(item);
        holder.parentLayout.setOnClickListener(this::onClick);
        holder.parentLayout.setTag(position);

    }

    public class RHSViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        LinearLayout parentLayout;

        public RHSViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }


    @Override
    public int getItemCount() {
        return mObjectList.size();
    }

    @Override
    public void onClick(View v) {
        mClickInterface.onClickAction(v);
    }
}
