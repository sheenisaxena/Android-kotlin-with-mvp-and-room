package com.healthproviderapp.ui.signup

import android.content.Context
import com.healthproviderapp.base.LifecycleAwarePresenter
import com.healthproviderapp.roomdatabase.database.AppDB
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.utils.SharedPref

class SignupPresenter(
        val mView: SignupContract.SignupView,
        val sharedPreference: SharedPref,
        var rowId : Long ?= 0
) : LifecycleAwarePresenter<SignupContract.SignupView>(mView),
    SignupContract.SignupInterface {
    override fun insertUserDataInDB(
            context: Context,
            name: String,
            age: String,
            address: String,
            gender: String,
            occupation: String,
            fostername: String,
            email: String,
            password: String
    ) {

        rowId = AppDB.getDatabase(context).userDao().insertAll(User(0, name,age.toInt(), address, gender, occupation, fostername, email, password))
        mView.dataInsertedSuccess(rowId)

    }

    override fun loadAllUserByIds(context: Context, userId: Long) {
        val userObj = AppDB.getDatabase(context).userDao().loadOneUserById(userId)
        mView.dataFetchSuccess(userObj)
    }


}