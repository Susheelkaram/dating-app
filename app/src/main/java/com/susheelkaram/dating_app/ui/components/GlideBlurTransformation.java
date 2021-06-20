package com.susheelkaram.dating_app.ui.components;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;


public class GlideBlurTransformation extends BitmapTransformation {

    private RenderScript rs;
    private int blurRadius;
    private float scale;

    public GlideBlurTransformation(Context context, int blurRadius, float scale) {
        super();
        this.blurRadius = blurRadius;
        this.scale = scale;
        rs = RenderScript.create(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        int scaledWidth = (int) (toTransform.getWidth() * scale);
        int scaledHeight = (int) (toTransform.getHeight() * scale);

        Bitmap scaledDownBitmap = Bitmap.createScaledBitmap(toTransform, scaledWidth, scaledHeight, false);
        Bitmap blurredBitmap = scaledDownBitmap.copy(Bitmap.Config.ARGB_8888, true);

        Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
        Allocation output = Allocation.createTyped(rs, input.getType());

        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setInput(input);

        // Set the blur radius
        script.setRadius(blurRadius);

        script.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(blurredBitmap);
        Bitmap scaledUpBitmap = Bitmap.createScaledBitmap(blurredBitmap, toTransform.getWidth(), toTransform.getHeight(), false);

        return scaledUpBitmap;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update("blur transformation".getBytes());
    }
}
