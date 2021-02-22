package com.healthproviderapp.ui.userdetail

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blaxity.provider.DependenciesProvider
import com.google.gson.Gson
import com.healthproviderapp.R
import com.healthproviderapp.databinding.ActivityUserdetailBinding
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.roomdatabase.entity.UserHealthRecords
import com.healthproviderapp.utils.Const

private var mBinding: ActivityUserdetailBinding? = null
private lateinit var mActivity: Activity
private lateinit var mContext: Context
private var mDataList: MutableList<UserHealthRecords> = mutableListOf()
private var mAdapter: PatientsDetailListAdapter? = null
private lateinit var presenter: UserDetailPresenter
private lateinit var layoutManager: LinearLayoutManager
private lateinit var userObj: User

class UserDetailActivity : AppCompatActivity(), UserDetailContract.UserDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_userdetail
        )

        instantiateDependencies()

        initUI()

    }

    private fun instantiateDependencies() {
        val provider = DependenciesProvider.getInstance(applicationContext)

        presenter = UserDetailPresenter(
                this,
                provider.getSharedPreference())
    }

    private fun displayDataInAdapter() {

        mAdapter = PatientsDetailListAdapter(mContext, mDataList, object :
                PatientsDetailListAdapter.OnItemClickListener {
            override fun onItemClick(pos: Int) {

            }

        })
        mBinding!!.recyclerViewPatientList.adapter = mAdapter

        mBinding!!.recyclerViewPatientList.visibility = View.VISIBLE
        mBinding!!.linearPatientNoDataFound.visibility = View.GONE
    }

    private fun initUI() {
        mActivity = this
        mContext = this

        layoutManager = LinearLayoutManager(mContext)
        mBinding!!.recyclerViewPatientList.layoutManager = layoutManager

        val intent = intent
        userObj = Gson().fromJson(intent.getStringExtra(Const.USER_OBJ), User::class.java)

        presenter.getAllUserRecords(mContext, userObj.uid)

    }

    override fun getAllUserSuccess(userList: List<UserHealthRecords>) {
        mDataList = userList as MutableList<UserHealthRecords>
        displayDataInAdapter()

    }

    override fun showingLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

}