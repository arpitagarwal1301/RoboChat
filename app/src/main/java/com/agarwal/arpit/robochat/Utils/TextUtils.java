package com.agarwal.arpit.robochat.Utils;

import android.content.Context;
import android.widget.Toast;

public class TextUtils {

    public static void showToast(Context context, String errorMsg){
        Toast.makeText(context,errorMsg,Toast.LENGTH_SHORT).show();
    }
}
