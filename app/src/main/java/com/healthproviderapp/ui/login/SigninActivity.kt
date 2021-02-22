package com.healthproviderapp.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blaxity.provider.DependenciesProvider
import com.google.gson.Gson
import com.healthproviderapp.R
import com.healthproviderapp.databinding.ActivityLoginBinding
import com.healthproviderapp.ui.dashboard.DashboardActivity
import com.healthproviderapp.ui.signup.SignupActivity
import com.healthproviderapp.utils.AppUtils
import com.healthproviderapp.utils.Const
import com.healthproviderapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

private var mBinding: ActivityLoginBinding? = null
private lateinit var mActivity: Activity
private lateinit var mContext: Context
private lateinit var presenter: SigninPresenter

class SigninActivity : AppCompatActivity(), SigninContract.SigninView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_login
        )


        instantiateDependencies()

        initUI()
    }

    private fun instantiateDependencies() {
        val provider = DependenciesProvider.getInstance(applicationContext)

        presenter = SigninPresenter(
                this)

    }

    private fun initUI() {
        mActivity = this
        mContext = this

        clickListener()
    }

    private fun clickListener() {
        login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun showingLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login -> {
                validateAndRedirect()
            }

            R.id.register -> {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateAndRedirect() {

        if (username.text.toString().trim().isEmpty()) {
            toast("Please enter your email")
            return
        }

        if (!AppUtils.isValidEmailAddress(username.text.toString().trim())) {
            toast("Please enter a valid email address")
            return
        }

        if (password.text.toString().trim().isEmpty()) {
            toast("Please enter your password")
            return
        }

        if (password.text.toString().trim().length < 6) {
            toast("Please enter a minimum 6 characters")
            return
        }

        val isEmailExist = presenter.checkIsEmailExist(this, username.text.toString().trim())
        if (isEmailExist != 0) {
            // success
            val userObj = presenter.getUserByEmailId(this, username.text.toString().trim())
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra(Const.USER_OBJ, Gson().toJson(userObj))
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finishAffinity()
        } else {
            // failure
            loading.visibility = View.GONE
            Toast.makeText(this, getString(R.string.label_user_not_exist), Toast.LENGTH_SHORT).show()
        }

    }
}