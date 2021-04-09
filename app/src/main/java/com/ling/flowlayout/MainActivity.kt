package com.ling.flowlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    lateinit var flowlayout3: RelativeLayout
    lateinit var strs: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addStrView()
    }

    private fun addStrView() {
        var flowlayout = findViewById<MyFlowLayout>(R.id.flow_layout)
        var datas: ArrayList<String> = Util.getDatas()
        datas.forEach { str ->
            var view = LayoutInflater.from(this).inflate(R.layout.text_view_layout, null)
            var tv_str = view.findViewById<TextView>(R.id.tv_str)
            tv_str.text = str
            flowlayout.addView(view)
        }
    }

}