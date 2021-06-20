package com.susheelkaram.dating_app.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class UiUtils {
    public static float spToPx(float sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
