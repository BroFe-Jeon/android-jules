package com.example.core.data.local.db // Updated package

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false) // UserEntity will be in the same package
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao // UserDao will be in the same package

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_core_database" // Changed DB name slightly to reflect its new location
                )
                // Add migrations here if needed in the future
                // .addMigrations(MIGRATION_1_2)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
