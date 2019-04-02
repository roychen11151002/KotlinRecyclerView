package com.example.kotlinrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

const val KotlinLog = "kotlinTest"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemName = arrayListOf<ItemName>(ItemName(R.drawable.apple, "Apple"), ItemName(R.drawable.pineapple, "Pineapple"),
            ItemName(R.drawable.avocado, "Avocado"), ItemName(R.drawable.banana, "Banana"), ItemName(R.drawable.cherries, "Cherries"),
            ItemName(R.drawable.coconut, "Coconut"), ItemName(R.drawable.grapes, "Grapes"), ItemName(R.drawable.lemon, "Lemon"),
            ItemName(R.drawable.orange, "Orange"), ItemName(R.drawable.peach, "Peach"), ItemName(R.drawable.strawberry, "strawberry"),
            ItemName(R.drawable.tomato, "Tomato"))

        val linearLaoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(itemName)

        linearLaoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLaoutManager
        recyclerView.adapter = adapter
/*
        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.orientation = GridLayoutManager.VERTICAL
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = MyAdapter(itemName)
*/
    }
}

class ItemName(val photo: Int, val name: String)

class MyAdapter(private val data: ArrayList<ItemName>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    lateinit var clickListener: AdapterView.OnItemClickListener
    lateinit var clickLongListener: AdapterView.OnItemLongClickListener
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val itemName: TextView = v.findViewById(R.id.txvName)
        val itemPhoto: ImageView = v.findViewById(R.id.imgPhoto)
        init {
            v.setOnClickListener(View.OnClickListener {
                Log.d(KotlinLog, "recyclerView click")
            })
        }
    }

    interface OnItemClickListener {
        fun onItemClick(p0: View, p1: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(po: View, p1: Int): Boolean
    }

    fun setOnItemClickListener(p0: AdapterView.OnItemClickListener) {
        this.clickListener = p0
    }

    fun setOnItemLongClickListener(p0: AdapterView.OnItemLongClickListener): Boolean {
        this.clickLongListener = p0
        return true
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.adapter_layout, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.itemName.text = data[p1].name
        p0.itemPhoto.setImageResource(data[p1].photo)
    }
}
