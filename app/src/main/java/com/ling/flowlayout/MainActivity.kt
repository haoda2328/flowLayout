package com.ling.flowlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    lateinit var flowlayout3: RelativeLayout
    lateinit var strs: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flowlayout3 = findViewById<FlowLayout3>(R.id.flowlayout3)
        addViewsss()
    }


//    fun dddddddd(view: View) {
//        var tess = findViewById<TextView>(R.id.tv_test)
////        tess.text = "ddddddddddddddddddd"
//
//
//    }

    private fun addViewsss() {
        var datas: ArrayList<String> = Utils.getDataas()
        datas.forEach { str ->
            var view = LayoutInflater.from(this).inflate(R.layout.text_view_layout, null)
            view as TextView
            view.text = str
            flowlayout3.addView(view)

        }
    }

}