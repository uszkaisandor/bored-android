package com.uszkaisandor.bored.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.uszkaisandor.bored.core.database.entity.LeisureActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeisureActivityDao {

    @Query("SELECT * FROM activities ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): LeisureActivityEntity?

    @Query("SELECT * FROM activities WHERE type = :type ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomByType(type: String): LeisureActivityEntity?

    @Query("SELECT * FROM activities WHERE id = :id")
    fun getById(id: String): Flow<LeisureActivityEntity?>

    @Query("SELECT COUNT(*) FROM activities")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: LeisureActivityEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<LeisureActivityEntity>)

    @Update
    suspend fun update(entity: LeisureActivityEntity)

    @Query("UPDATE activities SET isFavourite = :favourite WHERE id = :id")
    suspend fun updateFavourite(id: String, favourite: Boolean)

    @Query("SELECT * FROM activities WHERE isFavourite = 1")
    fun getFavoriteActivities(): PagingSource<Int, LeisureActivityEntity>

}
