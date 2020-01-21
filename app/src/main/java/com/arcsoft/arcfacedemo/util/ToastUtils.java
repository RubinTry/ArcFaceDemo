package com.arcsoft.arcfacedemo.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    private static Toast toast = null;
    private static Context context;
    
    public static void init(Context context){
        ToastUtils.context = context.getApplicationContext();
    }
    private static void showToast(Context context , CharSequence charSequence , int during){
        if(toast == null){
            toast = Toast.makeText(context , charSequence , during);
        }else{
            toast.setText(charSequence);
            toast.setDuration(during);
        }
        toast.show();
    }

    /**
     * 显示短时吐司
     * @param message
     */
    public static void showShortToast(CharSequence message){
        showToast(context , message , Toast.LENGTH_SHORT);
    }

    /**
     * 显示长时吐司
     * @param message
     */
    public static void showLongToast(CharSequence message){
        showToast(context , message , Toast.LENGTH_LONG);
    }
    
    public static void cancel(){
        if(toast != null){
            toast.cancel();
            toast = null;
        }
    }
}
