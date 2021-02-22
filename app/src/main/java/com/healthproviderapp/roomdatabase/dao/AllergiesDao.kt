package com.healthproviderapp.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.healthproviderapp.roomdatabase.entity.Allergies

@Dao
interface AllergiesDao {
    @Query("SELECT * FROM allergies")
    fun getAllAllergies(): List<Allergies>

    @Query("SELECT * FROM allergies WHERE id IN (:userIds)")
    fun loadAllAllergiesByIds(userIds: IntArray): List<Allergies>

    @Insert
    fun insertAll(vararg allergy: Allergies)

    @Delete
    fun delete(allergy: Allergies)
}