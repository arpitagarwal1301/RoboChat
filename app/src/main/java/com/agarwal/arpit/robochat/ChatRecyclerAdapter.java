package com.agarwal.arpit.robochat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agarwal.arpit.robochat.Utils.MessageType;
import com.agarwal.arpit.robochat.entities.ChatMessageItem;

import java.util.List;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private final List<ChatMessageItem> mObjectList ;
    private final RecyclerClickInterface mClickInterface;
    private Context mActivityContext = null;

    private final static int TYPE_SEND = 1;
    private final static int TYPE_RECEIVE = 2;


    public ChatRecyclerAdapter(List<ChatMessageItem> objectList,RecyclerClickInterface interfaceImplementaion) {
        mObjectList = objectList;
        mClickInterface = interfaceImplementaion;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==TYPE_RECEIVE){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lhs_chat_item, parent, false);
            return new LHSViewHolder(itemView);
        }else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rhs_chat_item, parent, false);
            return new RHSViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessageItem item = mObjectList.get(position);
        String message = item.getMsg();
        String name = item.getName();
        String time= item.getTime();


        if (holder instanceof RHSViewHolder){
            ((RHSViewHolder) holder).name.setText(name);
            ((RHSViewHolder) holder).message.setText(message);
            ((RHSViewHolder) holder).time.setText(time);
        }else {
            ((LHSViewHolder) holder).name.setText(name);
            ((LHSViewHolder) holder).message.setText(message);
            ((LHSViewHolder) holder).time.setText(time);
        }

    }

    public class RHSViewHolder extends RecyclerView.ViewHolder{

        TextView name,message,time;

        public RHSViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
        }
    }

    public class LHSViewHolder extends RecyclerView.ViewHolder{

        TextView name,message,time;

        public LHSViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);

        }
    }

    @Override
    public int getItemCount() {
        return mObjectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessageItem item = mObjectList.get(position);
        if (item.getType()== MessageType.RECEIVING){
            return TYPE_RECEIVE;
        }else {
            return TYPE_SEND;
        }
    }

    @Override
    public void onClick(View v) {
        mClickInterface.onClickAction(v);
    }
}
