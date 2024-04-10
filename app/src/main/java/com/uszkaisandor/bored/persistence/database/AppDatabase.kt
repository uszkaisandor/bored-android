package com.uszkaisandor.bored.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uszkaisandor.bored.persistence.dao.LeisureActivityDao
import com.uszkaisandor.bored.persistence.entity.LeisureActivityEntity

@Database(
    entities = [LeisureActivityEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(com.uszkaisandor.bored.persistence.database.TypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun leisureActivityDao(): LeisureActivityDao
}