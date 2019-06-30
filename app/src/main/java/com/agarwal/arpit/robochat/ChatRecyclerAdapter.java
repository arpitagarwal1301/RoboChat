package com.agarwal.arpit.robochat;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agarwal.arpit.robochat.Utils.MessageType;
import com.agarwal.arpit.robochat.database.MessageEntity;

import java.util.List;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.RHSViewHolder> implements View.OnClickListener{

    private final List<MessageEntity> mObjectList ;
    private final RecyclerClickInterface mClickInterface;

    public ChatRecyclerAdapter(List<MessageEntity> objectList,RecyclerClickInterface interfaceImplementaion) {
        mObjectList = objectList;
        mClickInterface = interfaceImplementaion;
    }

    @NonNull
    @Override
    public RHSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
            return new RHSViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RHSViewHolder holder, int position) {
        MessageEntity item = mObjectList.get(position);
        String message = item.getMsg();
        String name = item.getName();
        String time= item.getTime();

        holder.name.setText(name);
        holder.message.setText(message);
        holder.time.setText(time);

        if (item.getType().equalsIgnoreCase(MessageType.SENDING.name())){
            holder.message.setGravity(Gravity.RIGHT);
            holder.parentLayout.setGravity(Gravity.RIGHT);
        }else {
            holder.message.setGravity(Gravity.LEFT);
            holder.parentLayout.setGravity(Gravity.LEFT);
        }


    }

    public class RHSViewHolder extends RecyclerView.ViewHolder{

        TextView name,message,time;
        LinearLayout parentLayout;

        public RHSViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
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
