package com.susheelkaram.dating_app.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.susheelkaram.dating_app.R;
import com.susheelkaram.dating_app.databinding.ItemUserProfileBinding;
import com.susheelkaram.dating_app.util.UiUtils;

import org.jetbrains.annotations.NotNull;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class ProfileCardView extends CardView {
    String title;
    String subTitle;
    int imgResource;
    int viewRatioX;
    int viewRatioY;
    boolean shouldBlurImage;
    float titleTextSize;
    float subTitleTextSize;

    ItemUserProfileBinding binding;

    public ProfileCardView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ProfileCardView, 0, 0);
        viewRatioX = attributes.getInt(R.styleable.ProfileCardView_viewRatioX, 1);
        viewRatioY = attributes.getInt(R.styleable.ProfileCardView_viewRatioY, viewRatioX);
        shouldBlurImage = attributes.getBoolean(R.styleable.ProfileCardView_blurImage, false);
        titleTextSize = attributes.getDimension(R.styleable.ProfileCardView_titleTextSize, UiUtils.spToPx( 22f, getContext()));
        subTitleTextSize = attributes.getDimension(R.styleable.ProfileCardView_subtitleTextSize, UiUtils.spToPx(15f, getContext()));
        int imgSrc = attributes.getResourceId(R.styleable.ProfileCardView_imgSrc, R.drawable.ic_discover_1);
        attributes.recycle();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemUserProfileBinding.inflate(layoutInflater, this, true);
        binding.txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        binding.txtSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, subTitleTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (width * viewRatioY) / viewRatioX;
        this.setMeasuredDimension(width, height);
        measureChildren(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    public void setData(ProfileGlimpseData profileGlimpseData) {
        binding.txtTitle.setText(profileGlimpseData.title);

        if (profileGlimpseData.subTitle == null || profileGlimpseData.subTitle.isEmpty()) {
            binding.txtSubtitle.setVisibility(View.GONE);
        } else {
            binding.txtSubtitle.setVisibility(View.VISIBLE);
            binding.txtSubtitle.setText(profileGlimpseData.subTitle);
        }

        MultiTransformation<Bitmap> transformations;

        if (shouldBlurImage)
            transformations = new MultiTransformation<Bitmap>(new GlideBlurTransformation(getContext(), 25, 0.4f), new CenterCrop());
        else
            transformations = new MultiTransformation<Bitmap>(new CenterCrop());

        if (profileGlimpseData.imageUrl != null || profileGlimpseData.imageSrc != 0) {
            // TODO: Revert diskCacheStrategy and skipMemoryCache after testing
            Glide.with(this)
                    .load((profileGlimpseData.imageUrl != null) ? profileGlimpseData.imageUrl : profileGlimpseData.imageSrc)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .apply(bitmapTransform(transformations))
                    .into(binding.imgProfilePic);
        }
    }

}
