package com.healthproviderapp.ui.dashboard

import android.content.Context
import com.healthproviderapp.base.LifecycleAwarePresenter
import com.healthproviderapp.roomdatabase.database.AppDB
import com.healthproviderapp.ui.dashboard.DashboardContract
import com.healthproviderapp.utils.SharedPref

class DashboardPresenter(
        val mView: DashboardContract.DashboardView,
    val sharedPreference: SharedPref
) : LifecycleAwarePresenter<DashboardContract.DashboardView>(mView),
    DashboardContract.DashboardInterface {
    override fun getAllUser(context: Context) {
        mView.getAllUserSuccess(AppDB.getDatabase(context).userDao().getAllUser())
    }


}