package com.healthproviderapp.ui.diagnosis

import android.content.Context
import com.healthproviderapp.base.BasePresenter
import com.healthproviderapp.base.BaseView
import com.healthproviderapp.roomdatabase.entity.Allergies
import com.healthproviderapp.roomdatabase.entity.Diags
import com.healthproviderapp.roomdatabase.entity.Prescription


class DescDiagnosisContract {

    interface DescDiagnosisInterface : BasePresenter {
        fun getDiagnosisList(context: Context)
        fun getSymptomsList(context: Context)
        fun getPrescriptionList(context: Context)
        fun insertUserHealthRecords(context: Context, id: Int, diagnosis: String?, symptoms: String?, prescription: String?, reason :String?, userId : Long?)
    }

    interface DescDiagnosisView : BaseView<DescDiagnosisInterface> {
        fun getDiagListInUI(list : List<Diags>)
        fun getSympListInUI(list : List<Allergies>)
        fun getPrescibeListInUI(list : List<Prescription>)
        fun insertedRecordSuccess(msg : String)
    }
}