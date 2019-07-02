package com.example.databasex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import org.json.JSONObject
import java.lang.Integer.parseInt
import java.util.*
import kotlin.collections.ArrayList

class Adapter(var items: ArrayList<Interv>,var numaj:TextView, var nomaj:EditText, var typeaj:EditText) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row)  {
        var nom: TextView? = null
        var date: TextView? = null
        var num: TextView? = null
        var type: TextView? = null
        var item: LinearLayout? = null
        init {
            this.nom = row?.findViewById<TextView>(R.id.nom)
            this.date = row?.findViewById<TextView>(R.id.date)
            this.num = row?.findViewById(R.id.num)
            this.type = row?.findViewById<TextView>(R.id.type)
            this.item = row?.findViewById(R.id.item)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(p0?.context)
            .inflate(R.layout.interv, p0, false)

        return Adapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(p0: Adapter.ViewHolder, p1: Int) {
        var annonce = items[p1]
        p0?.nom?.text = annonce.nom
        p0?.date?.text = Date(annonce.date).toString()
        p0?.num?.text = annonce.num.toString()
        p0?.type?.text = annonce.interv

        p0?.item?.setOnClickListener{

            nomaj.setText(annonce.nom)
            typeaj.setText(annonce.interv)
            numaj.setText(annonce.num.toString())
        }
    }

    override fun getItemCount(): Int = items.size
}