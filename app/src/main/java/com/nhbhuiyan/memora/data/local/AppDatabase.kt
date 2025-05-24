package com.nhbhuiyan.memora.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalNote::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun noteDao(): NoteDao
    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabse(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE= instance
                instance
            }
        }
    }
}