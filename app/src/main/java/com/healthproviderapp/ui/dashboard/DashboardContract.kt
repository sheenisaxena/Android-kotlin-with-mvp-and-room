package com.healthproviderapp.ui.dashboard

import android.content.Context
import com.healthproviderapp.base.BasePresenter
import com.healthproviderapp.base.BaseView
import com.healthproviderapp.roomdatabase.entity.User


class DashboardContract {

    interface DashboardInterface : BasePresenter {
        fun getAllUser(context: Context)
    }

    interface DashboardView : BaseView<DashboardInterface> {
        fun getAllUserSuccess(userList : List<User>)
    }
}