package com.example.michong_pc.tiku.ViewFlipper;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/**
 * Created by MiChong-pc on 2016/5/30.
 */
public class MyGestureListener extends SimpleOnGestureListener {
    private OnFlingListener mOnFlingListener;
    public final boolean onFling(final MotionEvent e1, final MotionEvent e2,
                                 final float speedX, final float speedY) {
        if (mOnFlingListener == null) {
            return super.onFling(e1, e2, speedX, speedY);
        }

        float XFrom = e1.getX();  //按下坐标
        float XTo = e2.getX();
        float YFrom = e1.getY();
        float YTo = e2.getY();
        // 左右滑动的X轴幅度大于100，并且X轴方向的速度大于100
        if (Math.abs(XFrom - XTo) > 100.0f && Math.abs(speedX) > 100.0f) {
            // X轴幅度大于Y轴的幅度
            if (Math.abs(XFrom - XTo) >= Math.abs(YFrom - YTo)) {
                if (XFrom > XTo) {
                    // 下一个
                    mOnFlingListener.flingToNext();
                } else {
                    // 上一个
                    mOnFlingListener.flingToPrevious();
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**自定义滑动的回调接口**/
    public interface OnFlingListener {
        void flingToNext();  //滑动到下一页

        void flingToPrevious();  //滑动到上一页
    }

    public OnFlingListener getOnFlingListener() {
        return mOnFlingListener;
    }

    public void setOnFlingListener(OnFlingListener mOnFlingListener) {
        this.mOnFlingListener = mOnFlingListener;
    }

}
