package com.uszkaisandor.bored.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uszkaisandor.bored.persistence.entity.LeisureActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeisureActivityDao {

    @Query("SELECT * FROM activities WHERE id=:id")
    fun get(id: String): Flow<LeisureActivityEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LeisureActivityEntity)

    @Update
    suspend fun update(entity: LeisureActivityEntity)

    @Query("UPDATE activities SET isFavourite = :favourite WHERE id = :id")
    suspend fun updateFavourite(id: String, favourite: Boolean)

}
