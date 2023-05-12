package com.example.localdbroom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.localdbroom.database.AppDatabase
import com.example.localdbroom.databinding.ActivitySecondActivtyBinding
import com.example.localdbroom.model.Todo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivty : AppCompatActivity() {
    private lateinit var binding: ActivitySecondActivtyBinding
    private lateinit var appDB: AppDatabase
    var code = ""
    var id:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDB = AppDatabase.getDatabase(this)
        getIntentData()


        binding.saveTodo.setOnClickListener {
            val note = Todo(null, binding.titleET.text.toString(), binding.descET.text.toString())
            if (code =="y"){
                GlobalScope.launch {
                    appDB.totoDao().update(note.title.toString(),note.description.toString(),id)
                }
            }
            else{
                GlobalScope.launch {
                    appDB.totoDao().insert(note)
                }
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun getIntentData() {
        code = intent.getStringExtra("code").toString()
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("desc")
        id = intent.getIntExtra("note_id",0)
        if (code == "y") {
            binding.saveTodo.text = "Update Note"
            binding.titleET.setText(title.toString())
            binding.descET.setText(description.toString())
        }
    }
}