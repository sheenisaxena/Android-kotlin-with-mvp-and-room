package com.healthproviderapp.roomdatabase.database

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory
import com.healthproviderapp.roomdatabase.dao.*
import com.healthproviderapp.roomdatabase.entity.*
import java.io.*

@Database(entities = [Allergies::class, Diags::class, Prescription::class, User::class, UserHealthRecords::class], version = 3)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userRecordDao(): UserHealthRecordDao
    abstract fun diagsDao(): DiagsDao
    abstract fun allergyDao(): AllergiesDao
    abstract fun prescribeDao(): PrescriptionDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDB? = null
        private const val DATABASE_NAME = "external"

        /*@JvmField
        val MIGRATION_1_2 : Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }*/

        fun getDatabase(context: Context): AppDB {

//            copyAttachedDatabase(context, DATABASE_NAME)

            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        "external"
                ).createFromAsset("databases/$DATABASE_NAME.db")
                        .allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
                //.openHelperFactory( AssetSQLiteOpenHelperFactory())
                //.addMigrations(AppDB.MIGRATION_1_2)
            }
        }

        private fun copyAttachedDatabase(context: Context, databaseName: String) {
            val dbPath: File = context.getDatabasePath(databaseName)

            // If the database already exists, return
            if (dbPath.exists()) {
                return
            }

            // Make sure we have a path to the file
            dbPath.parentFile.mkdirs()

            // Try to copy database file
            try {
                val inputStream: InputStream = context.assets.open("databases/$databaseName")
                val output: OutputStream = FileOutputStream(dbPath)
                val buffer = ByteArray(8192)
                var length: Int
                while (inputStream.read(buffer, 0, 8192).also { length = it } > 0) {
                    output.write(buffer, 0, length)
                }
                output.flush()
                output.close()
                inputStream.close()
            } catch (e: IOException) {
                Log.d(TAG, "Failed to open file", e)
                e.printStackTrace()
            }
        }
    }
}