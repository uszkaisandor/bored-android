package com.uszkaisandor.bored.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uszkaisandor.bored.core.database.dao.LeisureActivityDao
import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity

@Database(
    entities = [LeisureActivityEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun leisureActivityDao(): LeisureActivityDao
}
