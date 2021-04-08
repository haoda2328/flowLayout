package com.ling.flowlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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


    ArrayList<ArrayList<View>> alls = new ArrayList();
    ArrayList<View> lines = new ArrayList();

    LayoutParams ddddddddd = null;

    private void init() {
        datas = Utils.getDataas();


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("asss", "onMeasure()");
        alls.clear();
        lines.clear();
        int w = 0, h = 0;
        int WWW = 1280, HHH = 800;
        int tempW = 0;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int count = getChildCount();
        if (count==0) return;
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
            LayoutParams params = (LayoutParams) getChildAt(i).getLayoutParams();
            ddddddddd = params;
            tempW += getChildAt(i).getMeasuredWidth() + params.leftMargin + params.rightMargin;
            if (tempW > WWW) {
                tempW = 0;
                tempW += getChildAt(i).getMeasuredWidth() + params.leftMargin + params.rightMargin;
                alls.add((ArrayList<View>) lines.clone());
                lines.clear();
            }
            lines.add(getChildAt(i));

        }
        alls.add((ArrayList<View>) lines.clone());
        if (alls.size() > 1) {
            w = WWW;
            h = alls.size() * (getChildAt(0).getMeasuredHeight() + ddddddddd.topMargin + ddddddddd.bottomMargin);
        } else {
            w = tempW;
            h = getChildAt(0).getMeasuredHeight() + ddddddddd.topMargin + ddddddddd.bottomMargin;
        }
        Log.i("ddddddd3", "w == " + w + ")( h ==" + h + ")( " + alls.get(0).size());
        Log.i("ddddddd3", "w == " + w + ")( h ==" + h + ")( " + alls.size());
        Log.i("ddddddd3", "w == " + w + ")( h ==" + h + ")( " + alls.get(0).get(0).getMeasuredWidth());

        setMeasuredDimension(w, h);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("asss", "onLayout()");

        if (alls.size()==0) return;
        int left = 0;
        int top = 0;
        for (int i = 0; i < alls.size(); i++) {
            int mtop = alls.get(0).get(0).getMeasuredHeight();
            left = 0;
            ArrayList<View> datas = alls.get(i);
            for (int j = 0; j < datas.size(); j++) {
                View view = datas.get(j);
                int mleft = view.getMeasuredWidth();

                if (i == 0 && j == 0) {
                    Log.i("ddddddd", "left == " + alls.get(0).get(0).getMeasuredWidth());
                }

                view.layout(left + ddddddddd.leftMargin, top + ddddddddd.topMargin, left + mleft + ddddddddd.rightMargin, top + mtop + ddddddddd.bottomMargin);

                left += mleft + ddddddddd.leftMargin + ddddddddd.rightMargin;

                Log.i("ddddddd", "left MMM == " + left);
            }
            top += mtop + ddddddddd.topMargin + ddddddddd.bottomMargin;

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("asss", "onDraw()");

        super.onDraw(canvas);

    }
}
