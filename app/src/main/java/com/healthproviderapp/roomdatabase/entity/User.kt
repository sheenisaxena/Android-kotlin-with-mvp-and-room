package com.healthproviderapp.roomdatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(
    value = ["email"],
    unique = true
)]
)
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "age") var age: Int?,
    @ColumnInfo(name = "address") var address: String?,
    @ColumnInfo(name = "gender") var gender: String?,
    @ColumnInfo(name = "occupation") var occupation: String?,
    @ColumnInfo(name = "fostername") var fostername: String?, // Father/husband name
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "password") var password: String?
)