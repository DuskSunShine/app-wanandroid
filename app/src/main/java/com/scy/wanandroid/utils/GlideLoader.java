package com.scy.wanandroid.utils;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;


import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created on 2017/11/6 0006.
 */

public class GlideLoader {

    public static void loadImage(Context context, ImageView iv, String url, int errorImg, int emptyImg) {
        RequestOptions requestOptions = new RequestOptions()
                .override(iv.getWidth(), iv.getHeight())
                .placeholder(emptyImg)
                .error(errorImg);
        Glide.with(context).load(url).transition(withCrossFade()).apply(requestOptions).into(iv);
    }
    public static void loadImage(Context context, ImageView iv, String url) {
        RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(requestOptions).into(iv);
    }

    public static void loadImage(Context context, ImageView iv, String url, int errorImg) {
        RequestOptions requestOptions = new RequestOptions()
                .override(iv.getWidth(), iv.getHeight())
                .error(errorImg);
        Glide.with(context).load(url).transition(withCrossFade()).apply(requestOptions).into(iv);
    }

    public static void loadImage2(Context context, ImageView iv, String url) {
        RequestOptions requestOptions = new RequestOptions().override(iv.getWidth(), iv.getHeight());
        Glide.with(context).load(url).transition(withCrossFade()).apply(requestOptions).into(iv);
    }

    public static void loadThumbnailImage(Context context, ImageView iv, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .encodeFormat(Bitmap.CompressFormat.JPEG)
                .format(DecodeFormat.PREFER_RGB_565)
                .encodeQuality(60)
                .override(iv.getWidth(), iv.getHeight());
        Glide.with(context).load(url).transition(withCrossFade()).apply(requestOptions).into(iv);
    }

    public static void loadImageListener(Context context, ImageView iv, String url,
                                         RequestListener<Drawable> requestListener) {
        RequestOptions requestOptions = new RequestOptions()
                .override(iv.getWidth(), iv.getHeight());
        Glide.with(context).load(url).apply(requestOptions).listener(requestListener).into(iv);
    }


    public static void loadRoundCornerImageListener(Context context, ImageView iv, String url,
                                                    RequestListener<Drawable> requestListener) {
        RequestOptions requestOptions = new RequestOptions()
                .override(iv.getWidth(), iv.getHeight())
                .transform(new RoundedCorners(10));
        Glide.with(context).load(url).apply(requestOptions).listener(requestListener).into(iv);
    }


    public static void loadCircleImage(Context context, ImageView iv, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .transforms(new CircleCrop())
                .override(iv.getWidth(), iv.getHeight());
        Glide.with(context).load(url).transition(withCrossFade()).apply(requestOptions)
                .into(iv);
    }


    public static void loadRoundCornerImage(Context context, ImageView iv, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .transforms(new RoundedCorners(10))
                .override(iv.getWidth(), iv.getHeight());
        Glide.with(context).load(url).transition(withCrossFade()).apply(requestOptions)
                .into(iv);
    }

    public static void loadImage(Context context, final ImageView imageView, final File file) {
        RequestOptions requestOptions = new RequestOptions()
                .override(imageView.getWidth(), imageView.getHeight());
        Glide.with(context)
                .load(file)
                .transition(withCrossFade())
                .apply(requestOptions)
                .into(imageView);
    }

    public static void loadImage(Context context, final ImageView imageView, final int resourceId) {
        RequestOptions requestOptions = new RequestOptions()
                .override(imageView.getWidth(), imageView.getHeight());
        Glide.with(context)
                .load(resourceId)
                .transition(withCrossFade())
                .apply(requestOptions)
                .into(imageView);
    }

    public static void loadImageWrapWidthHeight(Context context, final ImageView iv, String url) {
        Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                int imgWith = resource.getIntrinsicWidth();
                int imgHeight = resource.getIntrinsicHeight();
                ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
                layoutParams.width = imgWith;
                layoutParams.height = imgHeight;
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setImageDrawable(resource);
            }
        });
    }

    public static void loadImageWrapWidthHeight(Context context, final ImageView iv, String url, final int minWith,
                                                final int minHeight, final int maxWith, final int maxHeight) {
        Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                int imgWith = resource.getIntrinsicWidth();
                int imgHeight = resource.getIntrinsicHeight();
                ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
                Point point = computeWH((double) imgWith, (double) imgHeight, (double) minWith,
                        (double) minHeight, (double) maxWith, (double) maxHeight);
                layoutParams.width = point.x;
                layoutParams.height = point.y;
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv.setImageDrawable(resource);
            }
        });
    }

    private static Point computeWH(double imgWith, double imgHeight, double minWith, double minHeight,
                                   double maxWith, double maxHeight) {
        double scaleImage;
        double scaleX;
        double scaleY;
        if (imgWith > maxWith || imgHeight > maxHeight) {
            scaleX = maxWith / imgWith;
            scaleY = maxHeight / imgHeight;
            scaleImage = Math.min(scaleX, scaleY);
        } else if (imgWith < minWith || imgHeight < minHeight) {
            scaleX = minWith / imgWith;
            scaleY = minHeight / imgHeight;
            if (scaleX > scaleY) {
                if (scaleX * imgHeight > maxHeight) {
                    scaleImage = maxHeight / imgHeight;
                } else {
                    scaleImage = scaleX;
                }
            } else {
                if (scaleY * imgWith > maxWith) {
                    scaleImage = maxWith / imgWith;
                } else {
                    scaleImage = scaleY;
                }
            }
        } else {
            scaleImage = 1;
        }
        return new Point((int) (scaleImage * imgWith), (int) (scaleImage * imgHeight));
    }
}
