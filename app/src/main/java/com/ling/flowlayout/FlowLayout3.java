package com.ling.flowlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * @author hao2.ling
 * @date 2021/4/6
 */
public class FlowLayout3 extends RelativeLayout {
    ArrayList<String> datas;


    public FlowLayout3(Context context) {
        super(context);
        init();
    }

    public FlowLayout3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlowLayout3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 记录所有行的 view 集合
     */
    ArrayList<ArrayList<View>> alls = new ArrayList();
    /**
     * 记录每一行的 view 集合
     */
    ArrayList<View> lines = new ArrayList();


    private void init() {
        datas = Utils.getDataas();

    }

    LayoutParams layoutParams;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.i("asss", "onMeasure()");
        alls.clear();
        lines.clear();
        int count = getChildCount();
        if (count == 0) {
            return;
        }

        int width = 0, height = 0;
        int pad_top = getPaddingTop();
        int pad_right = getPaddingRight();
        int pad_bottom = getPaddingBottom();
        int pad_left = getPaddingLeft();
        Log.i("layoutParams", "pad_top == " + pad_top + ")pad_right =" + pad_right);

        int selftWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);


        int modeWidht = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        for (int i = 0; i < count; i++) {
            layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            layoutParams.rightMargin = 20;
            layoutParams.leftMargin = 20;
            layoutParams.topMargin = 20;
            layoutParams.bottomMargin = 20;
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, pad_left + pad_right, layoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, pad_top + pad_bottom, layoutParams.height);

            getChildAt(i).measure(childWidthMeasureSpec, childHeightMeasureSpec);

            int childWidth = getChildAt(i).getMeasuredWidth();
            int childHeigth = getChildAt(i).getMeasuredWidth();


            Log.i("layoutParams", "width == " + layoutParams.width);


            width += childWidth + layoutParams.leftMargin + layoutParams.rightMargin;
            if (width > selftWidth - pad_left - pad_right) {
                width = 0;
                alls.add((ArrayList<View>) lines.clone());
                lines.clear();
            }
            lines.add(getChildAt(i));

        }
        alls.add((ArrayList<View>) lines.clone());
        if (alls.size() > 1) {
            width = selftWidth;
            height = alls.size() * (getChildAt(0).getMeasuredHeight()+ pad_bottom + pad_top + layoutParams.topMargin  + layoutParams.bottomMargin) ;
            Log.i("asss", "alls.size() == " + alls.size() + ")(getChildAt(0).getMeasuredHeight() == " + getChildAt(0).getMeasuredHeight());

        } else {
            width = getChildAt(0).getMeasuredHeight();
        }
        switch (modeWidht) {
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.EXACTLY:
                width = selftWidth;
                break;
        }
        switch (modeHeight) {
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            case MeasureSpec.EXACTLY:
                height = selfHeight;
                break;
        }
        Log.i("asss", "width == " + width + ")(height == " + height);

        setMeasuredDimension(width, height);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("asss", "onLayout()");

        if (alls.size() == 0) {
            return;
        }
        int left = getPaddingLeft();
        int top = getPaddingTop();
        for (int i = 0; i < alls.size(); i++) {
            int mtop = alls.get(0).get(0).getMeasuredHeight();
            left = getPaddingLeft();
            ArrayList<View> datas = alls.get(i);
            for (int j = 0; j < datas.size(); j++) {
                layoutParams = (LayoutParams) getChildAt(j).getLayoutParams();
                View view = datas.get(j);
                int mleft = view.getMeasuredWidth();

                if (i == 0 && j == 0) {
                    Log.i("ddddddd", "left == " + alls.get(0).get(0).getMeasuredWidth());
                }

                view.layout(left + layoutParams.leftMargin, top + layoutParams.topMargin, left + mleft + layoutParams.rightMargin, top + mtop + layoutParams.bottomMargin);

                left += mleft + layoutParams.leftMargin + layoutParams.rightMargin;

                Log.i("ddddddd", "left MMM == " + left);
                if (j == (datas.size() - 1)) {
                    top += mtop + layoutParams.topMargin + layoutParams.bottomMargin;
                }
            }


        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("asss", "onDraw()");

        super.onDraw(canvas);

    }
}
