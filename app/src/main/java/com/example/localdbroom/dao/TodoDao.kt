package com.example.localdbroom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.localdbroom.model.Todo

@Dao
interface TodoDao {


    @Query("SELECT * FROM todo_table order by id DESC")
    fun getAllTodos(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(todo: Todo)


    @Query("SELECT * FROM todo_table WHERE title LIKE:mytitle")
    fun getAllTodosByTitle(mytitle: String): List<Todo>

    @Query("UPDATE todo_table Set title = :mytitle,description = :mydescription WHERE id = :myid")
    fun update(mytitle: String, mydescription: String, myid: Int)


}