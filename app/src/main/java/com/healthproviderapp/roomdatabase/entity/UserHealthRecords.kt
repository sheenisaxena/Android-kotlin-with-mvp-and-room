package com.healthproviderapp.roomdatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "user_health_records",
    foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = arrayOf("uid"),
    childColumns = arrayOf("userId"),
    onDelete = ForeignKey.CASCADE
)]
)
data class UserHealthRecords(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "diagnosis") val diagnosis: String?,
    @ColumnInfo(name = "symptoms") val symptoms: String?,
    @ColumnInfo(name = "prescription") val prescription: String?,
    @ColumnInfo(name = "reason") val visitingreason : String?,
    @ColumnInfo(name = "userId") val userId : Long?


)