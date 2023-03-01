package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.VERSION
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.convidados.constante.DataBaseConstants
import com.example.convidados.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase() : RoomDatabase() {

    abstract fun guestDao(): GuestDao
    companion object{
        private lateinit var INSTANCE: GuestDataBase
        fun getDataBase(context: Context): GuestDataBase{
            if (!Companion::INSTANCE.isInitialized){
                synchronized(GuestDataBase::class){
                    INSTANCE = Room.databaseBuilder(context,GuestDataBase::class.java,"guestdb")
                        .addMigrations()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
        private val MIGRTION_1_2:Migration =object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
             database.execSQL("DELETE FROM Guest")
            }
        }
    }
}