package com.healthproviderapp.ui.signup

import android.content.Context
import com.healthproviderapp.base.BasePresenter
import com.healthproviderapp.base.BaseView
import com.healthproviderapp.roomdatabase.entity.User


class SignupContract {

    interface SignupInterface : BasePresenter {
        fun insertUserDataInDB(context: Context, name: String, age: String, address: String, gender: String, occupation: String, fostername: String, email: String, password: String)
        fun loadAllUserByIds(context: Context, userId : Long)
    }

    interface SignupView : BaseView<SignupInterface> {
        fun dataInsertedSuccess(rowId: Long?)
        fun dataFetchSuccess(user : User)
    }
}