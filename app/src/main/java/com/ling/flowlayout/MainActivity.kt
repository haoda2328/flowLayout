package com.ling.flowlayout

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    lateinit var  flowlayout3:RelativeLayout
    lateinit var strs: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tess = findViewById<TextView>(R.id.tv_test)
        flowlayout3 = findViewById<FlowLayout3>(R.id.flowlayout3)
    }


    fun dddddddd(view: View) {
        var tess = findViewById<TextView>(R.id.tv_test)
        tess.text = "ddddddddddddddddddd"
        addViewsss()

    }

    private fun addViewsss() {
        var datas:ArrayList<String> = Utils.getDataas()
        for (i in datas.indices) {
            //10sp/20px 大小计算
            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 20
            params.topMargin = 20
            params.leftMargin = 20
            params.rightMargin = 20
            params.width = datas.get(i).length * 20
            params.height = 20
            val view = TextView(this)
                        view.setTextColor(getResources().getColor(R.color.white));
//            view.setTextAppearance(getContext(), R.style.mystyle);
            view.layoutParams = params
            Log.i("asss","flowlayout3  = "+flowlayout3)
            flowlayout3?.addView(view)
        }
    }

}