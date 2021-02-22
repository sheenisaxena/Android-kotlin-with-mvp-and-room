package com.healthproviderapp.ui.visitingreason

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
import com.healthproviderapp.databinding.ActivityVisitingreasonBinding
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.ui.diagnosis.DescDiagnosisActivity
import com.healthproviderapp.utils.Const
import com.healthproviderapp.utils.toast
import kotlinx.android.synthetic.main.activity_visitingreason.*


private var mBinding: ActivityVisitingreasonBinding? = null
private lateinit var mActivity: Activity
private lateinit var mContext: Context
private lateinit var presenter: VisitingReasonPresenter
private lateinit var userObj : User

class VisitingReasonActivity : AppCompatActivity(), VisitingReasonContract.VisitingReasonView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_visitingreason
        )

        instantiateDependencies()

        initUI()
    }

    private fun instantiateDependencies() {
        val provider = DependenciesProvider.getInstance(applicationContext)

        presenter = VisitingReasonPresenter(
                this,
                provider.getSharedPreference())

    }

    private fun initUI() {
        mActivity = this
        mContext = this

        val intent = intent
        userObj = Gson().fromJson(
            intent.getStringExtra(Const.USER_OBJ),User::class.java)

        clickListener()
    }

    private fun clickListener() {
        btnNext.setOnClickListener(this)
    }

    override fun showingLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnNext -> {
                validateAndRedirect()
            }
        }
    }

    private fun validateAndRedirect() {

        if (edVisitingReason.text.toString().trim().isEmpty()) {
            toast("Please enter reason")
            return
        }

        val intent = Intent(mActivity, DescDiagnosisActivity::class.java)
        intent.putExtra(Const.VISITING_REASON, edVisitingReason.text.toString().trim())
        intent.putExtra(Const.USER_OBJ, Gson().toJson(userObj))
        startActivity(intent)
        toast(R.string.label_please_wait)
        finish()
    }
}