package com.healthproviderapp.ui.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blaxity.provider.DependenciesProvider
import com.google.gson.Gson
import com.healthproviderapp.R
import com.healthproviderapp.databinding.ActivitySignupBinding
import com.healthproviderapp.roomdatabase.database.AppDB
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.ui.visitingreason.VisitingReasonActivity
import com.healthproviderapp.utils.AppUtils
import com.healthproviderapp.utils.Const
import com.healthproviderapp.utils.toast
import kotlinx.android.synthetic.main.activity_signup.*

private var mBinding: ActivitySignupBinding? = null
private lateinit var mActivity: Activity
private lateinit var mContext: Context
private lateinit var presenter: SignupPresenter
private var genderVal :String = "Male"

class SignupActivity : AppCompatActivity(), SignupContract.SignupView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_signup
        )


        instantiateDependencies()

        initUI()
    }

    private fun instantiateDependencies() {
        val provider = DependenciesProvider.getInstance(applicationContext)

        presenter = SignupPresenter(
            this,
            provider.getSharedPreference())

    }

    private fun initUI() {
        mActivity = this
        mContext = this

            rbMale.setOnCheckedChangeListener { buttonView, isChecked ->
                genderVal = "Male"
            }
            rbFemale.setOnCheckedChangeListener { buttonView, isChecked ->
                genderVal = "Female"
            }
        clickListener()
    }

    private fun clickListener() {
        btnSignup.setOnClickListener(this)
    }

    override fun dataInsertedSuccess(rowId: Long?) {
        presenter.loadAllUserByIds(mContext, rowId!!)
    }

    override fun dataFetchSuccess(user: User) {
        val intent = Intent(mActivity, VisitingReasonActivity::class.java)
        intent.putExtra(Const.USER_OBJ, Gson().toJson(user))
        startActivity(intent)
        finish()
    }

    override fun showingLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSignup -> {
                validateAndRedirect()
            }
        }
    }

    private fun validateAndRedirect() {

        if (edUserName.text.toString().trim().isEmpty()) {
            toast("Please enter username")
            return
        }

        /*if (edUserName.text.toString().trim().contains(" ")) {
            toast("Space is not allowed in username")
            return
        }*/

        if (edAge.text.toString().trim().isEmpty()) {
            toast("Please enter age")
            return
        }

        if (edOccupation.text.toString().trim().isEmpty()) {
            toast("Please enter occupation")
            return
        }

        if (edFatherName.text.toString().trim().isEmpty()) {
            toast("Please enter fathername")
            return
        }

        if (edEmail.text.toString().trim().isEmpty()) {
            toast("Please enter your email")
            return
        }

        if (!AppUtils.isValidEmailAddress(edEmail.text.toString().trim())) {
            toast("Please enter a valid email address")
            return
        }

        if (edPassword.text.toString().trim().isEmpty()) {
            toast("Please enter your password")
            return
        }

        if (edPassword.text.toString().trim().length < 6) {
            toast("Please enter a minimum 6 characters")
            return
        }

//        AppDB.getDatabase(mContext).userDao().emptyTable() // added by sheeni to remove later
        presenter.insertUserDataInDB(this, edUserName.text.toString().trim(), edAge.text.toString(), edAddress.text.toString().trim(), genderVal, edOccupation.text.toString().trim(), edFatherName.text.toString().trim(), edEmail.text.toString().trim(), edPassword.text.toString().trim())
    }
}