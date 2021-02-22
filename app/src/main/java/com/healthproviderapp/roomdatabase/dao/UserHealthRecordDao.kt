package com.healthproviderapp.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.healthproviderapp.roomdatabase.entity.UserHealthRecords

@Dao
interface UserHealthRecordDao {
    @Query("SELECT * FROM user_health_records")
    fun getAllUserRecord(): List<UserHealthRecords>

    @Query("SELECT * FROM user_health_records WHERE userId IN (:userIds)")
    fun loadAllUserRecordByIds(userIds: IntArray): List<UserHealthRecords>

    @Query("SELECT * FROM user_health_records WHERE userId IN (:userIds)")
    fun loadAllUserRecordById(userIds: Long): List<UserHealthRecords>

    @Insert
    fun insertAll(vararg userRecord: UserHealthRecords)

    @Delete
    fun delete(userRecord: UserHealthRecords)
}