package com.healthproviderapp.ui.dashboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.blaxity.provider.DependenciesProvider
import com.google.gson.Gson
import com.healthproviderapp.R
import com.healthproviderapp.databinding.ActivityDashboardBinding
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.ui.userdetail.UserDetailActivity
import com.healthproviderapp.ui.visitingreason.VisitingReasonActivity
import com.healthproviderapp.utils.Const
import kotlinx.android.synthetic.main.activity_dashboard.*

private var mBinding: ActivityDashboardBinding? = null
private lateinit var mActivity: Activity
private lateinit var mContext: Context
private var mDataList: MutableList<User> = mutableListOf()
private var mAdapter: PatientsListAdapter? = null
private lateinit var userObj: User
private lateinit var presenter: DashboardPresenter
private lateinit var layoutManager: LinearLayoutManager


class DashboardActivity : AppCompatActivity(), DashboardContract.DashboardView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_dashboard
        )

        instantiateDependencies()

        initUI()

    }

    private fun instantiateDependencies() {
        val provider = DependenciesProvider.getInstance(applicationContext)

        presenter = DashboardPresenter(
                this,
                provider.getSharedPreference())
    }

    private fun displayDataInAdapter() {

        if (mAdapter == null) {
            mAdapter = PatientsListAdapter(mContext, mDataList, object :
                    PatientsListAdapter.OnItemClickListener {
                override fun onItemClick(pos: Int) {
                    val intent = Intent(mActivity, UserDetailActivity::class.java)
                    intent.putExtra(Const.USER_OBJ, Gson().toJson(mDataList[pos]))
                    startActivity(intent)
                }

            })
            mBinding!!.recyclerViewPatientList.adapter = mAdapter
        } else {
            mAdapter!!.notifyDataSetChanged()
        }
        mBinding!!.recyclerViewPatientList.visibility = View.VISIBLE
        mBinding!!.linearPatientNoDataFound.visibility = View.GONE
    }

    private fun initUI() {
        mActivity = this
        mContext = this

        layoutManager = LinearLayoutManager(mContext)
        mBinding!!.recyclerViewPatientList.layoutManager = layoutManager

        val intent = intent
        if (intent.hasExtra(Const.USER_OBJ)) {
            userObj = Gson().fromJson(intent.getStringExtra(Const.USER_OBJ), User::class.java)
        }

        fab.setOnClickListener {
            Const.FAB_CLICKED = "1"
            val intent = Intent(mContext, VisitingReasonActivity::class.java)
            intent.putExtra(Const.USER_OBJ, Gson().toJson(userObj))
            startActivity(intent)
        }

        presenter.getAllUser(mContext)

    }

    override fun getAllUserSuccess(userList: List<User>) {
        mDataList = userList as MutableList<User>
        displayDataInAdapter()

    }

    override fun showingLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        Const.FAB_CLICKED = "0"
    }
}