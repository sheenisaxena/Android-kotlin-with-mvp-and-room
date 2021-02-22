package com.healthproviderapp.base

import android.app.Application
import com.healthproviderapp.roomdatabase.database.AppDB

class HealthProviderApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDB.getDatabase(this) }
//    val repository by lazy { WordRepository(database.wordDao()) }
}