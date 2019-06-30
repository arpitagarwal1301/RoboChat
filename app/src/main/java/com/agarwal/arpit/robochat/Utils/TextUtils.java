package com.agarwal.arpit.robochat.Utils;

import android.content.Context;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TextUtils {

    public static void showToast(Context context, String errorMsg){
        Toast.makeText(context,errorMsg,Toast.LENGTH_SHORT).show();
    }

    public static String getCurrentTime(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
