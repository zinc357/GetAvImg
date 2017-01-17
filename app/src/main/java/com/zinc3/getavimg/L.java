package com.zinc3.getavimg;

import android.util.Log;

/**
 * Created by zinc3 on 2017/1/17.
 */

public class L {
    private static final String TAG = "okhttp";
    private static boolean debug = true;
    public static void e(String msg){
        if (debug)
        Log.e(TAG,msg);
    }
}
