package com.healthproviderapp.ui.login

import android.content.Context
import com.healthproviderapp.base.LifecycleAwarePresenter
import com.healthproviderapp.roomdatabase.database.AppDB
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.utils.SharedPref
import kotlinx.android.synthetic.main.activity_login.*

class SigninPresenter(
        val mView: SigninContract.SigninView
) : LifecycleAwarePresenter<SigninContract.SigninView>(mView),
    SigninContract.SigninInterface {
    override fun checkIsEmailExist(context: Context, username: String): Int {
        return AppDB.getDatabase(context).userDao().isEmailExist(username)
    }

    override fun getUserByEmailId(context: Context, username: String): User {
        return AppDB.getDatabase(context).userDao().getUserByEmailId(username)
    }


}