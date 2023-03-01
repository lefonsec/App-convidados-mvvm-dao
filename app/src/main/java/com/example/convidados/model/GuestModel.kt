package com.example.convidados.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
data class GuestModel(
     @PrimaryKey(autoGenerate = true)
     @ColumnInfo(name = "id")
     val id: Int,

     @ColumnInfo(name = "name")
     var name: String,

     @ColumnInfo(name = "presence")
     var precense: Boolean
)
