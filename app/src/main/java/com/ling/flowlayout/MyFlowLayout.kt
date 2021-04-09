package com.ling.flowlayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout

/**
 *
 * @author  hao2.ling
 * @date 2021/4/9
 */
class MyFlowLayout(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    /**
     * 记录所有行的 view 集合
     */
    private var alls: ArrayList<ArrayList<View>> = ArrayList()

    /**
     * 临时记录每一行的 view 集合
     */
    private var lines: ArrayList<View?> = ArrayList()

    /**
     * 子类布局的 LayoutParams
     */
    private var params: RelativeLayout.LayoutParams? = null

    /**
     * 测量布局大小
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //重新计算时数据 归零
        alls.clear()
        lines.clear()
        //子类 View
        val count = childCount
        if (count == 0) {
            return
        }
        //需要计算出来的宽、高值
        var width = 0
        var height = 0

        //根据父类计算出 当前flowlayout 的 大小值（参考作用）
        val selftWidth = MeasureSpec.getSize(widthMeasureSpec)
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec)

        //根据父类计算出  MeasureSpec  mode
        val modeWidht = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        for (i in 0 until count) {
            params = getChildAt(i).layoutParams as LayoutParams?
            val childWidthMeasureSpec = params?.width?.let {
                getChildMeasureSpec(
                    widthMeasureSpec
                    , paddingLeft + paddingRight, it
                )
            }
            val childHeightMeasureSpec = params?.height?.let {
                getChildMeasureSpec(
                    heightMeasureSpec
                    , paddingTop + paddingBottom, it
                )
            }
            //子类测量
            childWidthMeasureSpec?.let {
                childHeightMeasureSpec?.let { it1 ->
                    getChildAt(i).measure(
                        it,
                        it1
                    )
                }
            }
            val childWidth = getChildAt(i).measuredWidth
            val childHeigth = getChildAt(i).measuredWidth

            width += childWidth
            if (width > selftWidth - paddingLeft - paddingRight) {
                width = 0
                alls.add(lines.clone() as java.util.ArrayList<View>)
                lines.clear()
            }
            lines.add(getChildAt(i))
        }
        alls.add(lines.clone() as java.util.ArrayList<View>)
        if (alls.size > 1) {
            width = selftWidth
            height =
                alls.size * (getChildAt(0).measuredHeight + paddingBottom + paddingTop)
        } else {
            width = getChildAt(0).measuredHeight
        }

        width = when (modeWidht) {
            MeasureSpec.AT_MOST -> {
                width
            }
            MeasureSpec.UNSPECIFIED -> {
                width
            }
            MeasureSpec.EXACTLY -> {
                selftWidth
            }
            else -> {
                width
            }
        }
        when (modeHeight) {
            MeasureSpec.AT_MOST -> {
            }
            MeasureSpec.UNSPECIFIED -> {
            }
            MeasureSpec.EXACTLY -> height = selfHeight
        }

        Log.i("layoutParams", "width == $width height == $height")
        setMeasuredDimension(width, height)

    }

    /**
     * 显示位置计算
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (alls.size == 0) {
            return
        }
        var top = paddingTop
        for (i in alls.indices) {
            val mtop = alls[0][0].measuredHeight
           var left = paddingLeft
            val datas = alls[i]
            for (j in datas.indices) {
                val view = datas[j]
                val mleft = view.measuredWidth
                view.layout(left, top, left + mleft, top + mtop)
                Log.i("layoutParams", "left == $left ")

                //宽度 累加计算
                left += mleft
                //换行 时高度 累加计算
                if (j == datas.size - 1) {
                    top += mtop
                }
            }
        }
    }

}