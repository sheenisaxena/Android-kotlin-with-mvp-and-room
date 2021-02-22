package com.healthproviderapp.ui.userdetail

import android.content.Context
import com.healthproviderapp.base.BasePresenter
import com.healthproviderapp.base.BaseView
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.roomdatabase.entity.UserHealthRecords


class UserDetailContract {

    interface UserDetailInterface : BasePresenter {
        fun getAllUserRecords(context: Context, userId : Long)
    }

    interface UserDetailView : BaseView<UserDetailInterface> {
        fun getAllUserSuccess(userList : List<UserHealthRecords>)
    }
}