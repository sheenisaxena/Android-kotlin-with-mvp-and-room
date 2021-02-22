package com.healthproviderapp.ui.diagnosis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blaxity.provider.DependenciesProvider
import com.google.gson.Gson
import com.healthproviderapp.R
import com.healthproviderapp.base.BaseActivity
import com.healthproviderapp.databinding.ActivityDiagnosisBinding
import com.healthproviderapp.roomdatabase.entity.Allergies
import com.healthproviderapp.roomdatabase.entity.Diags
import com.healthproviderapp.roomdatabase.entity.Prescription
import com.healthproviderapp.roomdatabase.entity.User
import com.healthproviderapp.ui.dashboard.DashboardActivity
import com.healthproviderapp.utils.Const
import com.healthproviderapp.utils.toast
import kotlinx.android.synthetic.main.activity_diagnosis.*
import java.util.*


private var mBinding: ActivityDiagnosisBinding? = null
private lateinit var mActivity: Activity
private lateinit var mContext: Context
private lateinit var presenter: DescDiagnosisPresenter
private lateinit var listDiagsDesc: ArrayList<String>
private lateinit var listSympDesc: ArrayList<String>
private lateinit var listPrescribeDesc: ArrayList<String>
private lateinit var visitingReasonStr: String
private lateinit var userObj : User


class DescDiagnosisActivity : BaseActivity(), DescDiagnosisContract.DescDiagnosisView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_diagnosis
        )

        val intent = intent
        visitingReasonStr = intent.getStringExtra(Const.VISITING_REASON).toString()
        userObj = Gson().fromJson(
            intent.getStringExtra(Const.USER_OBJ),User::class.java)
        instantiateDependencies()

        initUI()
    }

    private fun instantiateDependencies() {
        val provider = DependenciesProvider.getInstance(applicationContext)

        presenter = DescDiagnosisPresenter(
                this,
                provider.getSharedPreference())

    }

    private fun initUI() {
        mActivity = this
        mContext = this

        listDiagsDesc = ArrayList<String>()
        listSympDesc = ArrayList<String>()
        listPrescribeDesc = ArrayList<String>()

        clickListener()

        presenter.getDiagnosisList(mContext)
        presenter.getSymptomsList(mContext)
        presenter.getPrescriptionList(mContext)

    }

    private fun clickListener() {
        btnNext.setOnClickListener(this)
    }

    override fun getDiagListInUI(list: List<Diags>) {
        listDiagsDesc.clear()
        for (element in list) {
            listDiagsDesc.add(element.description!!)
        }
        val adapterdiag: ArrayAdapter<String> = ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listDiagsDesc)
        diagAutoCompletetv.setAdapter(adapterdiag)
    }

    override fun getSympListInUI(list: List<Allergies>) {
        listSympDesc.clear()
        for (element in list) {
            listSympDesc.add(element.description!!)
        }
        val adaptersymp: ArrayAdapter<String> = ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listSympDesc)
        sympAutoCompletetv.setAdapter(adaptersymp)
    }

    override fun getPrescibeListInUI(list: List<Prescription>) {
        listPrescribeDesc.clear()
        for (element in list) {
            listPrescribeDesc.add(element.description!!)
        }
        val adapterpres: ArrayAdapter<String> = ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listPrescribeDesc)
        presAutoCompletetv.setAdapter(adapterpres)
    }

    override fun insertedRecordSuccess(msg: String) {
        toast(R.string.label_success_inserted_records)
        if (Const.FAB_CLICKED != "1") {
            val intent = Intent(mContext, DashboardActivity::class.java)
            intent.putExtra(Const.USER_OBJ, Gson().toJson(userObj))
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finishAffinity()
        } else {
            finish()
        }

    }

    override fun showingLoading() {
//        showLoading()
  }

    override fun dismissLoading() {
//        hideLoading()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnNext -> {
                validateAndRedirect()
            }
        }
    }

    private fun validateAndRedirect() {

        if (diagAutoCompletetv.text.toString().trim().isEmpty()) {
            toast("Please enter diagnosis")
            return
        }

        if (sympAutoCompletetv.text.toString().trim().isEmpty()) {
            toast("Please enter symptoms")
            return
        }

        if (presAutoCompletetv.text.toString().trim().isEmpty()) {
            toast("Please enter prescription")
            return
        }

        presenter.insertUserHealthRecords(mContext, 0, diagAutoCompletetv.text.toString().trim(), sympAutoCompletetv.text.toString().trim(), presAutoCompletetv.text.toString().trim(), visitingReasonStr, userObj.uid)

    }
}