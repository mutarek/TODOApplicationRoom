package com.example.localdbroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.localdbroom.database.AppDatabase
import com.example.localdbroom.databinding.ActivityMainBinding
import com.example.localdbroom.model.Todo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appDB: AppDatabase
    private lateinit var dataList: ArrayList<Todo>
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDB = AppDatabase.getDatabase(this)
        getAllData()

        binding.fab.setOnClickListener {
            val intent = Intent(this, SecondActivty::class.java)
            intent.putExtra("code", "n")
            startActivity(intent)
        }
    }

    private fun getAllData() {
        dataList = arrayListOf()
        GlobalScope.launch {
            appDB.totoDao().getAllTodos().forEach { singleItem ->
                dataList.add(singleItem)
            }
            if(dataList.isEmpty())
            {
                Toast.makeText(this@MainActivity,"No Data Available",Toast.LENGTH_SHORT).show()
            }
            else{
                initRecyllerView()
            }

        }
    }

    private fun initRecyllerView() {
        binding.recyllerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(dataList)
        binding.recyllerView.adapter = adapter
        adapter.setOnClickListener(object : CustomAdapter.OnClickListener {
            override fun onClick(position: Int, todo: Todo) {
                val intent = Intent(this@MainActivity, SecondActivty::class.java)
                intent.putExtra("code", "y")
                intent.putExtra("note_id",todo.id)
                intent.putExtra("title", todo.title)
                intent.putExtra("desc", todo.description)
                startActivity(intent)
            }
        })
    }
}