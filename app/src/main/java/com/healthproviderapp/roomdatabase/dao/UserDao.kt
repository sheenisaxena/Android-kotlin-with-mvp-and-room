package com.healthproviderapp.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.healthproviderapp.roomdatabase.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllUserByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadOneUserById(userIds: Long): User

    @Query("SELECT * FROM user WHERE email IN (:emailId)")
    fun getUserByEmailId(emailId: String?): User

    @Query("SELECT * FROM user WHERE email = :emailId")
    fun isEmailExist(emailId: String?): Int

    @Insert
    fun insertAll(user: User) : Long

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user")
    fun emptyTable()

}