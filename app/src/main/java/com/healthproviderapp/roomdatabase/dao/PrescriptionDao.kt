package com.healthproviderapp.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.healthproviderapp.roomdatabase.entity.Prescription

@Dao
interface PrescriptionDao {
    @Query("SELECT * FROM drugs")
    fun getAllPresription(): List<Prescription>

    @Query("SELECT * FROM drugs WHERE id IN (:userIds)")
    fun loadAllPrescriptionByIds(userIds: IntArray): List<Prescription>

    @Insert
    fun insertAll(vararg prescribe: Prescription)

    @Delete
    fun delete(prescribe: Prescription)
}