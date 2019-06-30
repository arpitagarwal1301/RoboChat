package com.agarwal.arpit.robochat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agarwal.arpit.robochat.Utils.MessageType;
import com.agarwal.arpit.robochat.Utils.TextUtils;
import com.agarwal.arpit.robochat.entities.ChatMessageItem;
import com.agarwal.arpit.robochat.entities.ChatResponse;
import com.agarwal.arpit.robochat.entities.Message;
import com.agarwal.arpit.robochat.network.GsonVolleyRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.agarwal.arpit.robochat.Utils.AppConstants.URL_TAG;
import static com.agarwal.arpit.robochat.Utils.AppConstants.apiKey;
import static com.agarwal.arpit.robochat.Utils.AppConstants.chatBotID;
import static com.agarwal.arpit.robochat.Utils.AppConstants.externalID;
import static com.agarwal.arpit.robochat.Utils.AppConstants.hostUrl;
import static com.agarwal.arpit.robochat.Utils.TextUtils.showToast;

public class ChatScreenActivity extends AppCompatActivity {


    @BindView(R.id.content_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.chat_edit_text)
    EditText msgEditText;

    @BindView(R.id.chat_send_btn)
    Button sendBtn;

    private final List<ChatMessageItem> mAdapterList = new ArrayList<>();
    private ChatRecyclerAdapter adapter ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen_activity);
        ButterKnife.bind(this);

        setUpController();
    }

    private void setUpController() {

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        adapter = new ChatRecyclerAdapter(mAdapterList, view -> {
           // int position = (int)view.getTag();

        });
        recyclerView.setAdapter(adapter);

    }

    /*
    Checking the edit text for empty string before sending the message
     */
    private void sendMessage() {
        String message = msgEditText.getText().toString().trim();

        if (message.isEmpty()) {
            TextUtils.showToast(this, getString(R.string.empty_msg_error));
        } else {
            addMessageToList(message);
            sendMessageRequest(message);
            msgEditText.setText("");
        }

    }

    /*
    Adding the send message to list and displaying in the list
     */
    private void addMessageToList(String message) {
        ChatMessageItem item = new ChatMessageItem();
        item.setName(externalID);
        item.setMsg(message);
        item.setType(MessageType.SENDING);
        item.setTime(TextUtils.getCurrentTime());
        mAdapterList.add(item);

        adapter.notifyDataSetChanged();
    }

    /*
    Send the request
     */
    private void sendMessageRequest(String message) {

        final String url = hostUrl + "?apiKey=" + apiKey + "&message=" + message + "&chatBotID=" + chatBotID + "&externalID=" + externalID;

        GsonVolleyRequest gsonRequest = new GsonVolleyRequest(url, ChatResponse.class, null, new Response.Listener<ChatResponse>() {
            @Override
            public void onResponse(ChatResponse response) {
                // Handle response
                handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast(getApplicationContext(), error.getMessage());
            }
        });
        gsonRequest.makeReqeust(this, gsonRequest, URL_TAG);
    }

    private void handleResponse(ChatResponse response) {
        if (response != null) {

            if (response.getSuccess() == 1) {
                Message m = response.getMessage();
                if (m!=null){
                    ChatMessageItem item = new ChatMessageItem();
                    item.setName(m.getChatBotName());
                    item.setMsg(m.getMessage());
                    item.setType(MessageType.RECEIVING);
                    item.setTime(TextUtils.getCurrentTime());
                    mAdapterList.add(item);

                    adapter.notifyDataSetChanged();
                }


            } else {
                showToast(getApplicationContext(), "Message Error");
            }


        }
    }


}
