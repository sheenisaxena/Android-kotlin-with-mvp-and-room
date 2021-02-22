package com.healthproviderapp.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.healthproviderapp.roomdatabase.entity.Diags

@Dao
interface DiagsDao {
    @Query("SELECT * FROM diags")
    fun getAllDiags(): List<Diags>

    @Query("SELECT * FROM diags WHERE id IN (:userIds)")
    fun loadAllDiagsByIds(userIds: IntArray): List<Diags>

    @Insert
    fun insertAll(vararg diag: Diags)

    @Delete
    fun delete(diag: Diags)
}