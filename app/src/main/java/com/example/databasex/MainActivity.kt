package com.example.databasex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.lang.Integer.parseInt
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var adapter:Adapter
    var listdata= ArrayList<Interv>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "interv-list.db"
        ).build()


        setUpRecyclerView()

        doAsync {
            var data = db.IntervDao().getAll()
            data?.forEach {
                listdata.add(it)
            }
            adapter.notifyItemInserted(listdata.size-1)
        }

        ajouter.setOnClickListener {
            add(db)
        }
        suprimer.setOnClickListener {
            supp(db)
        }
        modifier.setOnClickListener {
            update(db)
        }

    }

    private fun add(db: AppDatabase) {
        val intrv = Interv(listdata.size, nom_aj.text.toString(),type_aj.text.toString(),0 )
        listdata.add(intrv)
        adapter.notifyItemInserted(listdata.size)
        doAsync { db.IntervDao().insertAll(intrv) }
    }
    private fun supp(db: AppDatabase){
        val pos:Int = parseInt(textView.text.toString())
        lateinit var intrv:Interv
        var k:Int=0

        for(i in listdata){
            if (i.num == pos){
                intrv = i
                break
            }
            k = k+1
        }
        adapter.notifyItemRemoved(k)
        doAsync { db.IntervDao().delete(intrv) }
    }
    private fun update(db: AppDatabase){
        val pos:Int = parseInt(textView.text.toString())
        lateinit var intrv:Interv
        val intrvn = Interv(pos, nom_aj.text.toString(),type_aj.text.toString(),0 )
        var k:Int=0

        for(i in listdata){
            if (i.num == pos){
                intrv = i
                break
            }
            k = k+1
        }
        adapter.notifyItemRemoved(k)
        listdata.add(intrvn)
        adapter.notifyItemInserted(pos)
        doAsync {
            db.IntervDao().delete(intrv)
            db.IntervDao().insertAll(intrvn)
        }
    }

    private fun setUpRecyclerView() {
        adapter = Adapter(listdata,textView,nom_aj,type_aj)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)


        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(adapter)
    }
}