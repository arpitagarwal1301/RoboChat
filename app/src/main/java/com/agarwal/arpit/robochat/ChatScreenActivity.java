package com.agarwal.arpit.robochat;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.agarwal.arpit.robochat.entities.ChatResponse;
import com.agarwal.arpit.robochat.network.GsonVolleyRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import static com.agarwal.arpit.robochat.Utils.AppConstants.URL_TAG;
import static com.agarwal.arpit.robochat.Utils.AppConstants.apiKey;
import static com.agarwal.arpit.robochat.Utils.AppConstants.chatBotID;
import static com.agarwal.arpit.robochat.Utils.AppConstants.externalID;
import static com.agarwal.arpit.robochat.Utils.AppConstants.hostUrl;
import static com.agarwal.arpit.robochat.Utils.TextUtils.showToast;

public class ChatScreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen_activity);


        //setUpViews();
        //setUpController();
        sendMessageRequest("");
    }


    private void setUpViews() {

    }

    private void setUpController() {

    }

    private void sendMessageRequest(String message){
        message = "hi" ;
        final String url = hostUrl+"?apiKey="+apiKey+"&message="+message+"&chatBotID="+chatBotID+"&externalID="+externalID;

        GsonVolleyRequest gsonRequest = new GsonVolleyRequest(url, ChatResponse.class, null, new Response.Listener<ChatResponse>() {
            @Override
            public void onResponse(ChatResponse response) {
                // Handle response
                handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast(getApplicationContext(),error.getMessage());
            }
        });
        gsonRequest.makeReqeust(this,gsonRequest, URL_TAG);
    }

    private void handleResponse(ChatResponse response) {
        if (response != null ){

            if (response.getSuccess()==1){
                //TODO :: add in the list of message and populate in recyclerview
            }else {
                showToast(getApplicationContext(),"Message Error");
            }


        }
    }


}
