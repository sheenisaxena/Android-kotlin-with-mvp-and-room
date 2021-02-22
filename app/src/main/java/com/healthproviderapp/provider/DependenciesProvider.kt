package com.blaxity.provider

import android.app.Application
import android.content.Context
import com.healthproviderapp.utils.SharedPref


@SuppressWarnings("All")
class DependenciesProvider(context: Context) {

    private var mContext: Context? = context

    private var sharePref: SharedPref? = null

    companion object {
        private var instance: DependenciesProvider? = null

        fun getInstance(context: Context): DependenciesProvider {
            if (context !is Application) {
                throw IllegalStateException("Context for a provider should be the application context")
            }

            if (instance == null) {
                instance = DependenciesProvider(context)
            }

            return instance!!
        }
    }

   /* fun getNetworkStatusService(): NetworkStatusService {
        return NetworkStatusService.getInstance(mContext!!)
    }*/

    /*fun getApiClientInstance(): ApiClient {
        return ApiClient.getInstance()
    }*/

    fun getSharedPreference(): SharedPref {
        if (sharePref == null) {
            sharePref = SharedPref.getInstance(mContext)
        }

        return sharePref!!
    }

}