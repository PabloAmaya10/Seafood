package com.example.seafood.data.database.dao

import androidx.room.*

/**
 * Base dtaabse operations
 */
@Dao
interface BaseDao<in T> {

    /**
     * Insert an Entity list into the database
     * @return Last id inserted
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entity: List<T>)

    /**
     * Insert an Entity into the database
     * @return Last id inserted
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: T): Long

    /**
     * Update a Entity into database
     * @return Last id updated
     */
    @Update
    suspend fun update(entity: T): Int

    /**
     * Insert or update a Entity into database
     * @return Last id inserted or updated
     */
    @Transaction
    suspend fun insertOrUpdate(entity: T): Long {

        val id = insert(entity)
        return if (id == -1L) {
            update(entity).toLong()
        } else {
            id
        }
    }
}