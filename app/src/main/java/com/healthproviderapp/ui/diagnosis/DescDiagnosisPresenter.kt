package com.healthproviderapp.ui.diagnosis

import android.content.Context
import com.healthproviderapp.base.LifecycleAwarePresenter
import com.healthproviderapp.roomdatabase.database.AppDB
import com.healthproviderapp.roomdatabase.entity.Diags
import com.healthproviderapp.roomdatabase.entity.UserHealthRecords
import com.healthproviderapp.utils.SharedPref

class DescDiagnosisPresenter(
        private val mView: DescDiagnosisContract.DescDiagnosisView,
        val sharedPreference: SharedPref
) : LifecycleAwarePresenter<DescDiagnosisContract.DescDiagnosisView>(mView),
        DescDiagnosisContract.DescDiagnosisInterface {


    override fun getDiagnosisList(context: Context) {
//        mView.showingLoading()
          mView.getDiagListInUI(AppDB.getDatabase(context).diagsDao().getAllDiags())
    }

    override fun getSymptomsList(context: Context) {
        mView.getSympListInUI(AppDB.getDatabase(context).allergyDao().getAllAllergies())

    }

    override fun getPrescriptionList(context: Context) {
        mView.getPrescibeListInUI(AppDB.getDatabase(context).prescribeDao().getAllPresription())
//        mView.dismissLoading()

    }

    override fun insertUserHealthRecords(context: Context, id: Int, diagnosis: String?, symptoms: String?, prescription: String?, reason :String?, userId : Long?) {
        AppDB.getDatabase(context).userRecordDao().insertAll(UserHealthRecords(id, diagnosis, symptoms, prescription, reason, userId))
        mView.insertedRecordSuccess("success")
    }

}