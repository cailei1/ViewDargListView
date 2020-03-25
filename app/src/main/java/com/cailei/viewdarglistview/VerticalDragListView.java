package com.cailei.viewdarglistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ListViewCompat;
import androidx.customview.widget.ViewDragHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author : cailei
 * @date : 2020-03-25 08:19
 * @description :
 */
public class VerticalDragListView extends FrameLayout {
    private ViewDragHelper mViewDragHelper;

    private View mBackView;
    private int mBackViewHeight;
    private View mFontView;
    private boolean isMenuOpen;


    public VerticalDragListView(@NonNull Context context) {
        this(context, null);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalDragListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mViewDragHelper = ViewDragHelper.create(this, viewDragCallBack);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mBackViewHeight = mBackView.getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        mBackView = getChildAt(0);
        mFontView = getChildAt(1);
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback viewDragCallBack = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            //子View 是否可以拖动
            if (child.getId() == R.id.front_view) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            //子View 可以垂直拖动
            //垂直拖动的距离只能是后面菜单的距离
            if (top <= 0) {
                top = 0;
            }
            if (top > mBackView.getMeasuredHeight()) {
                top = mBackView.getMeasuredHeight();
            }

            return top;
        }

//        @Override
//        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
//            //子View 可以水平拖动
//            return left;
//        }


        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            if (releasedChild == mFontView) {
                if (mFontView.getTop() > mBackViewHeight / 2) {
                    //滑动前面的View到指定位置
                    mViewDragHelper.settleCapturedViewAt(0, mBackViewHeight);
                    isMenuOpen = true;
                } else {
                    mViewDragHelper.settleCapturedViewAt(0, 0);
                    isMenuOpen = false;
                }
                //必须刷新一下才能滑动
                invalidate();
            }
        }


    };

    // 响应滚动
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
        super.computeScroll();
    }

    private float mDownY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isMenuOpen) {
            return true;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                mViewDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                float mMoveY = ev.getY();
                if (mMoveY - mDownY > 0&!canChildScrollUp()) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean canChildScrollUp() {
        if (mFontView instanceof ListView) {
            return ListViewCompat.canScrollList((ListView) mFontView, -1);
        }
        return mFontView.canScrollVertically(-1);
    }
}
