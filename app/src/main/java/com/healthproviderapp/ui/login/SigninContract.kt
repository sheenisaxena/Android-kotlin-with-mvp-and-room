package com.healthproviderapp.ui.login

import android.content.Context
import com.healthproviderapp.base.BasePresenter
import com.healthproviderapp.base.BaseView
import com.healthproviderapp.roomdatabase.entity.User


class SigninContract {

    interface SigninInterface : BasePresenter {
        fun checkIsEmailExist(context: Context, username : String) : Int
        fun getUserByEmailId(context: Context, username: String) : User
    }

    interface SigninView : BaseView<SigninInterface> {
    }
}