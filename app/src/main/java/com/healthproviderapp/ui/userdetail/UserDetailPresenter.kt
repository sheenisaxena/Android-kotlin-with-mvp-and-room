package com.healthproviderapp.ui.userdetail

import android.content.Context
import com.healthproviderapp.base.LifecycleAwarePresenter
import com.healthproviderapp.roomdatabase.database.AppDB
import com.healthproviderapp.ui.dashboard.DashboardContract
import com.healthproviderapp.utils.SharedPref

class UserDetailPresenter(
        val mView: UserDetailContract.UserDetailView,
    val sharedPreference: SharedPref
) : LifecycleAwarePresenter<UserDetailContract.UserDetailView>(mView),
    UserDetailContract.UserDetailInterface {
    override fun getAllUserRecords(context: Context, userId : Long) {
        mView.getAllUserSuccess(AppDB.getDatabase(context).userRecordDao().loadAllUserRecordById(userId))
    }


}