package com.healthproviderapp.roomdatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class Prescription(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "desc") val description: String?
)